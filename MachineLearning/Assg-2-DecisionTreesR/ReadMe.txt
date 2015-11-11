
NetId - kxk140230
Name - Kanika Kapoor
Date of Submission - Sept 27, 2015
Title - Machine Learning Assg02 Decision Trees

**********ZIP FOLDER CONTENTS**********
1) Dataset-1 files as training_set1.csv validation_set1.csv test_set1.csv
2) Dataset-2 files as training_set2.csv validation_set2.csv test_set2.csv
3) R script as kxk140230_Assg2.R
4) Empty folder as ‘images’ and ‘txtFiles’ where result files will be stored once you run the script from command line
5) Sample results as ‘sampleResults’ folder
6) Report as kxk140230_Report


**********HOW TO RUN THE SCRIPT FROM COMMAND LINE?**********

Step - 1 Download the zip folder to a your directory. Suppose it is Downloads.
Step - 2 Unzip the file. It will create a folder as ‘kapoor_kxk140230_Assg2’.
Step - 3 From terminal/cmd, navigate to the new unzipped folder
Step - 4 Run script as Rscript nameOfScript Argument files(.csv)
	 (Arguments go in order of training set, validation set, test set)
	 
For dataset-1
Rscript kxk140230_Assg2.R training_set1.csv validation_set1.csv test_set1.csv 

For dataset -2
Rscript kxk140230_Assg2.R training_set2.csv validation_set2.csv test_set2.csv

These cvs files are in the same folder as the script.

**********RESULTS**********

Once you run the script from command line, the plot results will be stored in ‘images’ folder as .jpeg files and summary of model and other useful data will be stored in ‘txtFiles’ folder as .txt files.
Each time you run the script, the results will be overwritten with new results.

(I created 2 empty folder for ease, so that you can view the results clearly.)

**********CHANGE DATASET**********

Step - 1 If you wish to change the data set files, copy the .csv files to folder where the script resides, and run in the same way as before.

If you do not wish to copy, then provide complete path of .csv files in the arguments


**********PACKAGE AND PARAMETERS USED**********Package used – rpartIt is used for recursive partitioning of decision tress for classification and regression procedures. 
It creates a model using parameters such as split type and minsplit value. In our case we have used two values of minsplit, 2 being the least and 100 being the max. For pruning the dataset , it uses parameters such as cp value which is the complexity parameter. We plot a cp table using command printcp(modelname) and then find the cp value corresponding to least xerror value. We use that cp value for pruning our dataset . Once we have the pruned tree, we predict it using again the testdata. It uses parameter such as type , which can be prob, vector etc. We have used type=”class” for our analysis. All plotting is done using the plot and text functions and the results have been stored as .jpg files. The summary of data models have been saved as .txt files. 

