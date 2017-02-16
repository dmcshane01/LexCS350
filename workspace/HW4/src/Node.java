/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 4
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	October 3, 2016(sample)

DESCRIPTION:
Node is a generic ADT that is used to create a linked list

CLASS FIELDS:
	data
		stores an object in the node
	link
		contains a reference to the next node in linked list
	*/

public  class  Node <T> {
	/*
	 * The ADT for Node is that link will always be a reference to the next
	 * node in the list, unless it is the end then it will be null
	 * If data is null in node then toString will print dummy for data
	 * if link is null toString prints that it's null to console
	 */
	
	
	//initialize class fields
	private T data;
	private Node link;
	
	public Node(T data, Node link)
	{
		this.data = data;
		this.link = link;
	}//end constructor
	
	//Assigns a new node to the objects link field, creating another node in linkedlist
	//returns reference to new node
	public Node addNodeAfter(T data)
	{
		link = new Node(data, link); //sets link of this node to a new node with data
		return link; //returns reference to next node
	}//end addNodeAfter
	
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
		
		return "data: " + f1 + "\n link: " + f2 ;
	}//end toString()
	
	//returns link
	public Node getLink()
	{
		return this.link;
	}//end getLink()
	
}//end class
