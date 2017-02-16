/*
NAME:		Daniel DelyMcShane
PROJECT:	HW 5
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	October 20, 2016

DESCRIPTION:
Main contains the main method and reads integers from a file and sorts them from highest to lowest
by using two stacks

The Big O of the program commented on below
	*/

import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		//initialize stacks
		LinkedStack<Integer> numStack = new LinkedStack<Integer>(); 
		LinkedStack<Integer> tempStack = new LinkedStack<Integer>();
		
		//open file, scanner
		File f = new File("Numbers.txt");
		Scanner scan = new Scanner(f);
		Integer read;
		
		
		/*This is the main algorithm for this program 
		 * The best case for this algorithm is O(n) which would occur when the numbers being read from the file are already in order
		 * The worst case for this algorithm will be O(n^2) because it will loop through each number in the file which is o(n)
		 * then it needs to loop through each stack at least once, but up to the whole stack, which is O(n+2)
		 * This gives O(n^2)
		 * The average case is the same as worst.
		 */
		while(scan.hasNextInt())//loops while Integers are still in file
		{
			
			read = scan.nextInt();
			
			if(numStack.isEmpty())//if stack is empty push number
			{
				numStack.push(read);				
			}
			else if(read > numStack.peek()) //if the number is greater than the current top of stack push it on
			{
				numStack.push(read);
			}
			else //otherwise the number read from the file is less than the numStack top
			{
				while(!numStack.isEmpty() && numStack.peek() > read) //loops through numStack while it is not empty and the top is greater than the selected number from file
				{
					tempStack.push(numStack.pop().getData()); // pop integer of numstack and push onto tempstack
				}
				
				numStack.push(read); //push the number read from file onto numstack
				
				while(!tempStack.isEmpty())//returns all the numbers from the tempstack onto the number stack
				{
					numStack.push(tempStack.pop().getData());
				}
			}//end if else
			
		}//end while
		System.out.println(numStack.toString());
	}//end main
}//end class
	


/*
 * DESIGN FOR MAIN:
 * 		The main algorithm in this program is located in the while loop in main
 * 		it loops through the numstack and pops numbers off until it reaches a point
 * 		in the stack where we can put the number being read from the file on so that its still in order
 * 		This can be accomplished using an if/else statement inside of a while loop
 * 		Simply loop while there is still numbers in the file to be read.
 * 		Then check the value of the number and compare it to the top of the stack
 * 		There are 3 scenarios: Stack is empty(push item onto stack without checking:
 * 		The current number read from file is larger than the top of stack(Push on stack without checking)
 * 		Or the current number is less than the top of stack. In this case, we need to pop each value off the main stack
 * 		And push it onto the temporary stack until we reach a point where we can push the number read from file onto the main stack
 * 		Then we can Simply pop all the numbers from the tempstack and push them onto the numstack while still retaining the proper order		
 *		
 *		Psuedocode:
 *		While numbers in file:
 *			Grab next number from file
 *			If numstack is empty:
 *				Push next number onto stack
 *			elseif number is greater than top of stack:
 *				push number onto stack
 *			else number must be less than top of stack:
 *				While numStack top is greater than number read from file:
 *					Pop top from numstack and push onto tempstack
 *				Push number onto numstack
 *				While tempStack is not empty:
 *					Push top of tempStack onto numStack
 */

