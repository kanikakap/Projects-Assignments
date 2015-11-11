package bigdata.assignment1;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * Program to implement ReduceSide Join
 * 
 */
public class Q3 {

	/**
	 * JOB1-Mapper:This class parses the review.csv file and emits all the
	 * businessId along with their rating
	 */
	public static class BusinessRatingMapper extends
			Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String businessData[] = value.toString().split("\\^");
			context.write(new Text(businessData[2]),
					new Text((businessData[3])));

		}
	}

	/**
	 * JOB1-Reducer:This reducer class emits the top ten BusinessId's on average
	 * rating
	 */
	public static class TopTenBusinessReducer extends
			Reducer<Text, Text, Text, Text> {

		private TreeMap<String, Float> map = new TreeMap<>();
		private RatingComparator ratingComparator = new RatingComparator(map);
		private TreeMap<String, Float> sortedMap = new TreeMap<String, Float>(
				ratingComparator);

		@Override
		protected void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {

			float sum = 0;
			float count = 0;
			for (Text value : values) {
				sum += Float.parseFloat(value.toString());
				count++;
			}
			float avg = new Float(sum / count);
			map.put(key.toString(), avg);
		}

		/**
		 * RatingComparator class sorts TreeMap values by average rating.
		 */
		class RatingComparator implements Comparator<String> {

			TreeMap<String, Float> mMap;

			public RatingComparator(TreeMap<String, Float> lMap) {
				this.mMap = lMap;
			}

			public int compare(String a, String b) {
				if (mMap.get(a) >= mMap.get(b)) {
					return -1;
				} else {
					return 1;
				}
			}
		}

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
			sortedMap.putAll(map);

			int count = 10;
			for (Entry<String, Float> entry : sortedMap.entrySet()) {
				if (count == 0) {
					break;
				}
				context.write(new Text(entry.getKey()),
						new Text(String.valueOf(entry.getValue())));
				count--;
			}
		}
	}

	/**
	 * JOB2-Mapper1:This mapper class parses the input from reducer of JOB1 and
	 * emits all businessId's and average rating appended with T1
	 */
	public static class TopTenBusinessRatingMapper extends
			Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String line = value.toString().trim();
			String[] detail = line.split("\t");
			String bId = detail[0].trim();
			String rating = detail[1].trim();
			context.write(new Text(bId), new Text("T1|" + bId + "|" + rating));

		}
	}

	/**
	 * JOB2-Mapper2:This mapper class parses the business.csv file and emits
	 * BusinessId as key and entire tuple as the value
	 */
	public static class BusinessDetailMapper extends
			Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String businessData[] = value.toString().split("\\^");
			context.write(new Text(businessData[0].trim()), new Text("T2|"
					+ businessData[0].trim() + "|" + businessData[1].trim()
					+ "|" + businessData[2].trim()));

		}
	}

	/**
	 * JOB2-Reducer: This reducer class joins the output from the following mappers
	 * 1.TopTenBusinessRatingMapper
	 * 2.BusinessDetailMapper
	 * and emits the entire tuple of business.csv file and average rating.
	 */
	public static class TopTenBusinessDetailReducer extends
			Reducer<Text, Text, Text, Text> {

		private ArrayList<String> topTenBusiness = new ArrayList<String>();
		private ArrayList<String> businessDetails = new ArrayList<String>();
		private static String splitter = "\\|";

		@Override
		protected void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {

			for (Text text : values) {
				String value = text.toString();
				if (value.startsWith("T1")) {
					topTenBusiness.add(value.substring(3));
				} else {
					businessDetails.add(value.substring(3));
				}
			}
		}

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
			for (String topBusiness : topTenBusiness) {
				for (String detail : businessDetails) {
					String[] t1Split = topBusiness.split(splitter);
					String t1BusinessId = t1Split[0].trim();

					String[] t2Split = detail.split(splitter);
					String t2BusinessId = t2Split[0].trim();

					if (t1BusinessId.equals(t2BusinessId)) {
						context.write(new Text(t1BusinessId), new Text(
								t2Split[1] + "\t" + t2Split[2] + "\t"
										+ t1Split[1]));
						break;
					}
				}
			}
		}
	}

	/**
	 * Driver Program
	 * 
	 * @param args
	 *            :Command line arguments
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InterruptedException {

		Configuration config = new Configuration();
		String[] otherArgs = new GenericOptionsParser(config, args)
				.getRemainingArgs();

		if (otherArgs.length != 4) {
			System.err.println("Argument problem");
			System.err
					.println("hadoop jar <jarname> <classname> <input1> <input2> <intermediate_output> <final_output>");
			System.exit(0);
		}

		// Creating new job for businesses
		Job job1 = Job.getInstance(config, "JOB1");
		job1.setJarByClass(Q3.class);
		job1.setMapperClass(BusinessRatingMapper.class);
		job1.setReducerClass(TopTenBusinessReducer.class);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job1, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job1, new Path(otherArgs[2]));

		boolean isJob1Completed = job1.waitForCompletion(true);

		if (isJob1Completed) {
			Configuration config2 = new Configuration();
			Job job2 = Job.getInstance(config2, "JOB2");
			job2.setJarByClass(Q3.class);
			job2.setOutputKeyClass(Text.class);
			job2.setOutputValueClass(Text.class);
			job2.setInputFormatClass(TextInputFormat.class);
			job2.setOutputFormatClass(TextOutputFormat.class);

			// Set multiple Mappers
			MultipleInputs.addInputPath(job2, new Path(args[2]),
					TextInputFormat.class, TopTenBusinessRatingMapper.class);
			MultipleInputs.addInputPath(job2, new Path(args[1]),
					TextInputFormat.class, BusinessDetailMapper.class);

			// Single Reduce Class
			job2.setReducerClass(TopTenBusinessDetailReducer.class);
			FileOutputFormat.setOutputPath(job2, new Path(args[3]));

			job2.waitForCompletion(true);
		}
	}

}
