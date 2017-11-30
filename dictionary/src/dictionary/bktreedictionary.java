package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.StringTokenizer;

public class bktreedictionary {
	public static void main(String args[]) throws IOException, InterruptedException
	{	 long startTime = System.currentTimeMillis();

		
			
			System.out.println("enter vocav.txt file location");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			File file1= new File(br.readLine());
			
			
			System.out.println("enter sentence.txt file location");
			File file2= new File(br.readLine());
			
			
			System.out.println("enter MaxDistance.txt file location");
			File file3= new File(br.readLine());
			
			System.out.println("enter MissSpelledwords.txt file location Where the output has to be saved");
			String file4= new String(br.readLine());
			 File statText = new File(file4);
			 FileOutputStream is = new FileOutputStream(statText);
		     OutputStreamWriter osw = new OutputStreamWriter(is);    
		     Writer w = new BufferedWriter(osw);
			
			 BufferedReader filereader = new BufferedReader(new FileReader(file3));
			int maxdistance=Integer.parseInt( filereader.readLine());
			
			 System.out.println(maxdistance);
			filereader = new BufferedReader(new FileReader(file1));
			 String st= null;
			
			 int count=0;
			
			 String nearwords="";
			 bktree b=new bktree(maxdistance);
			 
			while((st=filereader.readLine())!=null)
			 {
			 	
				b.add(st);
			 }
			
			BufferedReader vocabreader = new BufferedReader(new FileReader(file2));
			 String a="";
				while(( a=vocabreader.readLine())!=null)
				{
					StringTokenizer sentence=new StringTokenizer(a);
					while(sentence.hasMoreTokens())
					{
						String d=sentence.nextToken();
						 nearwords=b.search(d);
						 count++;
						
						
						 if(nearwords!="")
						 {
						 
							if(nearwords==null){
								w.write(d+":");
								w.append(System.lineSeparator());
							}
							else{
							
						 w.write(d+":"+nearwords.substring(5, nearwords.length()-2));
						 w.append(System.lineSeparator());
						 }
						 }
						 if(nearwords=="")
						 {
							 count--;
						 }
			
					}
					
					if(count==0)
					{
						w.write("0");
						
					}
					}
				w.close();
				long stopTime = System.currentTimeMillis();
			    long elapsedTime = stopTime - startTime;
			    System.out.println(elapsedTime);
			    
				}
			
	}

