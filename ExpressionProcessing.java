import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

class ExpressionProcessing{
	public static void main(String[] args) throws Exception {	  //read file & process each line
  	FileReader input = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(input);
		String line = br.readLine();
   		while (line != null) {
      	System.out.println("Now processing line " + line);//starts with b
        if (line.startsWith("b")) {
        	System.out.println("The expression is " + isBalanced(line.substring(1)));//starts with an e
        }
        else if (line.startsWith("e")) {
        	System.out.println("Result of this expression is " + evaluatePostfixExpression(line.substring(1)));
        }
        else {
        	System.out.println("The line does not start with either 'e' or 'b'.");
        }
        System.out.println("-------------------------------------");
        line = br.readLine();
     }
     br.close();
  }

  /*The method should consider only left '(' and right parentheses ')'. Other characters in the
     * parameter string expr should be ignored.
     * The method must use a Stack to determine
     * if the incoming string contains a balanced
     * expression or not.
     *
     * @param expr
     * @return true if the expression in the String is balanced
     * otherwise, false.*/
  static boolean isBalanced(String expr){
  	Stack aStack = new Stack();
    	for (int i = 0; i < expr.length(); i++){	//iterates through each char
      	char currentChar = expr.charAt(i);	//push ( on stack
        if (currentChar == '('){
       		aStack.push(currentChar);
				}
        else if (currentChar == ')'){	//pop from stack for )
        	if (aStack.isEmpty()){
          	return false;	// false if no pair
          }
          else{
          	aStack.pop();
          }
       	}
      }
     	return aStack.isEmpty();
    }

    /*Evaluate the postfix expression in the incoming String.
     * Operators and operands are separated by spaces.
     *
     * @param
     * @return the evaluated postfix value.*/
    static double evaluatePostfixExpression(String expression){
    	Stack aStack = new Stack();
      StringTokenizer tokenizer = new StringTokenizer(expression);

      while (tokenizer.hasMoreTokens()){	//process each token
      	String token = tokenizer.nextToken();	//push operand on stack
        if (isNumber(token)){
        	aStack.push(Double.parseDouble(token));
        }
        else {
        	double result;
          char operator = token.charAt(0);
          switch (operator){	//perform operation
          	case '+':
            	double operand2 = (double) aStack.pop();
              double operand1 = (double) aStack.pop();
              result = operand1 + operand2;
              break;
           case '-':
              double operand3 = (double) aStack.pop();
              double operand4 = (double) aStack.pop();
              result = operand4 - operand3;
              break;
           case '*':
              double operand5 = (double) aStack.pop();
              double operand6 = (double) aStack.pop();
              result = operand5 * operand6;
              break;
           case '/':
              double operand7 = (double) aStack.pop();
              double operand8 = (double) aStack.pop();
              result = operand8 / operand7;
              break;
           case '!':
              double operand9 = (double) aStack.pop();
              result = factorial(operand9);
              break;
           default:
              throw new IllegalArgumentException("Invalid operator: " + operator);
           }
           aStack.push(result); //push result back to stack
         	}
       }
       return (double) aStack.pop(); //return final result
    }

    /*The method returns the factorial of a number.
     *
     * @param num
     * @return factorial of num */
    static double factorial(double num){
    	if (num == 0 || num == 1){
      	return 1;
      }
      else{
      	return num * factorial(num - 1);
      }
    }
  
    /*The method checks if a sting is a double
     * precision number.
     *
     * @param s
     * @return true if the param string is a double precision number
     * false otherwise. */
    static boolean isNumber(String s) {
        s = s.trim();
        if (s == null || s.equals("")) {
            return false;
        }
        try {
            double num = Double.parseDouble(s);
        } catch (NumberFormatException excp) {
            return false;
        }
        return true;
    }
}