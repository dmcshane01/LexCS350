import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {


		BufferedReader r = new BufferedReader(new FileReader("test.txt"));
		
		Lexer lex = new Lexer(r);
		lex.analyzeProgram();
		lex.outputTokens("output.txt");
		System.out.println("Finish lexical analyzer");
		
		r = new BufferedReader(new FileReader("semFile.txt"));

		SyntaxAnalyzer sa = new SyntaxAnalyzer(r);
		System.out.println("STATEMENT: " + sa.nextStatement());
		System.out.println("STATEMENT: " + sa.nextStatement());
		System.out.println("STATEMENT: " + sa.nextStatement());
		
	}

}
