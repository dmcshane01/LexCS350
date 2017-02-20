
public class Token {
	
	final static char[] OPERATORS = {'+', '=', '-', '*', '/', '!', '<', '>'};
	final static char[] DELIMITERS = {'(', ')', '{', '}', ']', '[', '"',',' };
	final static String[] KEYWORDS = {"main","do", "end", "begin", "char", "int", "float", "if", 
			 "else", "while", "read", "float", "for", "write" }; //add more as needed
	
	
	String token = "";
	String lexeme = "";
	public Token()
	{
		
	}
	
	//determines if a char is a valid operator char
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
	
	//determines if a char is a delimiter
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
	
	//determines if a char is whitespace, such as /n /r /t
	public static boolean isWhiteSpace(char value)
	{
		if(value == ' ' || value == '\n' || value == '\t')
		{
			return true;
		}
		return false;
	}
	
	//checks for if a string is a keyword.
	//if it is the key flag is set to true and we mark it as a keyword
	//if it is not then it is treated as identifier
	public void setKeyWordToken(String val)
	{
		val.toLowerCase();
		boolean key = false;
		
		for(int i = 0; i < KEYWORDS.length; i++)
		{
			if(KEYWORDS[i].equals(val))
			{
				//if the string val s equal to a keyword, mark the key flag as true and find the correct token
				lexeme = val;
				key = true;
				
				if(val.equals("main"))
				{
					token = "PROGRAM_START";
				}
				else if(val.equals("begin"))
				{
					token = "START_POINT";
				}
				else if(val.equals("end"))
				{
					token = "PROGRAM_END";
				}
				else if(val.equals("char"))
				{
					token = "CHAR_DEC";
				}
				else if(val.equals("int"))
				{
					token = "INT_DEC";
				}
				else if(val.equals("float"))
				{
					token = "FLOAT_DEC";
				}
				else if(val.equals("if"))
				{
					token = "IF_STMT";
				}
				else if(val.equals("else"))
				{
					token = "DO_STMT";
				}
				else if(val.equals("while"))
				{
					token = "WHILE_LOOP";
				}
				else if(val.equals("for"))
				{
					token = "FOR_LOOP";
				}
				else if(val.equals("read"))
				{
					token = "FUNC_CALL";
				}
				else if(val.equals("write"))
				{
					token = "FUNC_CALL";
				}
			}//end outer if
		}//end for
				
		//if no keyword is found, assume that it is a variablie identifier
		if(!key)
		{
			token = "Identifier";
			lexeme = val;
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
				lexeme += val;
				
				if(val == '=')
				{
					token = "ASSIGN_OP";
				}
				else if(val == '+')
				{
					token = "ADD_OP";
				}
				else if(val == '-')
				{
					token = "SUB_OP";
				}
				else if(val == '*')
				{
					token = "MULT_OP";
				}
				else if(val == '/')
				{
					token = "DIV_OP";
				}
				else if(val == '!')
				{
					token = "NEGATE_OP";
				}
			}
		}
	}
	
	
	//creates token for logical operators that have 2 or more chars
	public void setOpToken(String val)
	{
	
			//loops through all operators and checks that the second char is a valid op char
			//do not need to check the first char because the first op must be a valid op to get to this point
			for(int i = 0; i < OPERATORS.length; i++)
			{
				if(OPERATORS[i] == (val.charAt(1))) //check if the second char in double operator is correct
				{
		
					lexeme = val;
					
					if(val.equals(">="))
					{
						token = "GREATER_OP";
					}
					else if(val.equals("<="))
					{
						token = "LESS_OP";
					}
					else if(val.equals("=="))
					{
						token = "EQUAL_OP";
					}
					else if(val.equals("!="))
					{
						token = "LOGNOT_OP";
					}
					
			}//end outer if
				
		}
	}
	
	//creates token for int or float
	public void setNumToken(String val)
	{
	
		token = "INT";
		lexeme = val;
		//search each char in string for a period, if so set token to FLOAT
		for(int i = 0; i < val.length(); i++)
		{
			if(val.charAt(i) == '.')
			{
				token = "FLOAT";
			}
		}
	}
	
	public void setString(String val)
	{
		token = "STRING LITERAL";
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

