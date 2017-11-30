package dictionary;

import java.util.HashMap;
import java.util.Set;

public class node {
	String word=null;
	HashMap<Integer, node> childern;
	public node(String word)
	{
		this.word=word;
		this.childern=new HashMap<Integer,node>();
	}
	public  node getchildern(int distance)
	{		
		return childern.get(distance);
	}
	public void addchildern(int distance,node node)
	{
		this.childern.put(distance, node);
	}
	int count=0;
	String nearwords=null;
	Boolean value;
	public String search(int maxdistance,String word,node n,int a) throws InterruptedException
	{	
		if(a==0){
			 value=false;
			 nearwords=null;
		}
		if(value==true)
			return "";
		count++;
		
		int distance=distance(word,n.word);
		
		
		if(distance==0)
		{
			nearwords="";
			value=true;
			return nearwords;
		}
		
		
		
		
		if(distance<=maxdistance)
			{
			nearwords=nearwords+" "+n.word+", ";
			}
		
		if (n.childern.size() == 0)
		{
            return nearwords;
		}
		//int i=Math.max(1,distance-maxdistance);
		int max=distance+maxdistance;
		int min=Math.max(1,distance-maxdistance);
		
	
		for(Integer i:n.childern.keySet())
		{	
			if(i<=max&&i>=min){
			node child=n.childern.get(i);
			
			search(maxdistance,word,child,2);
			}
			
			
		}
		return nearwords;
	
	}
	
	public static int distance(String a, String b) {
		
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        
        return costs[b.length()];
    }
	public String getword(node n)
	{
		return n.word;
	}
}
