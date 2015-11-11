
NetId - kxk140230
Name - Kanika Kapoor
Date of Submission - Oct 16, 2015
Title - Machine Learning Assg03 Comparison of Classifiers

**********ZIP FOLDER CONTENTS**********
1) partA folder - for exploratory data analysis
2) partB folder - Naive  bayes classifier
3) partC folder - Support vector machine classifier
4) partD folder - k-Nearest Neighbour classifier

**********HOW TO RUN THE SCRIPT FROM COMMAND LINE?**********

Step - 1 Download the zip folder to a your directory. Suppose it is Downloads.
Step - 2 Unzip the file. It will create a folder as ‘kapoor_kxk140230’.
Step - 3 From terminal/cmd, navigate to the new unzipped folder
Step - 4 Run script as 
Rscript nameOfScript Argument files(.csv/.data)

	 
For partA, go to partA folder
Rscript PartA_kxk140230.R pimaData.data

For partB, go to partB folder
Rscript naive_kxk140230.R pimaData.data

For partC, go to partC folder
Rscript svm_kxk140230.R pimaData.data

For partD, go to partD folder
Rscript kNN_kxk140230.R pimaData.data

The pimaData.data is in the same folder as the script.

**********RESULTS**********

Once you run the script from command line, the plot results for partA will be stored in ‘plotsPartA’ folder as .jpeg files and correlation of attributes with class variable and within themselves will be stored in two separate text files. 
Each time you run the script, the results will be overwritten with new results.

For every part, there is a separate ReadMe file and Report for results in pdf format. Please check the report for average accuracies. 

**********WHICH CLASSIFIER WORKED BEST FOR PIMA DATASET**********After calculating the average accuracies for each classifier, the classifier that worked best was Support Vector machine with default kernel. It gave the highest average accuracy of 77.5% in comparison to all others. 
Whereas in SVM only, kernel = sigmoid gave the lowest average accuracy amongst all classifier as 66.7%. It showed varying results. 

All other classifier worked equally good. Naive Bayes classifier gave a consistent accuracy in all sampling. 

k nearest neighbor showed an uneven trend in average accuracies with changing value of k. As we increase the value of k, accuracy increases . But in case of k=9, the avg accuracy dipped a little as compared to k=7. Otherwise, average accuracy in kNN increased in general with increasing value of k.
