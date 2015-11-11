library(rpart)
data(solder)
summary(solder)
str(solder)
# solder has 4 factors , so we arbitrarily choose Opening as the useful factor

myModel <- rpart(Opening ~  Solder + Mask + PadType + Panel + skips,data = solder, method = "class", parms = list(split = "information"), minsplit=2)

# n= 720 
printcp(myModel)
prunedmyModel<- prune(myModel, cp=0.010000)

plot(prunedmyModel)
text(prunedmyModel)

train <- sample(nrow(solder), size = 648)
train

trainingData <- solder[train,]
trainingData

testData <- solder[-train,]
testData

nrow(trainingData)
# 648
nrow(testData)
# 72

ktrainingData <- rpart(Opening ~  Solder + Mask + PadType + Panel + skips, data = trainingData, parms=list(split="information"), method ="class", minsplit=2)
printcp(ktrainingData)
prunedTreeKyphosis<- prune(ktrainingData, cp=0.010000)

outputFinal <- predict(prunedTreeKyphosis, newdata = testData, type = "class")
outputFinal

conf_table <- table(testData$Opening, outputFinal)
conf_table
correct <- sum(diag(conf_table))
correct

error <- sum(conf_table)-correct
error

accuracy <- correct / (correct+error)
accuracy

outputFinal2 <- predict(ktrainingData, newdata = testData, type = "class")
outputFinal2
conf_table2 <- table(testData$Opening, outputFinal2)
conf_table2
correct2 <- sum(diag(conf_table2))
correct2
error2 <- sum(conf_table2)-correct2
error2
accuracy2 <- correct2 / (correct2+error2)
accuracy2


