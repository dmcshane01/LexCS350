/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 1
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	September 3, 2016(sample)

DESCRIPTION:
Application creates 4 Number subclass arrays and demonstrates the Average<> class' methods with them
*/

import java.util.ArrayList;

public class Application {

	public static void main(String[] args) {
		
		//Initialze Array for each data type
		Float[] fArr = {5.2f, 4.1f, 9.8f, 1.7f};
		Long[] lArr = {1l,5l,4l,2l};
		Integer[] iArr = {2,5,3,2};
		Byte[] bArr = {2,3,5,2};
		
		//create Average object that corresponds to each Array
		Average<Float> fObj = new Average<Float>(fArr);
		Average<Long> lObj = new Average<Long>(lArr);
		Average<Integer> iObj = new Average<Integer>(iArr);
		Average<Byte> bObj = new Average<Byte>(bArr);
		
		//Print outt the average for each Average object
		System.out.println("Float Average: " + fObj.computeAverage());
		System.out.println("Long Average: " + lObj.computeAverage().getClass().getSimpleName());
		System.out.println("Integer Average: " + iObj.computeAverage());
		System.out.println("Byte Average: " + bObj.computeAverage());

		//numSet is an ArrayList that is instantiated with an Average that must have already been instantiated with a Number subclass
		//Then add our Average<Number> objects to the list.
		//The Average<> objects are stored in the ArrayList as opposed to an array to prevent the passing of raw or unintended types
		//It is instantiated with Averages<> that must have been instantiated with a subclass of Number, hence the <? extends Number>
		ArrayList<Average<? extends Number>> numSet = new ArrayList<Average<? extends Number>>();
		numSet.add(fObj);
		numSet.add(lObj);
		numSet.add(iObj);
		numSet.add(bObj);
		
		//nested for loop to compare each Average's average value
		for(int j = 0; j < numSet.size(); j++){
			for(int k = j+1; k < numSet.size(); k++){
				if(numSet.get(j).compareAverages(numSet.get(k)) == true){ //checks if two averages are the same.

					System.out.println("The " + numSet.get(j).getName() + " array has the same average as the "
							+ numSet.get(k).getName() + " array");
				}
				else{
					
					System.out.println("The " + numSet.get(j).getName() + " array does not have the same average as the "
							+ numSet.get(k).getName() + " array");
				}
			}//end of inner for loop
		}//end of Outer for loop
	}
}//end of Application
