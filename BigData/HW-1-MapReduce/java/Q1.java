
	package bigdata.assignment1;
	import java.io.IOException;
	import java.util.*;

	import org.apache.hadoop.fs.Path;
	import org.apache.hadoop.conf.*;
	import org.apache.hadoop.io.*;
	import org.apache.hadoop.mapred.*;
	import org.apache.hadoop.util.*;
	
	public class Q1 {
	
	   public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	     private final static IntWritable one = new IntWritable(1);
	     private Text word = new Text();
	
	     public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
	       String line = value.toString();
	       String[] splitLine = line.split("\\^");
	       if(splitLine.length>1){
	    	   
	    	   if(splitLine[1].contains("Palo Alto"))
	    		   {word.set(splitLine[0]);
	    		   output.collect(word, one);
	    		   }
	     }
	   }
	   }
	
	   public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
		   private final static IntWritable one = new IntWritable(1);
		   public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
	       output.collect(key,one);
	     }
	   }
	
	   public static void main(String[] args) throws Exception {
	     JobConf conf = new JobConf(Q1.class);
	     conf.setJobName("wordcount");
	
	     conf.setOutputKeyClass(Text.class);
	     conf.setOutputValueClass(IntWritable.class);
	
	     conf.setMapperClass(Map.class);
	     //conf.setCombinerClass(Reduce.class);
	     conf.setReducerClass(Reduce.class);
	
	     conf.setInputFormat(TextInputFormat.class);
	     conf.setOutputFormat(TextOutputFormat.class);
	
	     FileInputFormat.setInputPaths(conf, new Path(args[0]));
	     FileOutputFormat.setOutputPath(conf, new Path(args[1]));
	
	     JobClient.runJob(conf);
	   }
	}