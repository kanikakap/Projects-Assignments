
Name : Kanika Kapoor
Title - Assignment - 1 Big Data
Date of Submission - Sept 27, 2015

Create 3 input folders: inputB, inputR and inputU. 
Put business.csv in inputB, review.csv in inputR and user.csv in inputU directory.

Make input directory:
hdfs dfs -mkdir inputB

Put users.dat in input directory:
hdfs dfs -put business.csv inputB

Make input directory:
hdfs dfs -mkdir inputR  

Put users.dat in input directory:
hdfs dfs -put review.csv inputR

Make input directory:
hdfs dfs -mkdir inputU   

Put users.dat in input directory:
hdfs dfs -put user.csv inputU

Package name : kxk140230_Assg1

————————————————————
Part A:
Run the jar file:
hadoop jar PartA.jar kxk140230_Assg1.kxk140230_PartA /kxk140230/inputB /kxk140230/output1

View the output as:
hdfs dfs -cat /kxk140230/output1/*
————————————————————

Part B:
Run the jar file:
hadoop jar PartB.jar kxk140230_Assg1.kxk140230_PartB /kxk140230/inputR /kxk140230/output2

View the result as:
hdfs dfs -cat /kxk140230/output2/*

————————————————————

Part C:
Run the jar file:
hadoop jar PartC.jar kxk140230_Assg1.kxk140230_PartC /kxk140230/inputR /kxk140230/output3

————————————————————
Part D:
Run the jar file:
hadoop jar PartD.jar kxk140230_Assg1.kxk140230_PartD /kxk140230/inputB /kxk140230/output4

View the result as:
hdfs dfs -cat /kxk140230/output4/part-r-00000
————————————————————
