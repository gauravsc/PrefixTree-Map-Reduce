import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
        
public class SequenceCount {
	

	
	
	public static void main(String args[]) throws Exception{
		Configuration conf= new Configuration();
		Job job = new Job(conf,"sequencecount");
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
	    job.setInputFormatClass(TextInputFormat.class);
	    job.setOutputFormatClass(TextOutputFormat.class);
	    job.setMapperClass(SequenceMapper.class);
	    job.setReducerClass(SequenceReducer.class);
	    FileInputFormat.addInputPath(job, new Path("input"));
	    FileOutputFormat.setOutputPath(job, new Path("output"));
        job.waitForCompletion(true);
	    

		
		
	}
	

}
