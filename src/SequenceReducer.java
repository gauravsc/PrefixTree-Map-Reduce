import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import org.apache.mahout.common.Pair;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

 public  class SequenceReducer extends Reducer<Text, Text, Text, LongWritable> {
	 
	 public void reduce(Text key , Iterable<Text> values,Context context) throws IOException , InterruptedException{
		 
		String final_string="";
		List  lines =new ArrayList() ;
		for(Text value: values){
			
	     lines.add(value.toString());
			
		}
		
		
		PrefixTree tree=new PrefixTree(lines);
		HashMap result=tree.getSequence();
		for(Object key_sequence:result.keySet()){
			
			context.write(new Text(key_sequence.toString()),new LongWritable((Long)result.get(key_sequence)));
		}
		 
		 
	 }
}