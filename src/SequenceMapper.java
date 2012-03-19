import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


 public class SequenceMapper extends Mapper<LongWritable, Text, Text, Text> {
	 
	 public void map (LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		 String file_data = value.toString();
		 String[] lines= file_data.split("\n");
		 
		 for(String line : lines){
		 String[] elements=line.split(",");
         Text OutputKey= new Text();
         OutputKey.set(elements[0]+","+elements[1]);
         Text OutputValue =new Text();
         OutputValue.set(line);
         context.write(OutputKey,OutputValue);
		 }
		 
	 }

}