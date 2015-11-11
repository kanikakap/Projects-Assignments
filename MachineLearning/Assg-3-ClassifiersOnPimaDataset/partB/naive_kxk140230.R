#uncomment the below line if you need to install package
if (!require("rpart")) {
  install.packages("rpart", repos="http://cran.rstudio.com/") 
  library("rpart")
}
#install.packages("e1071")
#library (e1071)

if (!require("e1071")) {
  install.packages("e1071", repos="http://cran.rstudio.com/") 
  library("e1071")
}

#read training data
pimaDiabData <- read.csv(file=args[1],header = FALSE)
#pimaDiabData <- read.csv("~/Desktop/MachineLearning/Assg-3/pima-indians-diabetes.data", header=FALSE)
summary(pimaDiabData)
#nrow(pimaDiabData) #768 rows in the data

#split data set into 90-10 training and testing using Sample function
pimaTrain <- sample(nrow(pimaDiabData),size = 691)

nBTrainData <- pimaDiabData[pimaTrain,]
nBTestData<- pimaDiabData[-pimaTrain,]
#nrow(nBTrainData) #691 rows
#nrow(nBTestData) #77 rows

#?naiveBayes

#create NaiveBayes Model of Training data

#for default Laplace value
nBTrainModel <- naiveBayes(as.factor(V9) ~ ., data = nBTrainData)

nBPredict<- predict(nBTrainModel,nBTestData,type = "class")

nBTable <- table(nBTestData$V9, nBPredict)
#nBTable
nBCorrect <- sum(diag(nBTable))
nBError <- sum(nBTable)-nBCorrect
nBAccuracy <- (nBCorrect / (nBCorrect+nBError))*100
nBAccuracy



