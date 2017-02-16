package yyyy;

import java.util.Scanner;

public class test {

public static void main(String[] args) {
		
		int total = 0;
		Scanner s = new Scanner(System.in);
		int count = 0;
		while(count < 10)
		{
			System.out.println("Enter number " + (count+1) + ": ");
			total += s.nextInt();
			count++;
		}
		
		System.out.println("Enter number " + (count+1) + ": ");
		
		total = total/s.nextInt();
		System.out.println("Answer: " + total);
		s.close();

	}

	public static String middle(String word)
	{
		
		 int length = word.length();
		    String out;
		    if(length % 2 == 0){
		      out =  Character.toString(word.charAt((length/2)-1))  + Character.toString(word.charAt(length/2)) ;
		      
		    }
		    else{
		      out = Character.toString(word.charAt((length/2)));
		    }
		    
		    return out;
		  }
	}


