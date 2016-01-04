
public class Node {

	
	private char data;
	private Node child;
	private Node next;
	private boolean is_word;
	
	public Node() {
		data=' ';
		is_word=false;
		child=null;
		next=null;
	}
	public Node(char c, Node ch, Node n, boolean f){
		data=c;
		child=ch;
		next=n;
		is_word=f;
	}
	public Node(char c){
		data=c;
		child=null;
		next=null;
		is_word=false;
	}
	public void setData(char c)
	{
		data=c;
	}
	public void setNext(Node n)
	{
		next=n;
	}
	public void setChild(Node c)
	{
		child=c;
	}
	public void setWord(boolean b)
	{
		is_word=b;
	}
	public Node getChild()
	{
		return this.child;
	}
	public Node getNext()
	{
		return this.next;
	}
	public boolean getWord(){
		return this.is_word;
	}
	public char getData()
	{
		return this.data;
	}
	
	
}
