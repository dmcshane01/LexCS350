/*
NAME:		Daniel DelyMcShane
PROJECT:	Project 2
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	November 8, 2016

DESCRIPTION:
CellQueue is a circular queueu implemented using arrays using the books definitions
*/

import java.util.NoSuchElementException;

public class CellQueue {

	private int manyItems, front, rear;
	private Cell[] data;

	//construct takes size of queue
	//throws IllegalArgumentException when size is less than 0
	public CellQueue(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException(
					"Initial size must be greater than 0");
		}
		manyItems = 0;
		data = new Cell[size];
	}

	//add a cell to the queue
	public void enqueue(Cell item) {
		if (manyItems == data.length) { //if the queue is full increase capacity
			ensureCapacity(manyItems * 2 + 1);
		}

		//if its the first item to be added intialize front and rear to 0
		if (manyItems == 0) {
			front = 0;
			rear = 0;
		} else { //otherwise use nextindex to find the index to add it in
			rear = nextIndex(rear);
		}

		//store item in array
		data[rear] = item;
		manyItems++;
	}

	//ensure capacity is used to check the size of the array and the number of items
	//in it when items are added to the array
	public void ensureCapacity(int minimumCapacity) {
		Cell[] biggerArray;
		int n1, n2;

		if (data.length >= minimumCapacity)
			return; // Do Nothing
		else if (manyItems == 0) {
			data = new Cell[minimumCapacity];
		} else if (front <= rear) {
			biggerArray = new Cell[minimumCapacity];
			System.arraycopy(data, front, biggerArray, front, manyItems);
			data = biggerArray;
		} else {
			biggerArray = new Cell[minimumCapacity];
			n1 = data.length - front;
			n2 = rear + 1;
			System.arraycopy(data, front, biggerArray, 0, n1);
			System.arraycopy(data, 0, biggerArray, n1, n2);
			front = 0;
			rear = manyItems - 1;
			data = biggerArray;
		}
	}// end ensureCapacity

	public Cell dequeue() {
		Cell answer;
		if (manyItems == 0) {
			throw new NoSuchElementException("CellQueue Underflow");
		}
		answer = data[front];
		front = nextIndex(front);
		manyItems--;
		return answer;
	}

	//nextIndex ensures that our queue can be used as a circular queue by looping around
	//to the first index to store new items
	public int nextIndex(int i) {
		return (i + 1) % data.length;
	}

	public boolean isEmpty() {
		return manyItems == 0;
	}
}
