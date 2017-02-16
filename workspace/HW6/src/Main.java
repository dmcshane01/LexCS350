/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 6
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	November 1, 2016

DESCRIPTION:
Used to test both the BSTFactory method and the reverse method

	*/
public class Main {

	public static void main(String[] args) {
		
		System.out.println("Binary Searc Tree depth: 5");
		BinaryNode test = BinaryNode.BSTFactory(5, 32);
		test.inOrderPrint();
		System.out.println("\nBinary Search Tree depth: 4");
		test = BinaryNode.BSTFactory(4, 16);
		test.inOrderPrint();
		System.out.println("\nBinary Search Tree depth: 3");
		test = BinaryNode.BSTFactory(3, 8);
		test.inOrderPrint();
		System.out.println("\nBinary Search Tree depth: 2");
		test = BinaryNode.BSTFactory(2, 4);
		test.inOrderPrint();
		System.out.println("\nBinary Search Tree depth: 1");
		test = BinaryNode.BSTFactory(1, 2);
		test.inOrderPrint();
		System.out.println("\nBinary Search Tree depth: 0");
		test = BinaryNode.BSTFactory(0, 1);
		test.inOrderPrint();
		
		//List Length 1
		System.out.println( "Performing reverse on a list with length 0 doesn't do anything because it causes null pointer reference");
		SimpleNode head = new SimpleNode(1, null);
		System.out.println( "Output of list length 1: \n" + head.toString());
		head = SimpleNode.reverse(head);
		System.out.println("Output of list length 1 after reverse: \n" + head.toString());
		
		//List Length: 5
		head = new SimpleNode(5, null);
		SimpleNode temp = head;
		for(int i = 4; i > 0; i--)
		{
			
			temp = temp.addAfter(i);
		}
		System.out.println( "Output of list length 5: \n" + head.toString());
		head = SimpleNode.reverse(head);
		System.out.println("Output of list length 5 after reverse: \n" + head.toString());
		temp = head;
		
		//List length: 10
		head = new SimpleNode(10, null);
		temp = head;
		for(int i = 9; i > 0; i--)
		{
			
			temp = temp.addAfter(i);
		}
		
		System.out.println( "Output of list length 10: \n" + head.toString());
		head = SimpleNode.reverse(head);
		System.out.println("Output of list length 10 after reverse: \n" + head.toString());
		temp = head;
		
		

	}

}
