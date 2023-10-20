package util;

import stack.Stack;
import stack.StackUnderflowException;

import java.util.ArrayList;

public class EvaluatePrefixExpression {
    private ArrayList<String> table;
    private final Operations execute;

    public EvaluatePrefixExpression() {
        execute = new Operations();
    } // end of default constructor

    public double evaluate(String prefixExpression) throws StackUnderflowException {
        table = new ArrayList<>();
        Stack<Double> operandStack = new Stack<>();
        String[] tokenArray = prefixExpression.split(" ");
        execute.reverseArray(tokenArray);

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
    } // end of evaluate method

    public ArrayList<String> getTable() {
        return table;
    }
} // end of EvaluatePrefixExpression class
