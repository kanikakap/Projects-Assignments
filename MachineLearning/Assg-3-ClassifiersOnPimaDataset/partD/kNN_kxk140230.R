?knn

library(class)
#read training data
#pimaDiabData <- read.csv(file=args[1],header = FALSE)
pimaDiabData <- read.csv("~/Desktop/MachineLearning/Assg-3/pima-indians-diabetes.data", header=FALSE)
summary(pimaDiabData)

pimaTrain <- sample(nrow(pimaDiabData),size = 691)
kNNTrainData <- pimaDiabData[pimaTrain,]
kNNTestData<- pimaDiabData[-pimaTrain,]
#nrow(kNNTrainData) #691 rows
#nrow(kNNTestData) #77 rows

kNNModel <- knn(kNNTrainData, kNNTestData, cl=kNNTrainData$V9, k = 11, l = 0 , prob = FALSE, use.all = TRUE)
#kNNModel

kNNTable <- table(kNNTestData$V9, kNNModel)
kNNCorrect <- sum(diag(kNNTable))
kNNError <- sum(kNNTable)-kNNCorrect
kNNAccuracy <- (kNNCorrect / (kNNCorrect+kNNError))*100
kNNAccuracy