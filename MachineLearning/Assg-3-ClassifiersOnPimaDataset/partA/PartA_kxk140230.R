#install.packages("rpart",dependencies = TRUE)
#library(rpart)

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

args <- commandArgs(TRUE)
pimaData <- read.csv(file=args[1], sep = ",")
#pimaData <- read.csv("~/Desktop/MachineLearning/Assg-3/pima-indians-diabetes.data", header=FALSE)

#HISTOGRAMS and BARPLOTS OF ALL VARIABLES
#Histogram and Barplot of V1
jpeg("plotsPartA/V1Histogram.jpg")
hist(pimaData$V1, col = "blue", xlab = "Number of times pregnant", main = "Histogram")
dev.off()
jpeg("plotsPartA/V1BarPlot.jpg")
k <- table(pimaData$V1)
barplot(k, xlab = "Number of times pregnant", main = "Bar Plot")
dev.off()


#V2
jpeg("plotsPartA/V2Histogram.jpg")
hist(pimaData$V2, col = "blue", xlab = "Plasma glucose concentration", main = "Histogram")
dev.off()
jpeg("plotsPartA/V2BarPlot.jpg")
k <- table(pimaData$V2)
barplot(k, xlab = "Plasma glucose concentration", main = "Bar Plot")
dev.off()

#V3
jpeg("plotsPartA/V3Histogram.jpg")
hist(pimaData$V3, col = "blue", xlab = "Diastolic blood pressure", main = "Histogram")
dev.off()
jpeg("plotsPartA/V3BarPlot.jpg")
k <- table(pimaData$V3)
barplot(k, xlab = "Diastolic blood pressure", main = "Bar Plot")
dev.off()

#V4
jpeg("plotsPartA/V4Histogram.jpg")
hist(pimaData$V4, col = "blue", xlab = "Triceps skin fold thickness", main = "Histogram")
dev.off()
jpeg("plotsPartA/V4BarPlot.jpg")
k <- table(pimaData$V4)
barplot(k, xlab = "Triceps skin fold thickness", main = "Bar Plot")
dev.off()

#V5
jpeg("plotsPartA/V5Histogram.jpg")
hist(pimaData$V5, col = "blue", xlab = "2-Hour serum insulin", main = "Histogram")
dev.off()
jpeg("plotsPartA/V5BarPlot.jpg")
k <- table(pimaData$V5)
barplot(k, xlab = "2-Hour serum insulin", main = "Bar Plot")
dev.off()

#V6
jpeg("plotsPartA/V6Histogram.jpg")
hist(pimaData$V6, col = "blue", xlab = "Body mass index", main = "Histogram")
dev.off()
jpeg("plotsPartA/V6BarPlot.jpg")
k <- table(pimaData$V6)
barplot(k, xlab = "Body mass index", main = "Bar Plot")
dev.off()

#V7
jpeg("plotsPartA/V7Histogram.jpg")
hist(pimaData$V7, col = "blue", xlab = "Diab. pedigree function", main = "Histogram")
dev.off()
jpeg("plotsPartA/V7BarPlot.jpg")
k <- table(pimaData$V7)
barplot(k, xlab = "Diab. pedigree function", main = "Bar Plot")
dev.off()


#V8
jpeg("plotsPartA/V8Histogram.jpg")
hist(pimaData$V8, col = "blue", xlab = "Age", main = "Histogram")
dev.off()
jpeg("plotsPartA/V8BarPlot.jpg")
k <- table(pimaData$V8)
barplot(k, xlab = "Age", main = "Bar Plot")
dev.off()

#V9
jpeg("plotsPartA/V9Histogram.jpg")
hist(pimaData$V9, col = "blue", xlab = "Class variable 0|1", main = "Histogram")
dev.off()
jpeg("plotsPartA/V9BarPlot.jpg")
k <- table(pimaData$V9)
barplot(k, xlab = "Class variable 0|1", main = "Bar Plot")
dev.off()


#Find the correlation between each of the attributes and the class variable.
sink("Part1Ques2.txt")
for(i in 1:8)
  {
  corrVariable = cor(pimaData[i], y=pimaData[9])
  print(corrVariable )
}
sink()

#Compute the correlation between all pairs of the 8 attributes.
sink("Part1Ques3.txt")
maxCorr <- 0.0
for (x in 1:9)
  {
  for(k in 2:9){
    if(x!=k){
      corrVar = cor(pimaData[x],y = pimaData[k])
      if(maxCorr < corrVar)
        maxCorr <- corrVar
    }
  }
}
print (maxCorr)
cat("Highest Mutual correlation: ", maxCorr)
sink()