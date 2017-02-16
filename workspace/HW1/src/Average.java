/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 1
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	September 3, 2016(sample)

DESCRIPTION:
Average is a Generic class that requires a subclass of Number type parameter to be used on instantiation.
Average then performs simple computations and calculations on the Number array 

INSTANCE METHODS:
	computerAverage
		Calculates the average of the values in arr and returns the result as a Double
	compareAverages(Average<? extends Number>)
		Takes another Average<Number> object as a parameter that can contain any subclass of Number
		and returns true if the other object has the same average value as the calling object
	getName
		returns a String of the class that was instantiated in this instance ie: Integer, Float, etc

INSTANCE VARIABLES:
	arr
		set of Numbers used for computation and comparison
	total
		double used to keep total value of elements in arr to determine average
	result
		returns the average as a Double
*/


public class Average <T extends Number> { //declare that type T must be a subclass of Number
	
	private T[] arr; //	set of Numbers used for computation and comparison
	
	//Constructor's parameter is an array of T and sets that to the Objects Number array
	public Average(T[] arr){
		this.arr = arr;
	}
	
	//computerAverage returns the average of the Number array and returns the result as a Double
	public Double computeAverage(){
		double total = 0; //double used to keep total value of elements in arr to determine average
		
		for(int i = 0; i < arr.length; i++)//loop through array and add the total
		{
			total += arr[i].doubleValue(); //add current value of element in arr to total's value
		}
		
		Double result = total/arr.length; //returns the average as a Double
		
		return result;
	}//end computerAverage
	
	
	//Takes another Average<Number> object and returns true if the average of that object is the same as the calling object
	public boolean compareAverages(Average<? extends Number> other){
		if(this.computeAverage().compareTo(other.computeAverage()) == 0){ //Double.compareTo() returns 0 if two Doubles have the same v
			return true;
		}
		else
			return false;
	}
	
	//returns a String of the class that was instantiated in this instance ie: Integer, Float, etc
	public String getName(){
		return arr.getClass().getSimpleName();
	}

} //end Average<>
