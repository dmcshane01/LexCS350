/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 6
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	November 1, 2016

DESCRIPTION:
BinaryNode is a simple representation of a tree node

CLASS METHODS:
	BSTFactory()
		Given a depth and value, BSTfactory uses recursion to make a Binary Search Tree. Returns the root of the new full Binary Search Tree
	*/

public class BinaryNode {

	int data;
	BinaryNode left;
	BinaryNode right;
	
	public BinaryNode(int initialData, BinaryNode left, BinaryNode right)
	{
		this.data = initialData;
		this.left = left;
		this.right = right;
	}

	public static BinaryNode BSTFactory(int depth, int top)
	{
		BinaryNode leftChild;
		BinaryNode rightChild;
		
		//base case
		if(depth == 0)
		{
			return new BinaryNode(top, null,null);
		}
		else
		{
			//subtract 1 from depth each call, and the top value is the root node of the subtree's value + or - 2^depth -1.
			leftChild = BSTFactory(depth - 1, top - (int) Math.pow(2, depth - 1));
			rightChild = BSTFactory(depth - 1, top + (int) Math.pow(2, depth - 1));
			return new BinaryNode(top, leftChild, rightChild);
		}
		
	}
	
	//used to output the contents of the tree in ascending order
	public void inOrderPrint()
	{
		if(left != null)
		{
			left.inOrderPrint();
		}
		System.out.println(data);
		if(right != null)
		{
			right.inOrderPrint();
		}
		
		
	}
	

}
