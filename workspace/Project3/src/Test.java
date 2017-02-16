/*
NAME:		Daniel DelyMcShane
PROJECT:	Project 3
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	December 12, 2016

DESCRIPTION:
	Class Test is used to test the sorting implementations. To use it you must
	change the size of the set in the main method. Even if you are using a list, change the array
	size because that is where the RandomData() method determines how many elements to create
	
	The testSort() method is where the sorting methods are called. Because I want to avoid 
	sorting everything at the same time, you must change which sorting implementation you are using
	in the method
	*/

import java.util.Random;


public class Test {

	static int[] data;
	static Node head;
	static int TOP = 12000000;
	
	public static void main(String[] args) {
		
	     //must change data set size here, even if using a list method
		 data = new int[320000];
		 randomData(data.length);
		 testSort();
		 
		

	}
	
	//assign random values to list and array simultaneously
	public static void randomData(int len)
	{
		Random r = new Random();
		head = new Node(r.nextInt(TOP), null); //create dummy node, head is given a value in for loop
		Node temp = head;
		int item = r.nextInt(TOP);
		for(int i = 0; i < data.length; i++)
		{

			item = r.nextInt(TOP);
			data[i] = item;
			temp.link = new Node(item, null);
			temp = temp.link;	
		}
	}
	
	//testSort() calls the sorting methods
	//because sorting can take a long time for just one data set 
	//testSort only deals with one sorting algorithm/set
	//I have commented the areas where you make the changes to test other sets
	public static void testSort()
	{
		long startTime;
		long endTime;
		double total;
		
		//copy array to to be sorted
		int[] insSort = new int[data.length];
		int[] mergeSort = new int[data.length];
		System.arraycopy(data,0, insSort, 0, data.length);
		System.arraycopy(data,0, mergeSort, 0, data.length);
		
		
		//insert the array/sorting algorithm to use
		startTime = System.nanoTime();
		//change the sorting algorithm here
		Sortings.insertionSort(head);
		endTime = System.nanoTime();
		displayList();
		//displayArray(insSort);
		total = (double)endTime - startTime;
		System.out.println("The total time taken to sort a data set of size " +
		insSort.length + " is " + total / 1000000000 + " seconds");
	}
	
	public static void displayArray(int[] arr)
	{
		int step = 1;
		int counter = 0;
		
		if(arr.length > 50)
		{
			step = arr.length / 50;
		}
		System.out.println("arr[" + counter + "] is: " + arr[counter]);
		counter += step;
		for(int i = 0; i < 40 && i < arr.length - 1; i++ )
		{
			if(arr.length > 50)
			{
			System.out.println("arr[" + counter + "] is: " + arr[counter-1]);
			counter += step;
			}
			else
			{
				System.out.println("arr[" + i + "] is: " + arr[i]);

			}
		}
	}
	
	//prints just the first 10 elements in list
	public static void displayList()
	{
		Node temp = head; //pointer to head of list
		int counter = 0;
		while(counter < 10)
		{
		
			System.out.println("Value of node is: " + temp.data);
			counter++;
			temp = temp.link;
		}
	}
	
	

}
