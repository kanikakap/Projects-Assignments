library(rpart)
data(solder)
summary(solder)
str(solder)
# solder has 4 factors , so we arbitrarily choose Opening as the useful factor

myModel <- rpart(Opening ~  Solder + Mask + PadType + Panel + skips,data = solder, method = "class", parms = list(split = "information"), minsplit=2)

# n= 720 
printcp(myModel)
prunedmyModel<- prune(myModel, cp=0.010000)
#        CP nsplit rel error  xerror     xstd
#1 0.254167      0   1.00000 1.09375 0.024842
#2 0.062500      1   0.74583 0.78958 0.027912
#3 0.022917      2   0.68333 0.72708 0.027938
#4 0.018750      4   0.63750 0.69167 0.027866
#5 0.010000      6   0.60000 0.60625 0.027433
plot(prunedmyModel)
text(prunedmyModel)

train <- sample(nrow(solder), size = 576)
train

trainingData <- solder[train,]
trainingData

testData <- solder[-train,]
testData

nrow(trainingData)
# 576
nrow(testData)
#144

ktrainingData <- rpart(Opening ~  Solder + Mask + PadType + Panel + skips, data = trainingData, parms=list(split="information"), method ="class", minsplit=2)
plot(ktrainingData)
text(ktrainingData, use.n = TRUE)
summary(ktrainingData)
printcp(ktrainingData)


#
#CP nsplit rel error  xerror     xstd
#1 0.225201      0   1.00000 1.00000 0.030738
#2 0.061662      1   0.77480 0.79357 0.032159
#3 0.017426      2   0.71314 0.73727 0.032139
#4 0.016086      6   0.64343 0.72654 0.032115
#5 0.010000      7   0.62735 0.67828 0.031933

prunedTreeKyphosis<- prune(ktrainingData, cp=0.010000)


outputFinal <- predict(ktrainingData, newdata = testData, type = "class")
outputFinal

conf_table <- table(testData$Opening, outputFinal)
conf_table
correct <- sum(diag(conf_table))
correct

error <- sum(conf_table)-correct
error

accuracy <- correct / (correct+error)
accuracy

outputFinal2 <- predict(prunedTreeKyphosis, newdata = testData, type = "class")
outputFinal2
conf_table2 <- table(testData$Opening, outputFinal2)
conf_table2
correct2 <- sum(diag(conf_table2))
correct2
error2 <- sum(conf_table2)-correct2
error2
accuracy2 <- correct2 / (correct2+error2)
accuracy2

