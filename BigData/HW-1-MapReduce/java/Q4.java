package bigdata.assignment1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Q4 {

   public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
	   
	   public HashSet<String> filteredBusinessId = new HashSet<>(); 
	   
	   protected void setup(Context context) throws IOException, InterruptedException {
			//super.setup(context);
			//read data to memory on the mapper.
			//myCenterList = new ArrayList<>();
			Configuration conf = context.getConfiguration();
			String myfilepath = conf.get("myfilepath");
			//e.g /user/hue/input/
			Path part=new Path("hdfs://cshadoop1"+myfilepath);//Location of file in HDFS
			//loadHashSet(part);
			
			FileSystem fs = FileSystem.get(conf);
			//filteredBusinessId.add("cWUiqqkS4cpi_StZWkk-Qw");
			FileStatus[] fss = fs.listStatus(part);
		    for (FileStatus status : fss) {
		        Path pt = status.getPath();
		        BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
		        String line;
		        line=br.readLine();
		        while (line != null){
		        	String[] splitLine = line.split("\\^");
		 	       	if(splitLine.length>3){
		 	    	   
		 	    	   if(splitLine[1].contains("Stanford"))
		 	    		   {
		 	    		   	filteredBusinessId.add(splitLine[0]);
		 	    		   }
		 	       	}
		            line=br.readLine();
		        }
		       
		    }
			
			
			
	       
	    }
	  
	 private final static IntWritable one = new IntWritable(1);
     private Text word = new Text();

     public void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
       String line = value.toString();
       String[] splitLine = line.split("\\^");
       if(splitLine.length>1){
    	   
    	   //review.csv
    	   if(filteredBusinessId.contains(splitLine[2]))
    		   {
    		   word.set(splitLine[1]+":"+splitLine[3]);
    		   context.write(word, one);
    		   }
     }
   }
   }

   public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
	   private final static IntWritable one = new IntWritable(1);
	   public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
       context.write(key,one);
     }
   }

   public static void main(String[] args) throws Exception {
	 Configuration conf = new Configuration();
	 conf.set("myfilepath",args[1]);
	 Job job = new Job(conf, "JoinYelp");
	 job.setJarByClass(Q4.class);
	 job.setMapperClass(Map.class);
	 job.setReducerClass(Reduce.class);
	  // set output key type
	 job.setOutputKeyClass(Text.class);
	 // set output value type
	 job.setOutputValueClass(IntWritable.class);
	 //set the HDFS path of the input data
	 FileInputFormat.addInputPath(job, new Path(args[0]));
	 // set the HDFS path for the output
	 FileOutputFormat.setOutputPath(job, new Path(args[2]));
	 //Wait till job completion
	job.waitForCompletion(true);
   }
}