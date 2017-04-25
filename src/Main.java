import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader r = new BufferedReader(new FileReader("test.txt"));

		// Run lexical analyzer to create tokens for syntax analyzer
		Lexer lex = new Lexer(r);
		lex.analyzeProgram();
		lex.outputTokens("output.txt");
		System.out.println("Finished lexical analyzer\n");

		// reinitialize reader to the lex analyzer output
		r = new BufferedReader(new FileReader("semFile.txt"));

		// create syntax analyzer and loop through all statements until the return statement is reached
		SyntaxAnalyzer sa = new SyntaxAnalyzer(r);
		String temp = sa.nextStatement();
		try {
			while (!temp.equals("END_OF_FUNCTION")) {
				System.out.println(temp);
				temp = sa.nextStatement();
			}
		} catch (NullPointerException e) {
			System.out.println("MISSING RETURN STATEMENT");
			System.exit(1);
		}
		System.out.println(temp);
		if (sa.isValid()) {
			System.out.println("SYNTAX OKAY");
		} else {
			System.out.println("SYNTAX ERRORS WERE FOUND");
		}

	}

}
