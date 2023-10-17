package app;

import stack.StackUnderflowException;
import util.Evaluator;
import util.InfixToPostfixConverter;
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

        // postfix evaluation test
        Evaluator evaluate = new Evaluator();
        String postfixExpression2 = "42 5 + 7 *";
        double result = evaluate.evaluatePostfix(postfixExpression2);
        System.out.println("\n\nEvaluation of Postfix Expressions");
        System.out.println("===================================");
        System.out.println("Postfix expression: " + postfixExpression2);
        System.out.println("Result: " + result + "\n");
        execute.showTable(evaluate.getTable(), 2);
    }
} // end of Test class
