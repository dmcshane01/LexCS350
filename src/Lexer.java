import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*
 * Daniel DelyMcShane
 * CS350 Prog Lang Design, Dr. Ng
 * Feb 20, 2017
 * Assignment 2
 * 	CLASS LEXER:
 * 		The Lexer class acts as the lexical analyzer for this simple programming language
 * 		It uses a bufferedreader in the constructor that represents the program that it is analyzing
 * 		It reads the program char by char and creates the appropriate tokens for each terminal symbol
 */

public class Lexer {

	final int BUFFER_MARK = 1000; //use .mark and .reset of buffer to move it back and forth
	BufferedReader file;
	ArrayList<Token> tokens;
	int eofMark = 0;
	char prevChar;
	
	//Constructor gets passed a bufferedreader which used to read program
	public Lexer(BufferedReader file)
	{
		//store tokens in an arraylist
		tokens = new ArrayList<Token>();
		this.file = file;
	}
	
	//Main driver of lexical analyzer. Reads each token char by char
	public void analyzeProgram() throws IOException
	{
		//for some reason the buffer usess 65535 as the eof mark
		while(eofMark != 65535)
		{
			tokens.add(nextToken());
		}
	}
	
	
	//This method is where the lexical analyzr reads the file char by char and creates the tokens
	//this can be consider the finite state machine portion of the program
	public  Token nextToken() throws IOException
	{
		String temp = "";
		char val;
		Token curr = new Token();
		
		//get next char from file
		val = (char) file.read();
		eofMark = (int) val;
		
		//skips all whitespace
		while(Character.isWhitespace(val))
		{
			val = (char) file.read();
		}
		
		
		//if the token is a delimiter
		if(Token.isDel(val))
		{
			//if the double quotation is found then we know that it is a string literal
			if(val == '"')
			{
				temp += val;
				val = (char) file.read();
				//append each char to temp until we get to the closing double quotes
				while(val != '"')
				{
					temp += val;
					val = (char) file.read();
				}
				
				temp += val;
				
				curr.setString(temp);
				return curr;
			}//end if
			
			
			curr.setDelToken(val);
			return curr;
		}
		else if(Token.isOp(val)) //need to check if its a negative number or a double symbol op(!=, >=, etc)
		{
			
			//check for negative numbers
			if(val == '-')
			{
				//if the next character is a nummber we can assume that it is a negative int or float
				if(nextChar() == 4 )
				{
					temp += val;
					
					val = (char) file.read();
					file.mark(BUFFER_MARK); //mark current spot in buffer incase we need to roll back
					
					//read all chars while they are digit
					while(Character.isDigit(val))
					{
						temp += val;
						val = (char) file.read();
					}
					
					//if there is a period next to the digits we assume that it is a floating point numbe
					if(val == '.')
					{
						temp += val;
						val = (char) file.read();
						
						//appends all numbers after the period to string
						while(Character.isDigit(val))
						{
							temp+= val;
							val = (char) file.read();
						}
					}
					
					curr.setNumToken(temp);
					return curr;
					
				}
			}
			//if the next character is an operator, we check to see if its a logical operator that has 2 chars
			//if it is, appen both to an empty string
			if(nextChar() == 1)
			{
				temp += val;
				temp += (char) file.read();
				curr.setOpToken(temp);
				return curr;
			}
			//otherwise we can set the token as being a single char operator
			else
			{
			curr.setOpToken(val);
			return curr;
			}
		}
		//creates a token for float or integer if a digit is found
		else if(Character.isDigit(val)) 
		{
			
			temp += val;
			val = (char) file.read();
			
			//add all digits to string
			while(Character.isDigit(val))
			{
				temp += val;
				val = (char) file.read();
			}
			
			//if a period is directly after a number assume that this number is a float
			if(val == '.')
			{
				temp += val;
				val = (char) file.read();
				
				//append all numbers after period
				while(Character.isDigit(val))
				{
					temp+= val;
					val = (char) file.read();
				}
			}
			
			curr.setNumToken(temp);
			return curr;
		}
		//if the char read is a letter we know it must either be a keyword or a usermade identifier
		else if(Character.isLetter(val))
		{
			temp += val;

			
			//while the nextchar is still a letter continue adding it to sring
			while(nextChar() == 3)
			{
				val = (char) file.read();
				temp += val;
			}
			
			
		}
		curr.setKeyWordToken(temp);
		return curr;
	}
	
	//nextChar() peeks ahead in the buffer to examine the next character
	//returns 1 if the next character in the buffer is an operator
	//returns 2 if the next character is a /, indicating a comment
	//returns 3 if the next character is a letter
	//returns 4 if the next character i a digit
	//otherwise returns 0 if 
	public int nextChar() throws IOException
	{
		
		file.mark(BUFFER_MARK);
		char val = (char) file.read();
		
		if(Token.isOp(val))
		{
			file.reset();
			return 1;
		}
		if(val == '/')   //todo
		{
			file.reset();
			return 2;
		}
		if(Character.isLetter(val))
		{
			file.reset();
			return 3;
		}
		if(Character.isDigit(val))
		{
			file.reset();
			return 4;
		}
		
		file.reset(); //reset buffer back to original spot
		return 0;
		
		
	}
	
	
	//write all tokens and lexems to a file using the bufferedwriter
	//takes the filename/path as the parameter
	public void outputTokens(String outputFile) throws IOException
	{
	
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		bw.write("TOKEN:\t\tLEXEME\n");//header
		for(int i = 0; i < tokens.size() - 1; i++)
		{
			if(tokens.get(i).lexeme.equals(""))
			{
				//I cant figure out why it keeps adding some blank lexemes so just skip them for now
			}
			else
			{
				bw.write(tokens.get(i).toString() + "\n");
				bw.flush();
			}
		}
		
		 bw = new BufferedWriter(new FileWriter("semFile.txt"));
		
		for(int i = 0; i < tokens.size() - 1; i++)
		{
			if(tokens.get(i).lexeme.equals(""))
			{
				//I cant figure out why it keeps adding some blank lexemes so just skip them for now
			}
			else
			{
				bw.write(tokens.get(i).syntaxToString() + "\n");
				bw.flush();
			}
		}
	}
	
	
}
