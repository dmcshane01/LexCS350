/*
NAME:		Daniel DelyMcShane
PROJECT:	HW7
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	October 8, 2016

DESCRIPTION:
BinaryHeap is an implementation of a heap using arrays

NOTE: It doesn't work correctly. I can't get the add method to add the items in the correct places
	*/

public class BinaryHeap {

	int manyItems;
	String[] data;
	
	public BinaryHeap(int size)
	{
		data = new String[size];
		manyItems = 0;
	}
	
	public int compareTo(String a, String b)
	{
		return a.compareTo(b);
	}
	
	public void add(String item)
	{
		ensureCapacity(manyItems+1);
		
		data[manyItems] = item;
		manyItems++;
		
		if(manyItems != 1)
		{
		upward();
		}
	}
	
	public void ensureCapacity(int min)
	{
		 String[ ] biggerArray;
	      
	      if (data.length < min)
	      {
	         biggerArray = new String[min];
	         System.arraycopy(data, 0, biggerArray, 0, manyItems);
	         data = biggerArray;
	      }
	}
	
	public void upward()
	{
		int item = manyItems - 1;
		
		System.out.println("Red: " + data[item]);
		
		while(item != 0 && data[item].compareTo(data[getParent(item)]) > 0)
		{
			
			System.out.println("item: " + item + "  " + "parent: " + getParent(item));
			swap(item, getParent(item));
			item = getParent(item);
		}
		
	}
	
	public void removeRoot()
	{
		String answer = data[0];
		int index = 0;
		data[0] = data[manyItems-1];
		manyItems--;
		while((hasLeft(index) || hasRight(index))
		&& (answer.compareTo(data[rightChild(index)]) < 0 || answer.compareTo(data[leftChild(index)]) < 0 )){
			
			//if left is larger swap with left
			if(data[leftChild(index)].compareTo(data[rightChild(index)]) > 0 )
			{
				swap(index, leftChild(index));
				index = leftChild(index);
			}
			else//otherwise swap with right
			{
				swap(index, rightChild(index));
				index = rightChild(index);
			}
			
		}
	}
	
	public static BinaryHeap heapFactory(String[] arr)
	{
		BinaryHeap factory = new BinaryHeap(arr.length);
		for(int i = 0; i < arr.length; i++)
		{
			factory.add(arr[i]);
		}
		
		return factory;
	}
	
	public String toString()
	{
		String out = "";
		for(int i = 0; i < manyItems; i++)
		{
			out+= " " + data[i];
		}
		
		return out;
	}
	
	public void swap(int a, int b)
	{
		String temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
	
	public boolean hasParent(int i)
	{
		return i > 0; //if not in first array index then it has a parent
	}
	
	public int getParent(int i)
	{
		return i/2;
	}
	
	public boolean hasLeft(int i)
	{
		return leftChild(i) <= manyItems;
	}
	
	public boolean hasRight(int i)
	{
		return rightChild(i) <= manyItems;
	}
	
	//returns the index of left child or where left child would be
	public int leftChild(int i)
	{
		return (i*2)+1;
	}
	
	//returns index of right child or where right child should be
	public int rightChild(int i)
	{
		return (i*2)+2;
	}
}
