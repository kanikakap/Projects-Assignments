library(rpart)
data(kyphosis)
summary(kyphosis)
str(kyphosis)
kyphosis$Kyphosis
treeGrow <- rpart(Kyphosis ~ Age + Number + Start, data = kyphosis, parms=list(split="information"), method ="class", minsplit=2)
printcp(treeGrow)
plot(treeGrow)
text(treeGrow,use.n = TRUE)
summary(treeGrow)
treeGrow$variable.importance
nrow(kyphosis)

prunedtreeGrow<- prune(treeGrow, cp=0.011767)
plot(prunedtreeGrow)
text(prunedtreeGrow)

#80% training data
train <- sample(nrow(kyphosis),size=64)
train
trainingData <- kyphosis[train,]
trainingData

testData <- kyphosis[-train,]
testData
nrow(trainingData)
nrow(testData)

ktrainingData <- rpart(Kyphosis ~ Age + Number + Start, data = trainingData, parms=list(split="information"), method ="class", minsplit=2)
plot(ktrainingData)
text(ktrainingData, use.n = TRUE)
summary(ktrainingData)
printcp(ktrainingData)

prunedTreeKyphosis<- prune(ktrainingData, cp=0.0145833)

#predict testData on normal not pruned training Data
outputFinal <- predict(ktrainingData, newdata = testData, type = "class")
outputFinal
testData$Kyphosis
conf_table <- table(testData$Kyphosis, outputFinal)
conf_table
correct <- sum(diag(conf_table))
correct
error <- sum(conf_table)-correct
error
accuracy <- correct / (correct+error)
accuracy

#predict testData on pruned training Data
outputFinal2 <- predict(prunedTreeKyphosis, newdata = testData, type = "class")
outputFinal2
conf_table2 <- table(testData$Kyphosis, outputFinal2)
conf_table2
correct2 <- sum(diag(conf_table2))
correct2
error2 <- sum(conf_table2)-correct2
error2
accuracy2 <- correct2 / (correct2+error2)
accuracy2

