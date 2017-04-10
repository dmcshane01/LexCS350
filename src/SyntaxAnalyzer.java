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
		if(temp == "OPEN_PAR")
		{
			if(temp == "ID")
			{
				if(temp == "CLOSE_PAR")
				{
					if(temp == "OPEN_CURBRACK")
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
}
