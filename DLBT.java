
import java.io.*;
import java.util.*;
public class DLBT {
	
	//instance variables
	private Node root;
	private int count;
	public int words_printed;
	//constructor
	public DLBT()
	{
		root=new Node(' ');
		count=0;
		words_printed = 0;
	}
	
	public Node getRoot()
	{
		return root;
	}
	public int getCount(){
		
		return count;
	}
	public void add(String s)
	{
		Node currentNode = root;
		int length=s.length();
		if(currentNode.getChild()==null)
		{
			Node n = new Node();
			n.setData(s.charAt(0));
			currentNode.setChild(n);
		}
		
		
		for(int i=0; i<s.length(); i++)//at the correct character from last iteration
		{
			if(currentNode.getChild()==null)
			{
				Node n = new Node(s.charAt(i));
				currentNode.setChild(n);
				currentNode=currentNode.getChild();
			}
			else{//has a child
				currentNode=currentNode.getChild();
				if(currentNode.getData()==s.charAt(i))
				{
					continue;
				}
				else{
					while(currentNode.getNext()!=null)
					{
						if(currentNode.getData()==s.charAt(i))
							break;
						currentNode=currentNode.getNext();
					}
						if(currentNode.getData()!=s.charAt(i))
						{
							Node n = new Node(s.charAt(i));
							currentNode.setNext(n);
							currentNode=currentNode.getNext();
						}	
				}
			}
}
currentNode.setWord(true);
count++;
	}

//precondition: currentNode should be root
public void to_string(String s, Node currentNode, String filename, BufferedWriter bw)throws Exception
{
	if(currentNode.getWord())
	{
		bw.write(s+currentNode.getData());
		bw.newLine();
	}
	if(currentNode.getChild()!=null)
	{
		to_string(s+currentNode.getData(), currentNode.getChild(), filename, bw);
	}
	if(currentNode.getNext()!=null)
	{
		to_string(s, currentNode.getNext(), filename, bw);
	}
	
}
public void print_ten(String s, Node currentNode)
{
	if(currentNode.getWord()&&words_printed!=20)
	{
		if(words_printed==10){
			words_printed=20;
			return;
		}
		System.out.println(s+currentNode.getData());
		words_printed++;
	}
	if(currentNode.getChild()!=null&&words_printed!=20)
	{
		print_ten(s+currentNode.getData(), currentNode.getChild());
	}
	if(currentNode.getNext()!=null&&words_printed!=20)
	{
		print_ten(s, currentNode.getNext());
	}
}
public void resetCounter()
{
	words_printed=0;
}

public void addNumRepl(String s)
{
	for(int i=0; i<s.length(); i++)
	{
		if(s.charAt(i)=='t')
		{
			add(s);
			if(i!=s.length()-1)
			{
			addNumRepl(s.substring(0,i)+'7'+s.substring(i+1));
			}
			else{
				addNumRepl(s.substring(0,i)+'7');
			}
		}
		else if(s.charAt(i)=='l')
		{
			add(s);
			if(i!=s.length()-1)
			{
				addNumRepl(s.substring(0,i)+'1'+s.substring(i+1));
			}
			else
			{
				addNumRepl(s.substring(0,i)+'1');
			}
		}
		else if(s.charAt(i)=='i')
		{
			if(i!=s.length()-1)
			{
				addNumRepl(s.substring(0,i)+'1'+s.substring(i+1));
				
			}
			else{
				addNumRepl(s.substring(0,i)+'1');
			}
		}
		else if(s.charAt(i)=='a')
		{
			add(s);
			if(i!=s.length()-1)
			{
				addNumRepl(s.substring(0,i)+'4'+s.substring(i+1));
			}
			else{
			addNumRepl(s.substring(0,i)+'4');
			}
			
		}
		else if(s.charAt(i)=='e')
		{
			add(s);
			if(i!=s.length()-1)
			{
				addNumRepl(s.substring(0,i)+'3'+s.substring(i+1));
			}
			else
			{
				addNumRepl(s.substring(0,i)+'3');
			}
			
		}
		else if(s.charAt(i)=='s')
		{
			add(s);
			if(i!=s.length()-1)
			{
				addNumRepl(s.substring(0,i)+'5'+s.substring(i+1));
				
			}
			else{
				addNumRepl(s.substring(0,i)+'5');
			}
		}
		else if(s.charAt(i)=='o')
		{
			add(s);
			if(i!=s.length()-1)
			{
				addNumRepl(s.substring(0,i)+'0'+s.substring(i+1));
			}
			else{
				addNumRepl(s.substring(0,i)+'0');
			}
		}
		else{
			add(s);
		}
		
	}
}

public boolean contains(String s)
{
	Node currentNode=getRoot();
	if(currentNode.getChild()!=null)
	{
		currentNode=currentNode.getChild();
	}
	else{
		return false;//no child from root so word does not exist
	}
	for(int i=0; i<s.length(); i++)
	{
		
		while(currentNode.getData()!=s.charAt(i))
		{
			if(currentNode.getNext()!=null)
			{
				currentNode=currentNode.getNext();
			}
			else{
				return false;//out of next nodes, character not found
			}
			
		}
		if(currentNode.getWord()&&i==s.length()-1)
		{
			return true;
		}
		if(currentNode.getChild()!=null&&i!=s.length()-1)
		{
			currentNode=currentNode.getChild();
		}
	}
	
	if(currentNode.getWord())
	{
		
	return true;
	}
else{
	return false;
}	
	}
		
}








