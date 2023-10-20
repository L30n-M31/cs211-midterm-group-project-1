package util;

import stack.Stack;
import stack.StackUnderflowException;

import java.util.ArrayList;

public class EvaluatePostfixExpression {
    private ArrayList<String> table;
    private final Operations execute;

    public EvaluatePostfixExpression() {
        execute = new Operations();
    } // end of default constructor

    public double evaluate(String postfixExpression) throws StackUnderflowException {
        table = new ArrayList<>();
        Stack<Double> operandStack = new Stack<>();
        String[] tokenArray = postfixExpression.split(" ");
        double result = 0;

        for (String s : tokenArray) {
            String token = String.valueOf(s);
            if (execute.isAnOperand(token)) {
                operandStack.push(Double.valueOf(token));
                execute.updateTable(token, "", "", "", operandStack, table);
            } else {
                double secondOperand = operandStack.pop();
                double firstOperand = operandStack.pop();
                result = execute.evaluateOperands(firstOperand, secondOperand, token);
                operandStack.push(result);
                execute.updateTable(token, String.valueOf(firstOperand), String.valueOf(secondOperand), String.valueOf(result), operandStack, table);
            }
        }
        return result;
    } // end of result class

    public ArrayList<String> getTable() {
        return table;
    }
} // end of Evaluator class
