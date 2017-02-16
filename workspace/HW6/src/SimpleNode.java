/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 6
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	November 1, 2016

DESCRIPTION:
SimpleNode is a watered down version of the Node class used exlusively to test the recursive Reverse method

CLASS METHODS:
	Reverse()
		Reverse takes a node as a parameter and reverses the list of the given head node.
	*/
public class SimpleNode {
	
	
	private Integer data;
	private SimpleNode link;
	
	public SimpleNode(Integer data, SimpleNode link)
	{
		this.data = data;
		this.link = link;
	}//end constructor
	
	public SimpleNode getTail(SimpleNode head)
	{
		while(head.link != null)
		{
			head = head.link;
		}
		return head;
	}
	
	public SimpleNode addAfter(int value)
	{
		link = new SimpleNode(value, null);
		return link;
	}
	
	public static SimpleNode reverse(SimpleNode head)
	{
		//base case, head is end of list
		if(head==null || head.link == null)
		{
	        return head;
		}
		
		//set tail to tail of head
		SimpleNode tail = head.getTail(head);
		SimpleNode second = head; //to get to the second to last node in list
		
		//loop until second is before the tail
		while(second.link != tail)
		{
			second = second.link;
		}
		
		second.link = null; //remove the tail from the list
		tail.setLink(reverse(head)); // set link of the tail to the head of reduced list
		
		return tail; //return tail, which will be head on last iteration
		
	}
	
	public void setLink(SimpleNode a)
	{
		this.link = a;
	}
	
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
	
	

	
}
