/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 5
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	October 20, 2016

DESCRIPTION:
Node is a generic ADT that is used to create a linked list

CLASS FIELDS:
	data
		stores an object in the node
	link
		contains a reference to the next node in linked list
	*/
public class Node <T> {

	
	private T data;
	private Node<T> link;
	
	//constructor initializes node and link(both can be null)
	public Node(T data, Node<T> link)
	{
		this.data = data;
		this.link = link;
	
	}//end constructor
	
	//creates a Node that is referenced by the calling node and returns a reference to new node
	public Node<T> addNodeAfter(T element)
	{
		link = new Node<T>(element, link);
		return link;
	}//end addNodeAfter
	
	public Node<T> addNodeBefore(T element)
	{
		Node<T> newNode = new Node<T>(element, this);
		return newNode;
	}
	
	//recursively calls itself to print out all the objects in the linked list
	public String toString()
	{
		String f1 = "";
		String f2 = "";
		if(data == null)
		{
			f1 = "Dummy";
		}
		else
			f1 = data.toString();
		
		if(link == null)
		{
			f2 = "Null in tail";
		}
		else
			f2 = link.toString();
			
		//returns string that contains info about data and link
		return "data: " + f1 + "\n link: " + f2 ;
	}//end toString()
	
	/*
	 * precondition: Must have a node reference in link
	 * postcondition: THe linked node is removed from list and the reference is to the node after
	 * throws: NullPointerException
	 */
	public void removeNodeAfter()
	{
		if(link == null) {throw new NullPointerException("No Node to remove");}
	
		link = link.link;
	}
	
	public void setLink(Node<T> toAdd)
	{
		this.link = toAdd;
	}
	
	public Node<T> getLink()
	{
		return link;
	}
	
	public T getData()
	{
		return data;
	}
	
	//preconditon: None because this is static and can handle null parameters
	//postcondition: returns the head of a copy of the Linked List
	//throws: Can throw OutOfMemoryException
	public static<T> Node<T> listCopy(Node<T> source)
	{
		Node<T> copyHead;
		Node<T> copyTail;
		
		if(source.equals(null)) return null;
		
		copyHead  = new Node<T>(source.data, null);
		copyTail = copyHead;
		
		while(source.link != null)
		{
			source = source.link; //move source to it's reference because copyhead is equal to source
			copyTail = copyTail.addNodeAfter(source.data);	
		}
		
		return copyHead;
	}
	
	public static <T> Node<T> listPosition(Node<T> head, int position)
	   {
	      Node<T> cursor;
	      int i;
	      
	      if (position == 0)
	           throw new IllegalArgumentException("position is zero");
	      
	      cursor = head;
	      for (i = 1; (i < position) && (cursor != null); i++)
	         cursor = cursor.link;

	      return cursor;
	   }
	
	 public static <T> int listLength(Node<T> head)
	   {
	      Node<T> cursor;
	      int answer;
	      
	      answer = 0;
	      for (cursor = head; cursor != null; cursor = cursor.link)
	         answer++;
	        
	      return answer;
	   }
	 
	 public static <T> Node<T> getTail(Node<T> source)
	 {
		 if(source.link == null){return source;}
		 
		 while(source.link != null)
		 {
			 source = source.link;
		 }
		 
		 return source;
	 }	 
	 
}
