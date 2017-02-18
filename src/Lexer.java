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
		Token curr = null;
		
		//get next char from file
		val = (char) file.read();
		eofMark = (int) val;
		System.out.println(eofMark);
		
		//skips all whitespace
		while(Token.isWhiteSpace(val))
		{
			val = (char) file.read();
		}
		
		
		if(Token.isDel(val))
		{
			curr = new Token();
			curr.setDelToken(val);
			return curr;
		}
		else if(Token.isOp(val)) //need to check if its a negative number or a double symbol op(!=, >=, etc)
		{
			curr = new Token();
			
			//if the next character is an operator, we check to see if its a logical operator that has 2 chars
			//if it is, appen both to an empty string
			if(nextChar() == 1)
			{
				temp += val;
				temp += file.read();
				//curr.setOpToken(temp);
			}
			else if(nextChar() == 3)
			{
				
			}
			curr.setOpToken(val);
			return curr;
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
		else if(Character.isAlphabetic(val))
		{
			temp += val;
			val = (char) file.read();
			
			while(Character.isAlphabetic(val))
			{
				temp += val;
				val = (char) file.read();
			}
			
			
		}
		return curr;
	}
	
	//check if the next char in the buffered stream is an operator
	//returns 1 if the next character in the buffer is an operator
	//returns 2 if the next character is a /, indicating a comment
	//returns 3 if the next charatcter is a digit, usually indicating a negative constant.
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
		
		file.reset(); //reset buffer back to original spot
		return 0;
		
		
	}
	
	public void outputTokens()
	{
		System.out.println(tokens.get(0).toString());
		for(int i = 0; i < tokens.size() - 1; i++)
		{
			System.out.println(tokens.get(i).toString());
		}
	}
	
	
}
