package app;

import stack.StackUnderflowException;
import util.EvaluatePostfixExpression;
import util.InfixToPostfixConverter;
import util.InfixToPrefixConverter;
import util.Operations;

public class Test {
    public static void main(String[] args) {
        Test programFunction;
        try {
            programFunction = new Test();
            programFunction.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of main method

    public void run() throws StackUnderflowException {
        Operations execute = new Operations();

        // infix to postfix conversion test
        InfixToPostfixConverter infixToPostfix = new InfixToPostfixConverter();
        String infixExpression = "((A–(B+C))*D)^(E+F)";
        String postfixExpression = infixToPostfix.convert(infixExpression);
        System.out.println("Conversion of Infix Expression to Postfix Expression");
        System.out.println("====================================================");
        System.out.println("Infix expression: " + infixExpression);
        System.out.println("Postfix expression: " + postfixExpression + "\n");
        execute.showTable(infixToPostfix.getTable(), 1);

        // infix to prefix conversion test
        InfixToPrefixConverter infixToPrefix = new InfixToPrefixConverter();
        String infixExpression2 = "((A–(B+C))*D)^(E+F)";
        String prefixExpression = infixToPrefix.convert(infixExpression2);
        System.out.println("\n\nConversion of Infix Expression to Prefix Expression");
        System.out.println("===================================================");
        System.out.println("Infix expression: " + infixExpression2);
        System.out.println("Prefix expression: " + prefixExpression + "\n");
        execute.showTable(infixToPrefix.getTable(), 1);

        // postfix evaluation test
        EvaluatePostfixExpression postfix = new EvaluatePostfixExpression();
        String postfixExpression2 = "42 5 + 7 *";
        double result = postfix.evaluate(postfixExpression2);
        System.out.println("\n\nEvaluation of Postfix Expressions");
        System.out.println("===================================");
        System.out.println("Postfix expression: " + postfixExpression2);
        System.out.println("Result: " + result + "\n");
        execute.showTable(postfix.getTable(), 2);
    }
} // end of Test class
