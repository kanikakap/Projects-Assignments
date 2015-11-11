args <- commandArgs(TRUE)
dataURL<-as.character(args[1])
header<-as.logical(args[2])

if (!require("rpart")) 
{
  install.packages("rpart", repos="http://cran.rstudio.com/") 
  library("rpart")
}

if (!require("e1071")) {
  install.packages("e1071", repos="http://cran.rstudio.com/") 
  library("e1071")
}

if(!require("class"))
{
  install.packages("class",repos="http://cran.rstudio.com/")
  library("class")
}

if(!require("neuralnet"))
{
  install.packages("neuralnet",repos="http://cran.rstudio.com/")
  library("neuralnet")
}

if(!require("mlbench"))
{
  install.packages("mlbench",repos="http://cran.rstudio.com/")
library("mlbench")
}

if(!require("adabag"))
{
  install.packages("adabag",repos="http://cran.rstudio.com/")
  library("adabag")
}
library("ipred")
library("ada")

if (!require("party")) {
  install.packages("party", repos="http://cran.rstudio.com/") 
  library("party")
  
}

#read data
d<-read.csv(dataURL,header = header)
#d <- read.csv(file="/Users/kanikakapoor/Downloads/binary.csv",header=TRUE,sep=",")

#initialize arrays to store accuracy values 
dTArray<- numeric(10)
sVMArray<- numeric(10)
nBArray<- numeric(10)
kNNArray<- numeric(10)
lRArray<- numeric(10)
nNArray<- numeric(10)
baggingArray<- numeric(10)
rFArray<- numeric(10)
boostingArray<- numeric(10)


# create 10 samples
for(i in 1:10) 
{
  cat("Running sample ",i,"\n")
  sampleInstances<-sample(nrow(d),size = 0.9*nrow(d))
  trainingData <- d[sampleInstances,]
  testData <- d[-sampleInstances,]
  
  #------------------DECISION TREE-----------------
  #Model
  dTTrainModel <- rpart(as.factor(admit) ~ ., data = trainingData, parms=list(split="information"), method ="class", minsplit=2)
  prunedTree <- prune(dTTrainModel, cp =0.03)
  #predict
  dTPredict <- predict(prunedTree, newdata = testData, type = "class")
  #calculating accuracy
  dTTable <- table(testData$admit, dTPredict)
  dTCorrect <- sum(diag(dTTable))
  dTError <- sum(dTTable)-dTCorrect
  dTArray[i] <- (dTCorrect / (dTCorrect+dTError))*100
  
  
  #--------------SUPPORT VECTOR MACHINES-----------------
  #Model
  sVMTrainModel <- svm(as.factor(admit) ~ gre+gpa+rank, data =trainingData,method="C-classification")
  #Predict
  sVMPredict <- predict(sVMTrainModel, testData,type = "class")
  #accuracy
  sVMTable <- table(testData$admit, sVMPredict)
  sVMCorrect <- sum(diag(sVMTable))
  sVMError <- sum(sVMTable)-sVMCorrect
  sVMArray[i] <- (sVMCorrect / (sVMCorrect+sVMError))*100
  
  #------------------NAIVE BAYES------------------
  #Model 
  nBTrainModel <- naiveBayes(as.factor(admit) ~ gre+gpa+rank, data = trainingData,laplace = 5)
  #prediction
  nBPredict<- predict(nBTrainModel,testData)
  #calculating accuracy
  nBTable <- table(testData$admit, nBPredict)
  nBCorrect <- sum(diag(nBTable))
  nBError <- sum(nBTable)-nBCorrect
  nBArray[i] <- (nBCorrect / (nBCorrect+nBError))*100
  
  #--------------kNN-------------"
  #Model for kNN
  kNNModel <- knn(trainingData, testData, cl=trainingData$admit, k = 5, l = 0 , prob = FALSE, use.all = TRUE)
  #no prediction in knn
  kNNTable <- table(testData$admit, kNNModel)
  kNNCorrect <- sum(diag(kNNTable))
  kNNError <- sum(kNNTable)-kNNCorrect
  kNNArray[i] <- (kNNCorrect / (kNNCorrect+kNNError))*100
  
  #--------------LOGISTIC REGRESSION---------------
  #Model 
  lRTrainModel <- glm(admit ~ gre + gpa + rank, data = trainingData, family = "binomial")

  # use predict with type="response", it will give you a probability of 1
  lRPredict<-predict(lRTrainModel, newdata=testData, type="response")
  # use a threshold value and anything above that, you can assign to class=1 others to class=0
  threshold=0.65
  prediction<-sapply(lRPredict, FUN=function(x) if (x>threshold) 1 else 0)
  actual<-testData$admit
  accuracy <- sum(actual==prediction)/length(actual)
  #print(accuracy)
  lRArray[i] <- accuracy*100
  
  #---------------NEURAL NETWORK---------------
  #Model 
  nNTrainModel <- neuralnet(admit ~ gre + gpa + rank, data = trainingData ,hidden = 4, lifesign = "minimal", linear.output = FALSE, threshold = 0.1)
  
  #to predict in NN, we use compute method
  temp_test <- subset(testData, select = c("gre", "gpa","rank"))
  nNCompute<- compute(nNTrainModel, temp_test)
  results <- data.frame(actual = testData$admit, prediction = nNCompute$net.result)
  results$prediction <- round(results$prediction)
  #accuracy
  nNTable <- table(results$actual, results$prediction)
  nNCorrect <- sum(diag(nNTable))
  nNError <- sum(nNTable)-nNCorrect
  nNArray[i] <- (nNCorrect / (nNCorrect+nNError))*100
  
  #----------------BAGGING-----------------
  #Model 
  bagTrainModel <- bagging(admit ~., data = trainingData, coob=TRUE)
  #predict 
  bagPredict <- predict(bagTrainModel,testData)
  #accuracy
  bagTable <- table(testData$admit, bagPredict)
  bagCorrect <- sum(diag(bagTable))
  
  #calculating error in different way in order to improve accuracy of ensemble methods
  #Ref: http://www.vikparuchuri.com/blog/intro-to-ensemble-learning-in-r/
  error<-sqrt((sum((testData$admit-bagPredict)^2))/nrow(testData)) 
  error
  baggingArray[i] <- (bagCorrect / (bagCorrect+error))*100
  
  #---------------RANDOM FOREST----------------
  #Model
  rFTrainmodel <- ctree(admit ~., data = trainingData)
  #predict
  rFPredict <- predict(rFTrainmodel,testData)
  #accuracy
  rFTable <- table(testData$admit, rFPredict)
  rFCorrect <- sum(diag(rFTable))
  error1<-sqrt((sum((testData$admit-rFPredict)^2))/nrow(testData)) 
  error1
  rFArray[i] <- (rFCorrect / (rFCorrect+error1))*100
  
  #--------------------BOOSTING---------------
  #Model 
  boostingTrainModel <- ada(admit ~ ., data = trainingData, iter=20, nu=1, type="discrete")
  #predict
  boostingPredict=predict(boostingTrainModel,testData)
  # accuracy
  boostingTable <- table(testData$admit, boostingPredict)
  boostingCorrect <- sum(diag(boostingTable))
  boostingError <- sum(boostingTable)-boostingCorrect
  boostingArray[i]<- (boostingCorrect / (boostingCorrect+boostingError))*100
}

sink("Results-Dataset2.txt")
#output the array storing accuracy of all experiments
cat("Decision Tree Accuracy Array: ",dTArray)
cat("\n")
cat("SVM Accuracy Array: ",sVMArray)
cat("\n")
cat("Naive Bayes Accuracy Array: ",nBArray)
cat("\n")
cat("kNN Accuracy Array: ",kNNArray)
cat("\n")
cat("Logistic Regression Accuracy Array: ",lRArray)
cat("\n")
cat("Neural Network Accuracy Array: ",nNArray)
cat("\n")
cat("Bagging Accuracy Array: ",baggingArray)
cat("\n")
cat("Random Forest Accuracy Array: ",rFArray)
cat("\n")
cat("Boosting Accuracy Array: ",boostingArray)
cat("\n")
cat("\n")

#average Accuracy of 10 expt
dTAvg <- sum(dTArray)/10
cat("Average accuracy of 10 samples in Decision Tree Classifier: ",dTAvg)
cat("\n")
sVMAvg <- sum(sVMArray)/10
cat("Average accuracy of 10 samples in SVM Classifier: ",sVMAvg)
cat("\n")
nBAvg <- sum(nBArray)/10
cat("Average accuracy of 10 samples in Naive Bayes Classifier: ",nBAvg)
cat("\n")
kNNAvg <- sum(kNNArray)/10
cat("Average accuracy of 10 samples in KNN Classifier: ",kNNAvg)
cat("\n")
lRAvg <- sum(lRArray)/10
cat("Average accuracy of 10 samples in Logistic Regression Classifier: ",lRAvg)
cat("\n")
nNAvg <- sum(nNArray)/10
cat("Average accuracy of 10 samples in Neural Network Classifier: ",nNAvg)
cat("\n")
baggingAvg <- sum(baggingArray)/10
cat("Average accuracy of 10 samples in Bagging Classifier: ",baggingAvg)
cat("\n")
rFAvg <- sum(rFArray)/10
cat("Average accuracy of 10 samples in Random Forest Classifier: ",rFAvg)
cat("\n")

boostingAvg <- sum(boostingArray)/10
cat("Average accuracy of 10 samples in Boosting Classifier: ",boostingAvg)
cat("\n")
cat("------------------END OF DATASET - 2----------------")

sink()


