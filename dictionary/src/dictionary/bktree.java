package dictionary;



public class bktree {
node root=null;
int maxdistance;

public bktree(int maxdistance)
{
	this.maxdistance=maxdistance;
	}
public void add(String word)
	{
	
	if(word==null||word.isEmpty())
	{
		System.out.println("empty");
		throw new IllegalArgumentException("word can't be null or empty.");
	}
	
	
	if(root==null)
	{	root=new node("flowchart");
		
			}
		else
		{//System.out.println("root word is "+root.word);
			node n= new node(word);
			addinternal(this.root,n);
		}
		
	}
private void addinternal(node src, node n) {
	
	int distance1=node.distance(src.word, n.word);
	
	node a=src.getchildern(distance1);
	
	if(a==null)
	{	
		src.addchildern(distance1, n);
	}
	else{
		
		addinternal(a,n);
	}
	
}
public String search(String name) throws InterruptedException
	{int a=0;
	
	String matchingword=root.search(maxdistance,name,root,a);
		return matchingword;
	
	}
}
