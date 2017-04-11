import java.io.BufferedReader;
import java.io.IOException;


public class SyntaxAnalyzer {

	BufferedReader program;
	
	
	public SyntaxAnalyzer(BufferedReader program)
	{
		this.program = program;
	}
	
	
	public void analyze() throws IOException
	{
		String temp = next();
		
		//ignore everything before main
		while (temp != "PROGRAM_START")
		{
			temp = next();
		}
		
		if(!checkMain())
		{
			System.out.println("ERROR: Main not declared correctly");
			System.exit(0);
		}
	}
	
	private String next() throws IOException
	{
		return program.readLine();
	}
	
	//checks if the maind eclaration is correct ie: int main(void){
	private boolean checkMain() throws IOException
	{
		String temp;
		temp = next();
		if(temp.equals("OPEN_PAR"))
		{
			if(temp.equals("ID"))
			{
				if(temp.equals("CLOSE_PAR"))
				{
					if(temp.equals("OPEN_CURBRACK"))
					{
						//if it reaches this point then main was used properly
						return true;
					}
				}
			}
		}
		
		//if main wasn't correct return false
		return false;
	}
	
	//nextStatement gets the next token and tries to deduce which type of statement 
	public String nextStatement() throws IOException
	{
		String temp = next();
		String output = "";
		
		
		//if the first token is int/float/char then it must be an assignment
		if(temp.equals("INT_DEC") || temp.equals("FLOAT_DEC")|| temp.equals("CHAR_DEC") )
		{
			temp = next();
			
			output += "(" + temp + ")";
			//if the assignment is simple int = 5
			if(temp.equals("ASSIGN_OP"))
			{
				output += "=";
				temp = next();
				if(temp.equals( "INT" )|| temp.equals("FLOAT") || temp.equals("CHAR"))
				{
					output += temp;
					temp = next();
					
					if(!temp.equals("END_DEL"))
					{
						output = "SYNTAX ERROR";
					}
				}
			}
			while(temp.equals("ID"))
			{
				output +=  "(" + temp + ")";
				temp = next();
			}
			
			if(!temp.equals("END_DEL"))
			{
				output = "SYNTAX ERROR";
			}
		} //end of int/float/char assignment check
		
		
		//analyze syntax of read function
		if(temp.equals("READ_FUNC"))
		{
			temp = next();
			
			//because read can be in parantheses or not in parentheses, check that it has an open par or an ID
			if(temp.equals("OPEN_PAR") || temp.equals("ID"))
			{
				//if so loop through all identifiers being read
				while(temp.equals("ID"))
				{
					temp = next();
				}
				
				//next token that is not an ID must be a closing parentheses or and ending delimiter, if not syntax error
				if(!temp.equals("CLOSE_PAR") || !temp.equals("END_DEL"))
				{
					System.out.println("SYNTAX ERROR");
				}
			}
			
		}
		
		//analyze syntax of write function
		if(temp.equals("WRITE_FUNC"))
		{
			temp = next();
			if(temp.equals("OPEN_PAR"))
			{
				temp = next();
				if(temp.equals("STR_LIT"))
				{
					temp = next();
					if(temp.equals("CLOSE_PAR"))
					{
						
					}
					
				}
			}
		}
		
		if(temp.equals("WHILE_LOOP"))
		{
			temp = next();
			if(temp.equals("ID"))
			{
				if(temp.equals("EQUAL_OP") ||temp.equals("GREATER_OP") || temp.equals("LESS_OP") || temp.equals("LOGNOT_OP"))
				{
					if(temp.equals("ID") || temp.equals("INT") || temp.equals("FLOAT"))
					{
						//valid
					}
				}
			}
		}
		if(temp.equals("DO_STMT"))
		{
			temp = next();
			if(temp.equals("OPEN_CURBRACK"))
			{
				
			}
			
		}
		
		return null;
	}
}
