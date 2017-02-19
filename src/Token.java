
public class Token {
	
	final static char[] OPERATORS = {'+', '=', '-', '*', '/', '!', '<', '>'};
	final static char[] DELIMITERS = {'(', ')', '{', '}', ']', '[', '"', };
	final static String[] KEYWORDS = {"main", "end", "begin", "char", "int", "float", "if", "else", "while", "read" }; //add more 
	
	
	String token = "";
	String lexeme = "";
	public Token()
	{
		
	}
	
	//Return true if the char is an operator
	public static boolean isOp(char value)
	{
		for(int i = 0; i < OPERATORS.length; i++)
		{
			if(value == OPERATORS[i])
			{
				return true;
			}
			
		}
		return false;
	}
	
	//returns true if the character is a delimiter
	public static boolean isDel(char value)
	{
		for(int i = 0; i < DELIMITERS.length; i++)
		{
			if(value == DELIMITERS[i])
			{
				return true;
			}
			
		}
		return false;
	}
	
	//returns true if char is whitespace
	public static boolean isWhiteSpace(char value)
	{
		if(value == ' ' || value == '\n' || value == '\t')
		{
			return true;
		}
		return false;
	}
	
	public void setKeyWordToken(String val)
	{
		val.toLowerCase();
		
		for(int i = 0; i < KEYWORDS.length; i++)
		{
			if(KEYWORDS[i].equals(val))
			{
				//need to change to proper token/lexeme
				token = val;
				lexeme = val;
				System.out.println("R");
			}
		}
		
	}
	
	public void setDelToken(char val)
	{
		for(int i = 0; i < DELIMITERS.length; i++)
		{
			if(DELIMITERS[i] == val)
			{
				//need to change to proper token/lexeme
				token += val;
				lexeme += val;
			}
		}
	}
	
	public void setOpToken(char val)
	{
		for(int i = 0; i < OPERATORS.length; i++)
		{
			if(OPERATORS[i] == val)
			{
				//need to change to proper token/lexeme
				token += val;
				lexeme += val;
			}
		}
	}
	
	
	/*TODO
	public void setOpToken(String val)
	{

		for(int i = 0; i < OPERATORS.length; i++)
		{
			if(OPERATORS[i].equals(val))
			{
				//need to change to proper token/lexeme
				token = val;
				lexeme = val;
			}
		}
	}
	*/
	
	public void setNumToken(String val)
	{
		token = val;
		lexeme = val;
	}
	

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
	public String toString()
	{
		return token + "  " + lexeme;
	}
}

