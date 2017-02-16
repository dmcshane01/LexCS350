/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 6
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	October 20, 2016

DESCRIPTION:
LinkedStack implements the Stack data structure using a linked list

CLASS FIELDS:
	Top:
		Is a generic node and represents the top of the stack
	*/

import java.util.EmptyStackException;


public class LinkedStack<T> {

	Node<T> top;
	
	/*
	 * LinkedStack is a simple stack structure
	 * Everytime data is added to the stack, it is added to the top
	 * Sets the link of new data to the old top
	 * Can only remove the top of stack
	 */

	public LinkedStack()
	{
		this.top = null;
	}
	
	//precondition: Data must have same type as LinkedStack
	//postcondition: data added is now top of stack
	public void push(T data)
	{
		if(top == null)
		{
			top = new Node<T>(data,null);
			return;// top;
		}
		
		top = new Node<T>(data, top);
		//return top;
	}
	
	
	
	//postcondition: returns value of top of stack
	public T peek()
	{
		return top.getData();
	}
	
	//precondition: top must be non-null
	//postcondition: The top of the stack is removed and returned
	//throws: NullPointerException when top is null
	public Node<T> pop()
	{
		if(top == null){throw new EmptyStackException();}
		
		Node<T> temp = top;
		top = top.getLink();
		return temp;
		
	}
	
	//Postcondition: returns boolean value indicating if stack is empty
	public boolean isEmpty()
	{
		if(top == null)
		{
			return true;
		}
		else
			return false;
	}
	
	//Simply calls the toString for the top Node
	public String toString()
	{
		return top.toString();
	}
	
	/*
	 * DESIGN:
	 * 
	 * The design for the LinkedStack mimics the example of the Stack in the book
	 * Not much variation was needed for this application
	 */
}
