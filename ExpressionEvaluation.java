import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

class ExpressionEvaluation{
 	private static int stackSize = 0;	//counter variable to keep track of stack size
  
  public static void main(String[] args) throws Exception {
  	FileReader input = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(input);
    
		String line;

    while ((line = br.readLine()) != null) {
    	if (!line.trim().isEmpty()) {
        processLine(line);
      }
    }
		br.close();
    input.close();
  }
  
  static void processLine(String line) {
  	System.out.println("Now processing line: " + line);

    if (line.startsWith("e")) {	//process postfix expression
   		double result = evaluatePostfixExpression(line.substring(2));
      if (result != Double.NEGATIVE_INFINITY) {	// Print an error message if evaluation fails
      	System.out.println("Result of this expression is: " + result);
      }
    }
  	else if (line.startsWith("b")) {	//process balanced expression
    	if (isBalanced(line.substring(2))) {
      	System.out.println("The expression is: balanced");
      }
      else {
      	System.out.println("The expression is: not balanced");
      }
    }
  	else {
    	System.out.println("The line does not start with either 'e' or 'b'.");
    }
    System.out.println("----------");
	}
  
  /**
   * The method should consider only left '(' and
   * right parentheses ')'. Other characters in the
   * parameter string expr should be ignored.
   * The method must use a Stack to determine
   * if the incoming string contains a balanced
   * expression or not. See
   * the assignment description for further details.   
   * @param expr
   * @return true if the expression in the String is balanced
   * otherwise, false.
   */
  static boolean isBalanced(String expr) {
  	Stack aStack = new Stack();	//create stack to track open parentheses

    for (int i = 0; i < expr.length(); i++) {	//iterate through each char
    	char ch = expr.charAt(i);
      if (ch == '(') { 	//if an open parenthesis is found, push it onto stack
      	aStack.push(ch);
        stackSize++;	//increment stack size when pushing
      }
      else if (ch == ')') {	//if a close parenthesis is found
      	if (aStack.isEmpty()|| stackSize == 0) {	//if stack is empty, no matching open parenthesis
        	System.out.println("Trying to pop when stack is empty");
          return false;
        }
        else {	//pop an open parenthesis from the stack
        	aStack.pop();
          stackSize--;	//decrement stack size when popping
        }
      }	//ignore other chars (not parentheses)
    }
    return aStack.isEmpty() && stackSize == 0;	//if stack is empty, all parentheses are balanced
	}
    
  /**
   * Evaluate the postfix expression in the incoming String.
   * Operators and operands are separated by spaces. See
   * the assignment description for further details.
   * @param postfix
   * @return the evaluated postfix value.
   */  
  static double evaluatePostfixExpression(String expression) {
    Stack aStack = new Stack();	//create stack to hold operands during evaluation
    StringTokenizer tokenizer = new StringTokenizer(expression, " ");	//tokenize expression using spaces
    
		while (tokenizer.hasMoreTokens()) {	//process each token in the expression
    	String token = tokenizer.nextToken();

			if (isNumber(token)) {	//if the token is number, push it onto the stack
      	aStack.push(Double.parseDouble(token));
        stackSize++;	//increment stack size when pushing
      }
      else {	//if the token is operator
      	if (token.equals("!")) {		//handle factorial operation
        	if (aStack.isEmpty()) {
          	System.out.println("Error: Not enough operands for factorial operation");
            return Double.NEGATIVE_INFINITY;
          }
          double operand = (double) aStack.pop();
          aStack.push(factorial(operand));
        }
        else if (stackSize < 2) {	//check if theres enough operands for binary operators
          System.out.println("Error: Not enough operands for operator " + token);
          return Double.NEGATIVE_INFINITY;
        }
        else {	//pop two operands and perform the operation
        	double operand2 = (double) aStack.pop();
          double operand1 = (double) aStack.pop();
          switch (token) {
           	case "+":
            	aStack.push(operand1 + operand2);
              break;
            case "-":
            	aStack.push(operand1 - operand2);
              break;
            case "*":
              aStack.push(operand1 * operand2);
              break;
            case "/":
              if (operand2 == 0) {	//check for division by zero
          	    System.out.println("Error: Division by zero");  
                return Double.NEGATIVE_INFINITY;
              }
              aStack.push(operand1 / operand2);
              break;
       	    default:	//invalid operator
        	    System.out.println("Invalid operator: " + token);
              return Double.NEGATIVE_INFINITY;
			   	}
    		}
    	}
    }
    if (stackSize == 1) {	//check if there is exactly one value left on the stack
    	return (double) aStack.pop();
    }
    else {	//not enough operators for given operands
    	System.out.println("Error: Not enough operators");
      return Double.NEGATIVE_INFINITY;
    }
	}

  
   /**
   * The method returns the factorial of a number.
   * @param num
   * @return factorial of num
   */

  static double factorial(double num) {
   if (num < 0) {		//check if the # is negative
   	System.out.println("Error: Factorial of a negative number");
    return Double.NEGATIVE_INFINITY;
   }
   if (num == 0 || num == 1) {		//base cases: 0! and 1! are both 1
   	return 1;
   }
   else {		//recursive case: num! = num * (num-1)!
   	return num * factorial(num - 1);
   }
  }
  
  /**
   * The method checks if a sting is a double 
   * precision number.
   * @param s
   * @return true if the param string is a double precision number
   *         false otherwise.
   */
  static boolean isNumber(String s) {
    s = s.trim();
    if (s==null || s=="")
      return false;
    
    try{
      double num = Double.parseDouble(s);
    }catch(NumberFormatException excp){
      return false;
    }
    return true;
  }
}