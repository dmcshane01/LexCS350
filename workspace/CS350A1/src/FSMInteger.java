/*
 * Daniel DelyMcShane
 * CS350 Assignment 1 Q2.2
 * 1/28/2017
 * User inputs a string. The string is matched against regex patterns and determines if the string is a valid integer or float
 * returns 1 if valid, otherwise 0
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;


public class FSMInteger {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(System.in);
		String input= s.next();
		
		while(input.charAt(0) != 'q')
		{
			System.out.println(isNum(input));
			input= s.next();
		}
		s.close();
	}
	
	public static int isNum(String input)
	{
		//searches for numbers in format 11.11, 1, +1, -1
		String reg1 = "[-+]?[0-9]+\\.?[0-9]*";
		//searches for 43.3E1, 0.3E+1, -0.3E+1
		String reg2 = "[-+]?[0-9]+\\.?[0-9]+[E]+[-+]?[0-9]+";
		//searches for -33232E8
		String reg3 = "[-+]?[0-9]+[E]+[-+]?[0-9]+";
		
		//create Pattern objects from regex patterns
		Pattern p1 = Pattern.compile(reg1);
		Pattern p2 = Pattern.compile(reg2);
		Pattern p3 = Pattern.compile(reg3);
		
		//Create matcher objects and assign them the input string
		Matcher m1 = p1.matcher(input);
		Matcher m2 = p2.matcher(input);
		Matcher m3 = p3.matcher(input);
		
		//if any of the patterns are a match 
		if(m1.matches() || m2.matches() || m3.matches())
		{
			return 1;
		}
		
		return 0;
	}
	
}

/*

public int toChange(String input)
{
	input.toUpperCase();
	int pos = 0;//position of character in string
	
	//checks if the first character is a valid input
	if(input.charAt(pos) == '-' || input.charAt(pos) == '+' || input.charAt(pos) == '.'|| Character.isDigit(input.charAt(pos)))
	{
		pos++;
		//if the second character is a digit start this block
		if(Character.isDigit(input.charAt(pos)))
		{
			// loop pos until the next character is not a digit
			while(Character.isDigit(pos))
			{
				pos++;
			}
			
			//if the next non digit character is a period
			if(input.charAt(pos) == '.' )
			{
				//if there is an E following the period
				if(input.charAt(pos) == 'E')
				{
					//checks that the character following the E is a digit or +=
					if(input.charAt(pos + 1) == '+' || input.charAt(pos + 1) == '-' || Character.isDigit(input.charAt(pos)) )
					{
						//if it is increment pos and check for the rest of the strings to be digits
						pos++;
						for(; pos < input.length(); pos++)
						{
							//if there is a non digit character return 0.
							if(!Character.isDigit(input.charAt(pos)))
							{
								return 0;
							}
						}
						
						//if all remaining characters are digits return 1
						return 1;
					}
				}
			}
			else if(input.charAt(pos) == 'E')
			{
				//checks that the character following the E is a digit or +=
				if(input.charAt(pos + 1) == '+' || input.charAt(pos + 1) == '-' || Character.isDigit(input.charAt(pos)) )
				{
					//if it is increment pos and check for the rest of the strings to be digits
					pos++;
					for(; pos < input.length(); pos++)
					{
						//if there is a non digit character return 0.
						if(!Character.isDigit(input.charAt(pos)))
						{
							return 0;
						}
					}
					
					//if all remaining characters are digits return 1
					return 1;
				}
			}
		}
	}
	else
	{
		return 0;
	}
	return 1;
}
*/

