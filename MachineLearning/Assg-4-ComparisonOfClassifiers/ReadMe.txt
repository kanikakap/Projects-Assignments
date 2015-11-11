
NetId - kxk140230
Name - Kanika Kapoor
Date of Submission - Nov 01, 2015
Title - Machine Learning Assg04 Comparison of Classifiers

————————ZIP FOLDER CONTENTS———————
1) Dataset1.R - For credit default dataset
2) Dataset2.R - For Graduate school admission dataset 	
3) Dataset3.R - For Wisconsin Prognostic Breast Cancer (WPBC) dataset
4) Dataset4.R - For Wisconsin Diagnostic Breast Cancer (WDBC) dataset:
5) Dataset5.R - For Ionosphere dataset
6) Report.pdf - consists of all accuracy tables and best classifiers for each dataset


———————HOW TO RUN THE SCRIPT FROM COMMAND LINE?——————

Step - 1 Download the zip folder to a your directory. Suppose it is Downloads.
Step - 2 Unzip the file. It will create a folder as ‘kapoor_kxk140230’.
Step - 3 From terminal/cmd, navigate to the new unzipped folder
Step - 4 Run script as 
Rscript nameOfScript Argument files or url (.csv/.data)

	 
Dataset1
Rscript Dataset1.R http://www.utdallas.edu/~axn112530/cs6375/creditset.csv T 

Dataset2
Rscript Dataset2.R http://www.ats.ucla.edu/stat/data/binary.csv T

Dataset3
Rscript Dataset3.R https://archive.ics.uci.edu/ml/machine-learning-databases/breast-cancer-wisconsin/wpbc.data F

Dataset4
Rscript Dataset4.R https://archive.ics.uci.edu/ml/machine-learning-databases/breast-cancer-wisconsin/wdbc.data F

Dataset5
Rscript Dataset5.R http://archive.ics.uci.edu/ml/machine-learning-databases/ionosphere/ionosphere.data F


———————RESULTS————————

Once you run the script from command line, the accuracy arrays and average accuracy will be stored in a .txt file for each dataset.
Each time you run the script, the results will be overwritten with new results.
