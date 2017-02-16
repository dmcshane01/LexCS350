import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


public class Lexer {

	final int BUFFER_MARK = 1000; //use .mark and .reset of buffer to move it back and forth
	BufferedReader file;
	ArrayList<Token> tokens;
	
	public Lexer(BufferedReader file)
	{
		this.file = file;
	}
	
	public  Token nextToken() throws IOException
	{
		String temp = "";
		char val;
		Token curr;
		
		//get next char from file
		val = (char) file.read();
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
	}
	
	//check if the next char in the buffered stream is an operator
	//returns 1 if the next character in the buffer is an operator
	//returns 2 if the next character is a /, indicating a comment
	//otherwise returns 0 if 
	public int nextChar() throws IOException
	{
		
		file.mark(BUFFER_MARK);
		char val = (char) file.read();
		
		if(Token.isOp(val))
		{
			return 1;
		}
		if(val == '/')
		{
			return 2;
		}
		
		file.reset(); //reset buffer back to original spot
		return 0;
		
		
	}
	
	
}
