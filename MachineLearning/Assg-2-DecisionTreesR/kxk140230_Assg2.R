#install.packages("rpart",dependencies = TRUE)
#uncomment if rpart is not already installed 

library(rpart)

#for command line arguments
args <- commandArgs(TRUE)

# reading Training Data
trainData <- read.csv(file=args[1],header = TRUE, sep = ",")

# reading Validation Data
validData <- read.csv(file=args[2],header = TRUE, sep = ",")

# combining Training and Validation Dataset into one set
combinedTrainingSet <-rbind(trainData,validData)

#Model Decision Tree with minsplit=2
myModel <-rpart(Class ~ .,parms = list(split="information" ),data = combinedTrainingSet,method = "class",minsplit=100 )

#output results in txt file
sink("txtFiles/myModel.txt")
print(myModel)
sink()

#print cp table & Summary
sink("txtFiles/myModelCPTableAndSummary.txt")
printcp(myModel)
# #summary of myModel
summary(myModel)
sink()

# output results as plot
 jpeg("images/myModel.jpg", width =900, height=800)
 par(mar = rep(0.1, 4))
 plot(myModel,uniform=TRUE, compress=TRUE)
 text(myModel,cex = 1.2)
 dev.off()

#plotcp
jpeg("images/myModelCP.jpg", width = 900, height = 800)
plotcp(myModel)
dev.off()

#prune training model
prunedTree <- prune(myModel, cp =0.010000)

#plot post - pruned tree
jpeg("images/PrunedTree.jpg", width = 900, height = 800)
par(mar = rep(0.1, 4))
plot(prunedTree,uniform=TRUE, compress=TRUE)
text(prunedTree, cex=1.2)
dev.off()

# #plot cp
jpeg("images/PrunedTreeCP.jpg")
plotcp(prunedTree)
dev.off()


#print cp table & Summary
sink("txtFiles/prunedModelCPTableAndSummary.txt")
printcp(prunedTree)
# #summary of prunedModel
summary(prunedTree)
sink()


#test data
testData <- read.csv(file=args[3],header = TRUE, sep = ",")

#predict
predictOutput <- predict(prunedTree, newdata = testData, type = "class")

#accuracy
sink("txtFiles/accuracy.txt")
accuracy1<- table(predictOutput,testData$Class)
sum(diag(accuracy1))/sum(accuracy1) *100
sink()
