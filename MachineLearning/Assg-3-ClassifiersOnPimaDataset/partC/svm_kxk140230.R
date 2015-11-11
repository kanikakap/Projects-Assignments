#install.packages("e1071", dependencies = TRUE)
library(e1071)
args <- commandArgs(TRUE)

#read training data
#pimaDiabData <- read.csv(file=args[1],header = FALSE)
pimaDiabData <- read.csv("~/Desktop/MachineLearning/Assg-3/pima-indians-diabetes.data", header=FALSE)
#nrow(pimaDiabData) #768 rows in the data

#split data set into 90-10 training and testing using Sample function
pimaTrain <- sample(nrow(pimaDiabData),size = 691)
sVMTrainData <- pimaDiabData[pimaTrain,]
sVMTestData<- pimaDiabData[-pimaTrain,]
#nrow(nBTrainData) #691 rows
#nrow(nBTestData) #77 rows

#create SVM Model of Training data

#Default Kernel 
sVMModel <- svm(as.factor(V9) ~ ., data =sVMTrainData,method="C-classification")

#Linear Kernel
#sVMModel <- svm(as.factor(V9) ~ ., data = sVMTrainData,method="C-classification", kernel="linear",cost=5, gamma=0.1)
 
#Polynomial Kernel
#sVMModel <- svm(as.factor(V9) ~ ., data = sVMTrainData,method="C-classification", kernel="polynomial",degree=5,cost=5, gamma=0.1)

#Radial kernel
#sVMModel <- svm(as.factor(V9) ~ ., data = sVMTrainData,method="C-classification", kernel="radial",cost=5, gamma=0.1)

#sigmoid
#sVMModel <- svm(as.factor(V9) ~ ., data = sVMTrainData,method="C-classification", kernel="sigmoid",cost=5, gamma=0.1)

#Predict
sVMPredict <- predict(sVMModel, sVMTestData,type = "class")

#accuracy
sVMTable <- table(sVMTestData$V9, sVMPredict)
sVMCorrect <- sum(diag(sVMTable))
sVMError <- sum(sVMTable)-sVMCorrect
sVMAccuracy <- (sVMCorrect / (sVMCorrect+sVMError))*100
sVMAccuracy




