/*
NAME:		Daniel DelyMcShane
PROJECT:	Project 3
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	December 12, 2016

DESCRIPTION:
	Sortings houses static sorting methods to be used on arrays and linked lists
	
*/
public class Sortings {

	/*
	 * InsertionSort
	 * Uses the insertion sort algorithm to sort an array of ints
	 */
	public static void insertionSort(int[] data)
	{
		//outer for loop starts at first index+1
		for(int i = 1; i < data.length; i++)
		{
			int cur = data[i]; //used for comparison
			int j; //declare j here because we need to retain index after for loop to swap
			
			for(j = i-1; j >= 0 && cur < data[j]; j--)
			{
				data[j+1] = data[j]; //move data down if cur is less than index at j
		
			}
			data[j+1] = cur; //put the new value in the swapped spot
			
		}

	}
	
	
	/*
	 * InsertionSort
	 * uses insertion sort to sort a linked list 
	 */
	public static Node insertionSort(Node head)
	{

		   Node sorted = null; //stores sorted list
		   Node temp;
		   Node previous;
		   
		   while(head != null){
		    Node cur = head; //current node for comparison
		    head = head.link; //advance head 1 positon each loop
		    previous = null; //set to null each loop
		    
		    for(temp = sorted; temp != null; temp = temp.link){
		        
		    	if(cur.data < temp.data) //once temp has reached a node greater than the current break for loop
		    	{ 
		                break;
		        } 
		         previous = temp; //store previous node
		  
		    }
		    
		    if(previous == null)
		    {               
		          cur.link = sorted; //point to the sorted list
		          sorted = cur; //assign sorted to the start of new list
		    }
		    else
		    {               
		       cur.link = previous.link; //cur links to the new node in the list
		       previous.link = cur; // inserts cur into the sorted list
		    }
		  }
		   
		   
		  return sorted;
		
	}
	
	
	/*
	 * MergeSort
	 * Uses mergesort to sort a linked list
	 * Helper methods:
	 * 	MidNode(): finds the middle node of the list
	 * 	MergeSortHelper(): combines the sorted list into one list
	 */
	public static Node mergeSort(Node head)
	{
		if(head == null || head.link == null)
		{
			return head;
		}
		
		Node mid = midNode(head);
		
		//set upper to the 2nd half of list
		Node upper = mid.link;
		//detach mid from other half of list making two lists
		mid.link = null;
		
		//recursively split the list into smaller halfs
		return mergeSortHelper(mergeSort(head), mergeSort(upper));
	}
	
	public static Node midNode(Node head)
	{
		Node answer = head; //will be the middle node in list
		Node dbl = head; //Node dbl advances twice as fast as answer
		
		//once dbl reaches end of list answer will be at middle of list
		while(dbl.link != null && dbl.link.link != null)
		{
			answer = answer.link;
			dbl = dbl.link.link;
		}
		
		return answer;
	}
	
	public static Node mergeSortHelper(Node x, Node y)
	{
		Node temp = new Node(-1, null); //dummy node initialized to -1
		Node current = temp; //pointer to temp for looping
		
		while(x != null && y != null)
		{
			//if x is less than y add x to the list and advance x
			if(x.data <= y.data)
			{
				current.link = x;
				x = x.link;
			}
			else
			{
				current.link = y; 
				y = y.link;
			}
			//advances list
			current = current.link;
		}
		
		//if x or y is the empty list add the last value to the list
		if(x == null)
		{
			current.link = y;
		}
		else
			current.link = x;
		
		//skips dummy node
		return temp.link;
		
	}
	
	/*
	 * MergeSortArray
	 * Uses merge sort to sort an array of ints
	 * Helper Methods:
	 * 		merge: combines smaller sorted subarrays
	 */

	public static void mergeSortArray(int[] data, int first, int n)
	{
		int n1;
		int n2;
		
		if(n > 1)
		{
			n1 = n/2;
			n2 = n - n1;
			
			mergeSortArray(data, first, n1);
			mergeSortArray(data, first+n1, n2);
			merge(data, first, n1, n2);
		}
	}

	public static void merge(int[] data, int first, int n1, int n2)
	{
		  int[] temp = new int[n1+n2]; //temp array for storage
	      int total  = 0; //elements in temp array
	      int lower = 0; // lower half elements
	      int upper = 0; // higher elements
	     

	      // copy the lower and upper elements into the temp array while lower and upper
	      //are less than the input n's
	      while ((lower < n1) && (upper < n2))
	      {
	    	  //if the lower portion data is less than the upper portion
	    	  //
	         if (data[first + lower] < data[first + n1 + upper])
	         {
	        	 //store the lower data in next array index
	            temp[total] = data[first + lower];
	            total++;
	            lower++;
	         }
	         else
	         {
	        	 //else the upper data needs to be inserted into the temp array
	        	 temp[total] = data[first + n1 + upper];
	        	 total++;
	        	 upper++;
	         }
	            
	      }

	      //copy any leftover elements into the array
	      while (lower < n1)
	      {
	    	  temp[total] = data[first + lower];
	    	  total++;
	    	  lower++;
	      }
	         
	      while (upper < n2)
	      {
	         temp[total] = data[first + n1 + upper];
	         total++;
	         upper++;
	      }

	      // put sorted array into data array
	      for (int i = 0; i < n1+n2; i++)
	         data[first + i] = temp[i];
	}
}
