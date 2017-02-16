/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 4
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	October 3, 2016(sample)

DESCRIPTION:
NodeApplication houses the main method for the program. It builds a linked list and
outputs figures 1 - 3

CLASS FIELDS:
	head
		references start of linked list
	tail
		References the end of linked list
			*/

public class NodeApplication {

	public static void main(String[] args) {
		
		//Figure 1 output
		Node<String> head= new Node("Paul", null);
		Node<String> tail = head.addNodeAfter("Saul");
		System.out.println("Figure 1: \n" + head.toString());
		
		//Figure 2 output
		tail = head.addNodeAfter("mauls");
		tail = tail.getLink();
		tail = tail.addNodeAfter("Saul");
		tail = tail.addNodeAfter("mauls");
		tail = tail.addNodeAfter("Raul");
		System.out.println("\nFigure 2: \n" + head.toString());
		
		//figure 3 output
		Node<String> dummy = new Node(null, head);
		head = dummy.addNodeAfter("Raul");
		head = head.addNodeAfter("mauls");
		head = head.addNodeAfter("Paul");
		System.out.println("\nFigure 3: \n" + dummy.toString());

	}//end main

}//end class
