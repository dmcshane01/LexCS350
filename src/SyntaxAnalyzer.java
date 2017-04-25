import java.io.BufferedReader;
import java.io.IOException;

public class SyntaxAnalyzer {

	private BufferedReader program;
	private boolean isValid;

	public SyntaxAnalyzer(BufferedReader program) throws IOException {
		isValid = true;
		this.program = program;
		analyze();
	}

	public void analyze() throws IOException {
		String temp = next();

		// ignore everything before main
		while (!temp.equals("PROGRAM_START")) {

			temp = next();
		}

		if (!checkMain()) {
			System.out.println("ERROR: Main not declared correctly");
			isValid = false;
			System.exit(0);
		}
		else{
			System.out.println("(MAIN_DECLARATION)");
		}
	}

	private String next() throws IOException {
		return program.readLine();
	}

	// checks if the maind eclaration is correct ie: int main(void){
	private boolean checkMain() throws IOException {
		String temp;
		temp = next();
		if (temp.equals("OPEN_PAR")) {
			temp = next();
			if (temp.equals("ID")) {
				temp = next();
				if (temp.equals("CLOSE_PAR")) {
					temp = next();
					if (temp.equals("OPEN_CURBRACK")) {
						// if it reaches this point then main was used properly
						return true;
					}
				}
			}
		}

		// if main wasn't correct return false
		return false;
	}

	public boolean isDel(String temp) {
		if (temp.equals("END_DEL") || temp.equals("OPEN_CURBRACK") || temp.equals("CLOSE_CURBRACK")
				|| temp.equals("CLOSE_PAR") || temp.equals("OPEN_PAR")) {
			return true;
		}
		return false;
	}

	// nextStatement gets the next token and tries to deduce which type of
	// statement it is.
	public String nextStatement() throws IOException {
		String temp = next();
		String output = "";

		// if the next token is a delimter skip it/them because they should have
		// been handled elsewhere
		while (isDel(temp)) {
			temp = next();
		}

		// System.out.println("\nDEBUGGING: START OF METHOD CALL: " + temp +
		// "\n");

		if (temp.equals("RETURN_STMT")) {
			return "END_OF_FUNCTION";
		}
		// if the first token is int/float/char then it must be an assignment
		if (temp.equals("INT_DEC") || temp.equals("FLOAT_DEC") || temp.equals("CHAR_DEC")) {
			output += "(" + temp + ")";
			temp = next();

			output += "(" + temp + ")";
			// if the assignment is simple int = 5
			if (temp.equals("ASSIGN_OP")) {
				output += "=";
				temp = next();
				if (temp.equals("INT") || temp.equals("FLOAT") || temp.equals("CHAR")) {
					output += temp;
					temp = next();

					if (!temp.equals("END_DEL")) {
						output = "SYNTAX ERROR";
						isValid = false;
					}

					return output;
				}
			}
			while (temp.equals("ID")) {
				output += "(" + temp + ")";
				temp = next();
			}

			if (!temp.equals("END_DEL")) {
				isValid = false;
				output = "SYNTAX ERROR: MISSING SEMICOLON";
			}
			return output;
		} // end of int/float/char assignment check

		// checks to see if statement is the start of a method call
		String conditionCheck = checkMethodCall(temp);
		if (!conditionCheck.equals("0")) {
			return conditionCheck;
		}

		// check if its a variable assignment
		conditionCheck = checkVarAssignStatement(temp);
		if (!conditionCheck.equals("0")) {
			return conditionCheck;
		}

		// checks to see if it is the start of a conditional or loop statement
		// if it returns string 0 then it is not
		conditionCheck = checkConditionStatement(temp);
		if (!conditionCheck.equals("0")) {
			return conditionCheck;
		}

		return null;
	}

	// checks if the statement is a simple assignment statement for variables
	// eg: x = 5
	public String checkVarAssignStatement(String val) throws IOException {

		String temp = val;
		String output = "";

		if (temp.equals("ID")) {
			temp = next();
			if (temp.equals("ASSIGN_OP")) {
				temp = next();
				// assignment can be another variable, or int,float,char
				if (temp.equals("ID") || temp.equals("INT") || temp.equals("FLOAT") || temp.equals("CHAR")) {
					temp = next();

					// if there is a del after then it is a valid assign
					// statment
					if (temp.equals("END_DEL")) {
						return "(ASSIGN_STMT)";
						// if the next token is not an end delimiter check to
						// see if its an operand
					} else if (temp.equals("SUB_OP") || temp.equals("ADD_OP") || temp.equals("MULT_OP")
							|| temp.equals("DIV_OP")) {
						temp = next();
						// if it is an operand check to see if its an assignment
						// in an assignment
						if (temp.equals("ID") || temp.equals("INT") || temp.equals("FLOAT") || temp.equals("CHAR")) {

							return "(ASSIGN_STMT)";
						}
					}

				}
			}
		}

		return "0";
	}

	// checks to see if the token is the start of a conditional statement. If So
	// it returns the parsed statement
	// if not it returns String 0
	public String checkMethodCall(String val) throws IOException {
		String temp = val;
		String output = "";

		// analyze syntax of write function
		if (temp.equals("WRITE_FUNC")) {
			output += "(" + temp + ")";
			temp = next();
			if (temp.equals("OPEN_PAR")) {
				temp = next();
				if (temp.equals("STR_LIT")) {
					output += "(" + temp + ")";
					temp = next();
					if (temp.equals("CLOSE_PAR")) {
						return output;
					}
					// if a variablie is passed as a write func argument
					else if (temp.equals("ID")) {
						output += "(ID_TO-WRITE)";
						return output;

					}

				}
			}
		} // end outer if

		// analyze syntax of read function
		if (temp.equals("READ_FUNC")) {
			output += "(" + temp + ")";
			temp = next();

			// because read can be in parantheses or not in parentheses, check
			// that it has an open par or an ID
			if (temp.equals("OPEN_PAR") || temp.equals("ID")) {
				output += "(" + temp + ")";
				temp = next();
				// if so loop through all identifiers being read
				while (temp.equals("ID")) {
					output += "(" + temp + ")";
					temp = next();
				} // end while

				// next token that is not an ID must be a closing parentheses or
				// and ending delimiter, if not syntax error
				if (!temp.equals("CLOSE_PAR") && !temp.equals("END_DEL")) {
					System.out.println(temp);

					System.out.println("SYNTAX ERROR");
				}
				output += "(" + temp + ")";
				return output;
			} // end 2nd if

		} // end 1st if

		return "0";
	}// end of checkmethodcall

	// checks to see if the token is the start of a conditional statement. If So
	// it returns the parsed statement
	// if not it returns String 0
	public String checkConditionStatement(String val) throws IOException {
		String temp = val;
		String output = "";

		// checks while loop, does not check for parentheses as they are not
		// used in this syntax
		if (temp.equals("WHILE_LOOP")) {
			output += "(" + temp + ")";
			temp = next();
			if (temp.equals("ID")) {
				output += "->(" + temp + ")";
				temp = next();
				if (temp.equals("EQUAL_OP") || temp.equals("GREATER_OP") || temp.equals("LESS_OP")
						|| temp.equals("LOGNOT_OP")) {
					output += "(" + temp + ")";
					temp = next();
					if (temp.equals("ID") || temp.equals("INT") || temp.equals("FLOAT")) {
						output += "(" + temp + ")";
						return output;
						// valid
					}
				}
			}
		} // end main if
		if (temp.equals("DO_STMT")) {
			output += "(" + temp + ")";
			temp = next();
			output += "->" + nextStatement();
			return output;

		} // end 1st if

		if (temp.equals("IF_STMT")) {

			output += "(" + temp + ")";
			temp = next();
			if (temp.equals("ID")) {
				output += "->(" + temp + ")";
				temp = next();
				if (isComp(temp)) {
					output += "(" + temp + ")";
					temp = next();
					if (temp.equals("ID") || temp.equals("INT") || temp.equals("FLOAT") || temp.equals("CHAR")) {
						output += "(" + temp + ")";
						return output;
					}

				}
			}
			isValid = false;
			return "SYNTAX ERROR";
		} // end 1st if

		// if there is an else then return the token so that the next call can
		// parse the inner statement
		if (temp.equals("ELSE_STMT")) {
			return "(" + temp + ")";
		}

		return "0";

	}

	public boolean isComp(String temp) {
		if (temp.equals("EQUAL_OP") || temp.equals("GREATER_OP") || temp.equals("LESS_OP")
				|| temp.equals("LOGNOT_OP")) {
			return true;
		} else
			return false;
	}

	public boolean isValid() {
		return isValid;
	}
}
