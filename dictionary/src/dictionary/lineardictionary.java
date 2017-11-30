package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class lineardictionary {
	public static void main(String args[]) throws IOException
	{
		long startTime = System.currentTimeMillis();

	 
		
		System.out.println("enter vocav.txt file location");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		File file1= new File(br.readLine());
		
		
		System.out.println("enter sentence.txt file location");
		File file2= new File(br.readLine());
		
		
		System.out.println("enter MaxDistance.txt file location");
		File file3= new File(br.readLine());
		System.out.println("enter MissSpelled.txt file location i.e. where you want to save you output file");
		String file4= new String(br.readLine());
		File statText = new File(file4);
		 FileOutputStream is = new FileOutputStream(statText);
	     OutputStreamWriter osw = new OutputStreamWriter(is);    
	     Writer w = new BufferedWriter(osw);
		 BufferedReader filereader = new BufferedReader(new FileReader(file3));
		int maxdistance=Integer.parseInt( filereader.readLine());
	
		System.out.println(maxdistance);
		filereader = new BufferedReader(new FileReader(file2));
		 String st= null;
		 String vocab=null;
		 int count=0;
		
		while((st=filereader.readLine()) != null){
			

			StringTokenizer sentence=new StringTokenizer(st);
			while(sentence.hasMoreTokens())
			{	 
				String a=sentence.nextToken();
				count++;
				 BufferedReader vocabreader = new BufferedReader(new FileReader(file1));
				 String nearwords="";
				 ArrayList<Integer> num=new ArrayList<>();
				 while((vocab=vocabreader.readLine())!=null)
				 {
					 int dist=distance(a,vocab);
					 if(dist<=maxdistance)
					 {
						 nearwords=nearwords+" "+vocab+", ";
						 num.add(dist);
									
						 }
					 if(dist==0){
						 break;
					 }
					
					 }
				
				 boolean value = false;
				 for(int i=0;i<num.size();i++)
					  
				 {
					 if(num.get(i).equals(0))
					 {
						 count--;
						value=true;
					 }
				 }
				 if (value==false)
				 {	  
					 if(nearwords!=null)  
				 {
				        w.write(a+":"+nearwords.substring(0, nearwords.length())+"\n");
				        w.append(System.lineSeparator());
				 }
				        				
				 }
			}
		}
		if(count==0){
			
			w.write("0");
	
		}
		 w.close();
		long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
		
		
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
}

