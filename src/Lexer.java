import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


/*
 * Output of token will be like this.
 * Token Lexeme
*  IDENT result
   ASSIGN_OP =
   IDENT oldsum
   SUB_OP -
   IDENT value
   DIV_OP /
   INT_LIT 100
   SEMICOLON 
 */

public class Lexer {

	final int BUFFER_MARK = 1000; //use .mark and .reset of buffer to move it back and forth
	BufferedReader file;
	ArrayList<Token> tokens;
	int eofMark = 0;
	
	public Lexer(BufferedReader file)
	{
		tokens = new ArrayList<Token>();
		this.file = file;
	}
	
	public void analyzeProgram() throws IOException
	{
		while(eofMark != 65535)
		{
			tokens.add(nextToken());
		}
	}
	
	public  Token nextToken() throws IOException
	{
		String temp = "";
		char val;
		Token curr = new Token();
		
		//get next char from file
		val = (char) file.read();
		eofMark = (int) val;
		
		//skips all whitespace
		while(Token.isWhiteSpace(val))
		{
			val = (char) file.read();
		}
		
		
		if(Token.isDel(val))
		{
			if(val == '"')
			{
				temp += val;
				val = (char) file.read();
				while(val != '"')
				{
					temp += val;
				}
			}
			
			curr.setDelToken(val);
			return curr;
		}
		else if(Token.isOp(val)) //need to check if its a negative number or a double symbol op(!=, >=, etc)
		{
			
			
			//if the next character is an operator, we check to see if its a logical operator that has 2 chars
			//if it is, appen both to an empty string
			if(nextChar() == 1)
			{
				temp += val;
				temp += (char) file.read();
				curr.setOpToken(temp);
			}
			else
			{
			curr.setOpToken(val);
			return curr;
			}
		}
		else if(Character.isDigit(val)) //need to check for floats, etc
		{
			
			temp += val;
			val = (char) file.read();
			while(Character.isDigit(val))
			{
				temp += val;
			}
		}
		//checks for keywords
		else if(Character.isLetter(val))
		{
			temp += val;

			
			while(nextChar() == 3)
			{
				val = (char) file.read();
				temp += val;
			}
			
			
		}
		curr.setKeyWordToken(temp);
		return curr;
	}
	
	//check if the next char in the buffered stream is an operator
	//returns 1 if the next character in the buffer is an operator
	//returns 2 if the next character is a /, indicating a comment
	//returns 3 if the next charatcter is a letter
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
		if(val == '/')
		{
			file.reset();
			return 2;
		}
		if(Character.isLetter(val))
		{
			file.reset();
			return 3;
		}
		
		file.reset(); //reset buffer back to original spot
		return 0;
		
		
	}
	
	public void outputTokens()
	{
	
		for(int i = 0; i < tokens.size() - 1; i++)
		{
			System.out.println(tokens.get(i).toString());
		}
	}
	
	
}
