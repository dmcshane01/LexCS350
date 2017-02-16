
public class Token {

	public enum TOKENS{
		IDENT("Identifier"), ASSIGN("Assignment p"), SUB_OP("Subtraction op"), ADD_OP("Addition Op");
		private String token;
		
		private TOKENS(String token){
			this.token = token;
		}
	}
	TOKENS a = TOKENS.IDENT;
	
	final static char[] OPERATORS = {'+', '=', '-', '*', '/', '!', '<', '>'};
	final static char[] DELIMITERS = {'(', ')', '{', '}', ']', '['};
	final static String[] KEYWORDS = {"main", "end", "begin"}; //add more 
	
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

	public void setDelToken(char val)
	{
		//todo
	}
	
	public void setOpToken(char val)
	{
		
	}
	
	public void setNumToken(String cal)
	{
		
	}
}

