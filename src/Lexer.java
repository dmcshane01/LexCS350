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
	
	
}
