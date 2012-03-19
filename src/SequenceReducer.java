import java.util.List;
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
		List<Pair<String,Long>> result=tree.getSequence();
		System.out.println("key:"+key.toString()+"value: "+result.toString());
		for(Pair<String,Long> sequences:result){
			
			context.write(new Text(sequences.getFirst()),new LongWritable(sequences.getSecond()));
		}
		 
		 
	 }
}