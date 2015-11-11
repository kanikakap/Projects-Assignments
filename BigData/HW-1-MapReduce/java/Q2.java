
	package bigdata.assignment1;
	import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

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

import bigdata.assignment1.Q4.Map;
import bigdata.assignment1.Q4.Reduce;


	public class Q2 {
	
	   public static class Map extends Mapper<LongWritable, Text, Text, FloatWritable>  {
	     private final static IntWritable one = new IntWritable(1);
	     private Text word = new Text();
	
	     public void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
	       String line = value.toString();
	       String[] splitLine = line.split("\\^");
	       if(splitLine.length==4){
	    	   	   word.set(splitLine[2]);
	    		   context.write(word, new FloatWritable((float) Double.parseDouble(splitLine[3])));
	     }
	   }
	   }
	
	   public static class Reduce extends Reducer<Text, FloatWritable, Text, NullWritable> {
		   private String word;
		   TreeMap<Float, LinkedList<Text>> topTenBusiness = new TreeMap< Float, LinkedList<Text>>();
		   Comparator<Rating> comparator = new RatingComparator();
	       PriorityQueue<Rating> queue = 
	            new PriorityQueue<Rating>(10, comparator);
		   public void reduce(Text key, Iterable<FloatWritable> values,Context context) throws IOException, InterruptedException {
	        float sum = (float) 0.0;
	        int noOfRatings = 0;
	        word = key.toString();
	        Iterator itr = values.iterator();
			   while(itr.hasNext()){
	        	sum +=((FloatWritable) itr.next()).get();
	        	noOfRatings+=1;
	        }
			 float average=  (float)(sum/noOfRatings);
			if(topTenBusiness.containsKey(average)){
				LinkedList<Text> temp = topTenBusiness.get(average);
				temp.add(new Text(word+" "+average));
				topTenBusiness.put(average, temp);
			}
			else
			{
			LinkedList<Text> temp = new LinkedList<Text>(Arrays.asList(new Text(word+" "+average)));
			topTenBusiness.put(average,temp);
			}
			if(topTenBusiness.size()>10)
			{
				topTenBusiness.remove(topTenBusiness.firstKey());
			}
	     }
		   @Override
			protected void cleanup(Context context) throws IOException,
			    InterruptedException {
		        int N = 1;  
		       for(LinkedList<Text> val : topTenBusiness.descendingMap().values() )
		       {
		    	   
		    	  for(Text value : val)
		       {
		    		  if(N<11)
		    		  {
		    			  context.write(value, NullWritable.get());
		    			  N++;
		    		  }
		    		  else {
		    			  break;
		    		  }
		       }
		    	  if(N==11)
		    	  {
		    		  break;
		    	  }
			}
		   }
	   }	
	
	   public static void main(String[] args) throws Exception {
		   Configuration conf = new Configuration();
			 Job job = new Job(conf, "JoinYelp");
			 job.setJarByClass(Q2.class);
			 job.setMapperClass(Map.class);
			 job.setReducerClass(Reduce.class);
			  // set output key type
			 job.setOutputKeyClass(Text.class);
			 // set output value type
			 job.setOutputValueClass(FloatWritable.class);
			 //set the HDFS path of the input data
			 FileInputFormat.addInputPath(job, new Path(args[0]));
			 // set the HDFS path for the output
			 FileOutputFormat.setOutputPath(job, new Path(args[1]));
			 //Wait till job completion
			job.waitForCompletion(true);
	   }
	}
