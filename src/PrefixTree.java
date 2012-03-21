import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.mahout.common.Pair;
public class PrefixTree {
	HashMap prefix_tree= new HashMap();
	Long count=1L;
	
	public PrefixTree(){
		
	}
	
	public PrefixTree(List lines){
		String line;
		/* add items into the tree and build the tree*/
		for(Object line_object: lines){
			line=line_object.toString();
			String []elements= line.split(",");
			addIntoTree(elements);
			
		}			
		
	}
	
	
	public void addIntoTree(String[] elements){
		PrefixTree ptr=this;
		for(String element: elements){
			ptr=addIntoTreeElement(element,ptr);
			
		}
		
		
	}
	
	public PrefixTree addIntoTreeElement(String element,PrefixTree ptr){
		
			if(ptr.prefix_tree.containsKey(element)){
				System.out.println("key found");
				ptr=(PrefixTree)ptr.prefix_tree.get(element);
				ptr.count++;

				
				
			}else{
				
				PrefixTree temp_created= new PrefixTree();
				ptr.prefix_tree.put(element, temp_created);
				ptr=temp_created;
				ptr.count=1L;


			}
			
		

		return ptr;
	}
	public void addToResult(HashMap result, List<Pair<String, Long>> stack){
		String temp_string="";
		int counter=0;
		for(Pair<String, Long> element:stack){
			if(counter==0){
				temp_string=element.getFirst();
				result.put(temp_string,(Long)element.getSecond());
				counter++;
				continue;
			}
			temp_string=temp_string+","+element.getFirst();
			result.put(temp_string,(Long)element.getSecond());

		}
		
		
	}
	public void dfs(HashMap result,List stack, PrefixTree ptr){
		if(ptr.prefix_tree.size()<1  && ptr!=this){
			
			addToResult(result,stack);

		}
		for(Object key : ptr.prefix_tree.keySet()){
			stack.add(new Pair((String)key,((PrefixTree)ptr.prefix_tree.get(key)).count));
			dfs(result,stack,(PrefixTree)ptr.prefix_tree.get(key));
			stack.remove(stack.size()-1);
			
		}
		
	}
	
	public  HashMap getSequence(){
		
		List<Pair<String, Long>> stack=new ArrayList();
		HashMap result = new HashMap();
        dfs(result,stack,this);
		return result;
	}
	
	public static void main(String args[]){
		List <String>lines= new ArrayList<String>();
		lines.add("a,b,c");
		lines.add("a,b,d");
		lines.add("f,g,h");
		PrefixTree tree=new PrefixTree(lines);
		HashMap result=tree.getSequence();
		
	}
	
	
	

}
