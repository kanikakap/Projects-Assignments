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

if (!require("party")) {
  install.packages("party", repos="http://cran.rstudio.com/") 
  library("party")
  
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

library("ada")

#read from URL
d<-read.csv(dataURL,header = header)
#d <- read.csv("https://archive.ics.uci.edu/ml/machine-learning-databases/breast-cancer-wisconsin/wpbc.data", header=FALSE, sep = ",")


d <- na.omit(d)
d[d=="NA"] <- 0
d[d=="?"] <- 0
#View(d)
#names(d)

#is.na(d)
#initialize separate arrays to store accuracy values 
dTArray<- numeric(10)
sVMArray<- numeric(10)
nBArray<- numeric(10)
kNNArray<- numeric(10)
lRArray<- numeric(10)
nNArray<- numeric(10)
baggingArray<- numeric(10)
rFArray<- numeric(10)
boostingArray<- numeric(10)


#Class attribute is Column 2 i.e V2

# create 10 samples
for(i in 1:10) 
{
  cat("Running sample ",i,"\n")
  sampleInstances<-sample(nrow(d),size = 0.9*nrow(d))
  trainingData <- d[sampleInstances,]
  testData <- d[-sampleInstances,]
  
  
  #--------------DECISION TREE----------------
  dTTrainModel <- rpart(as.factor(V2) ~ ., data = trainingData, parms=list(split="information"), method ="class", minsplit=2)
  prunedTree <- prune(dTTrainModel, cp =0.03)
  #predict
  dTPredict <- predict(prunedTree, newdata = testData, type = "class")
  #calculating accuracy
  dTTable <- table(testData$V2, dTPredict)
  dTCorrect <- sum(diag(dTTable))
  dTError <- sum(dTTable)-dTCorrect
  dTArray[i] <- (dTCorrect / (dTCorrect+dTError))*100
  
  #---------------SUPPORT VECTOR MACHINES-----------------
  #Model
  sVMTrainModel <- svm(as.factor(V2) ~ ., data =trainingData,method="C-classification")
  #Predict
  sVMPredict <- predict(sVMTrainModel, testData,type = "class")
  #accuracy
  sVMTable <- table(testData$V2, sVMPredict)
  sVMCorrect <- sum(diag(sVMTable))
  sVMError <- sum(sVMTable)-sVMCorrect
  sVMArray[i] <- (sVMCorrect / (sVMCorrect+sVMError))*100
  
  #----------------NAIVE BAYES---------------
  #Model for Naive Bayesian
  nBTrainModel <- naiveBayes(as.factor(V2) ~ ., data = trainingData,laplace = 5)
  #prediction
  nBPredict<- predict(nBTrainModel,testData)
  #calculating accuracy
  nBTable <- table(testData$V2, nBPredict)
  nBCorrect <- sum(diag(nBTable))
  nBError <- sum(nBTable)-nBCorrect
  nBArray[i] <- (nBCorrect / (nBCorrect+nBError))*100
  
  #-------------KNN----------------

  f1<-factor(trainingData$V2)
  #convert to numeric
  trainingData[,as.integer(2)] <- as.numeric(trainingData[,as.integer(2)])
  testData[,as.integer(2)] <- as.numeric(testData[,as.integer(2)])
  #Model for kNN
  kNNMmodel <- knn(trainingData, testData, f1, k = 5, l = 0 , prob = FALSE, use.all = TRUE)
  kNNTable <- table(testData$V2, kNNMmodel)
  kNNCorrect <- sum(diag(kNNTable))
  kNNError <- sum(kNNTable)-kNNCorrect
  kNNArray[i] <- (kNNCorrect / (kNNCorrect+kNNError))*100

  #---------------LOGISTIC REGRESSION------------------
  #Model
  lRTrainModel <- glm(as.factor(V2) ~  V3 + V6 + V7 ++V25+V17 +V26 +V27, data = trainingData, family = "binomial") 
  
  lRPredict<-predict(lRTrainModel,testData, type="response")
  threshold=0.40
  lRPrediction<-sapply(lRPredict, FUN=function(x) if (x>threshold) 1 else 0)
  actual<-as.integer(testData[,2])
  #accuracy 
  accuracy <- sum(actual==lRPrediction)/length(actual)
  lRArray[i] <- accuracy*100
 
  #--------------NEURAL NETWORK-------------
  #Model for Neural Network
  sapply(d, sd)
  nNTrainModel <- neuralnet(as.integer(V2) ~ V3 + V6 + V7 +V17 +V25+V26 +V27 , trainingData, hidden = 10, lifesign = "minimal", 
                         linear.output = FALSE, threshold = 0.1,stepmax=1e6)

  temp_test <- subset(testData, select = c("V3", "V6", "V7","V17","V25","V26","V27"))
  nNCompute <- compute(nNTrainModel, temp_test)
  results <- data.frame(actual = testData$V2, prediction = nNCompute$net.result) 
  results$prediction <- round(results$prediction)
  nNTable<-table(results$actual,results$prediction)
  nNCorrect <- sum(diag(nNTable))
  nNError <- sum(nNTable)-nNCorrect
  nNArray[i] <- (nNCorrect / (nNCorrect+nNError))*100

 
  #----------------BAGGING-------------
  #Model 
  
  bagTrainModel <- ada(V2 ~ ., data = trainingData,control = rpart.control(maxdepth=1))
  #predict
  bagPredict <-predict(bagTrainModel, testData)
  #accuracy
  baggingTable <- table(testData$V2, bagPredict)
  baggingCorrect <- sum(diag(baggingTable))
  baggingError <- sum(baggingTable)-baggingCorrect
  baggingArray[i]<- (baggingCorrect / (baggingCorrect+baggingError))*100
  
  #----------------RANDOM FOREST-------------
  #Model 
  rFTrainmodel <- ctree(as.factor(V2) ~ ., data=trainingData)
  #predict
  rFPredict <- predict(rFTrainmodel,testData)
  #accuracy
  rFTable <- table(testData$V2, rFPredict)
  rFCorrect <- sum(diag(rFTable))
  rFError <- sum(rFTable)-rFCorrect
  rFArray[i] <- (rFCorrect / (rFCorrect+rFError))*100
  
  #-------------BOOSTING----------------
  #Model for Boosting
  
  boostingTrainModel <- ada(V2 ~ ., data = trainingData, iter=20, nu=1, type="discrete")
  boostingPredict=predict(boostingTrainModel,testData)
 
  boostingTable <- table(testData$V2, boostingPredict)
  boostingCorrect <- sum(diag(boostingTable))
  boostingError <- sum(boostingTable)-boostingCorrect
  boostingArray[i]<- (boostingCorrect / (boostingCorrect+boostingError))*100
}

#output the array storing accuracy of all experiments
sink("Results-Dataset-3.txt")
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
cat("-----------END OF DATASET-3------------")
sink()

