/*
 * Daniel DelyMcShane
 * CS350 Prog Lang Design, Dr. Ng
 * Feb 20, 2017
 * Assignment 2
 * 
 * CLASS TOKEN:
 * 		The Token class is used to store one token. It has static methods that determine if
 * 		which type of token a lexeme needs to be assigned.
 * 		
 */
public class Token {

	// collection of legal operators, delimters and keywords
	// These can also be considered the Regular Grammer of our programming
	// language(Hopefully address 1.1 of the assignment)
	final static char[] OPERATORS = { '+', '=', '-', '*', '/', '!', '<', '>' };
	final static char[] DELIMITERS = { '(', ')', '{', '}', ']', '[', '"', ',', ';' };
	final static String[] KEYWORDS = { "main", "do", "end", "begin", "char", "int", "float", "if", "else", "while",
			"read", "float", "for", "write", "then", "return" }; // add more as
																	// needed

	// initialize token and lexeme to empty strings because I cannot get the
	// lexical analyzer and buffered reader
	// to read in some cases of whitespace which don't modify them if they are
	// uninitialized and causes nullref errors
	String token = "";
	String lexeme = "";

	// determines if a char is a valid operator char
	public static boolean isOp(char value) {
		for (int i = 0; i < OPERATORS.length; i++) {
			if (value == OPERATORS[i]) {
				return true;
			}
		}
		return false;
	}

	// determines if a char is a delimiter
	public static boolean isDel(char value) {

		for (int i = 0; i < DELIMITERS.length; i++) {
			if (value == DELIMITERS[i]) {
				return true;
			}

		}
		return false;
	}

	// determines if a char is whitespace, such as /n /r /t
	// figured out this is not need at this point in time
	/*
	 * public static boolean isWhiteSpace(char value) {
	 * if(Character.isWhitespace(value)) { return true; } return false; }
	 */

	// checks for if a string is a keyword.
	// if it is the key flag is set to true and we mark it as a keyword
	// if it is not then it is treated as identifier
	public void setKeyWordToken(String val) {
		val.toLowerCase();
		boolean key = false;

		for (int i = 0; i < KEYWORDS.length; i++) {
			if (KEYWORDS[i].equals(val)) {
				// if the string val s equal to a keyword, mark the key flag as
				// true and find the correct token
				lexeme = val;
				key = true;

				if (val.equals("main")) {
					token = "PROGRAM_START";
				} else if (val.equals("begin")) {
					token = "START_POINT";
				} else if (val.equals("end")) {
					token = "PROGRAM_END";
				} else if (val.equals("char")) {
					token = "CHAR_DEC";
				} else if (val.equals("int")) {
					token = "INT_DEC";
				} else if (val.equals("float")) {
					token = "FLOAT_DEC";
				} else if (val.equals("if")) {
					token = "IF_STMT";
				} else if (val.equals("else") || val.equals("then")) {
					token = "ELSE_STMT";
				} else if (val.equals("do")) {
					token = "DO_STMT";
				} else if (val.equals("while")) {
					token = "WHILE_LOOP";
				} else if (val.equals("for")) {
					token = "FOR_LOOP";
				} else if (val.equals("read")) {
					token = "READ_FUNC";
				} else if (val.equals("write")) {
					token = "WRITE_FUNC";
				} else if (val.equals("return")) {
					token = "RETURN_STMT";
				}
			} // end outer if
		} // end for

		// if no keyword is found, assume that it is a variablie identifier
		if (!key) {
			token = "ID";
			lexeme = val;
		}

	}

	// sets the token for a delimeters using the ambiguous DELIMITER tag for the
	// token name
	public void setDelToken(char val) {

		if (val == ';') {
			token = "END_DEL";
			lexeme += val;
			return;
		} else if (val == '(') {
			token = "OPEN_PAR";
			lexeme += val;
		} else if (val == ')') {
			token = "CLOSE_PAR";
			lexeme += val;
		} else if (val == '{') {
			token = "OPEN_CURBRACK";
			lexeme += val;
		} else if (val == '}') {
			token = "CLOSE_CURBRACK";
			lexeme += val;
		} else if (val == '[') {
			token = "OPEN_BRACK";
			lexeme += val;
		} else if (val == ']') {
			token = "CLOSE_BRACK";
			lexeme += val;
		}

	}

	// Set the Token as an operator for single character operators
	public void setOpToken(char val) {
		for (int i = 0; i < OPERATORS.length; i++) {
			// all cases of single char operators
			if (OPERATORS[i] == val) {
				lexeme += val;

				if (val == '=') {
					token = "ASSIGN_OP";
				} else if (val == '+') {
					token = "ADD_OP";
				} else if (val == '-') {
					token = "SUB_OP";
				} else if (val == '*') {
					token = "MULT_OP";
				} else if (val == '/') {
					token = "DIV_OP";
				} else if (val == '!') {
					token = "NEGATE_OP";
				}
			}
		}
	}

	// creates token for logical operators that have 2 or more chars
	public void setOpToken(String val) {
		// loops through all operators and checks that the second char is a
		// valid op char
		// do not need to check the first char because the first op must be a
		// valid op to get to this point
		for (int i = 0; i < OPERATORS.length; i++) {
			// may need to add +=, ++, etc, not sure if required
			if (OPERATORS[i] == (val.charAt(1))) // check if the second char in
													// double operator is
													// correct
			{

				lexeme = val;

				if (val.equals(">=")) {

					token = "GREATER_OP";
				} else if (val.equals("<=")) {
					token = "LESS_OP";
				} else if (val.equals("==")) {

					token = "EQUAL_OP";
				} else if (val.equals("!=")) {
					token = "LOGNOT_OP";
				}

			} // end outer if

		}
	}

	// creates token for int or float
	public void setNumToken(String val) {

		// default token to INT
		token = "INT";
		lexeme = val;
		// search each char in string for a period, if so set token to FLOAT
		for (int i = 0; i < val.length(); i++) {
			if (val.charAt(i) == '.') {
				token = "FLOAT";
			}
		}
	}

	// Set token for String literal
	public void setString(String val) {
		token = "STR_LIT";
		lexeme = val;
	}

	public void setStringErrorToken() {
		token = "STRING_ERROR";
		lexeme = "ERROR";
	}

	// Returns the lexeme and token as a string and formatted to appropriate
	// column width
	public String toString() {
		// if the lexeme or token matches case, add extra tab for consistent
		// formatting on output
		if (token.equals("INT") || token.equals("FLOAT") || Character.isDigit(token.charAt(0)) || lexeme.equals("if")
				|| lexeme.equals("int") || lexeme.equals("+") || lexeme.equals("-") || lexeme.equals("do")) {
			return token + "\t\t" + lexeme;
		}
		return token + "\t" + lexeme;
	}

	// output for syntax analyzer
	public String syntaxToString() {
		return token;

	}
}
