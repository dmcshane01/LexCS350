/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 8
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	December 1, 2016

DESCRIPTION:
StudentTable implements a hash table using the ID objects as the key and Student objects as the data
*/


public class StudentTable {
	
	private int manyItems;
	private ID[] keys;
	private Student[] data;
	private boolean[] used;
	

	
	
	//constructor initalizes arrays to size and sets all indexes of used to false
	public StudentTable(int size)
	{
		keys = new ID[size];
		data = new Student[size];
		used = new boolean[size];
		for(int i = 0; i < used.length; i++)
		{
			used[i] = false;
		}
	}
	
	//returns the number of items in table
	public int size()
	{
		return manyItems;
	}
	
	//returns the total size of the table
	public int capacity()
	{
		return keys.length;
	}
	
	//hash function 
	private int hash(ID key)
	{
		return Math.abs(key.hashCode()) % keys.length;
	}
	
	
	
	private int findIndex(ID key)
	{
		for(int i = 0; i < keys.length; i++)
		{
			if(key.equals(keys[i]))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	//adds object to table
	public void put(ID id, Student student)
	{
		keys[manyItems] = id; //puts the key into next available slot\
		manyItems++; //increase manyitems
		data[hash(id)] = student; //hashes id and stores the student in that index
		used[hash(id)] = true; //marks the index as used
	}
	
	public String firstFifty()
	{
		int count = 0;
		String out = "";
		for(int i = 0; count < 50;i++)
		{
			if(data[i] != null)
			{
				out += (data[i].toString() + "\n");
				count++;
			}
		}
		
		return out;
	}
	
}
