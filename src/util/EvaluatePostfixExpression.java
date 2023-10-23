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
        String[] charArray = postfixExpression.split(" ");
        double value;

        for (String s : charArray) {
            String symbol = String.valueOf(s);
            if (execute.isOperand(symbol)) {
                operandStack.push(Double.valueOf(symbol));
                execute.updateTable(symbol, Double.NaN, Double.NaN, Double.NaN, operandStack, table);
            } else {
                double secondOperand = operandStack.pop();
                double firstOperand = operandStack.pop();
                value = execute.evaluateOperands(firstOperand, secondOperand, symbol);
                operandStack.push(value);
                execute.updateTable(symbol, firstOperand, secondOperand, value, operandStack, table);
            }
        }
        return operandStack.pop();
    } // end of result class

    public ArrayList<String> getTable() {
        return table;
    }
} // end of Evaluator class
