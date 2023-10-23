package util;

import stack.Stack;
import stack.StackUnderflowException;

import javax.swing.*;
import java.util.ArrayList;

public class InfixToPostfixConverter {
    private ArrayList<String> table;
    private final Operations execute;

    public InfixToPostfixConverter() {
        execute = new Operations();
    } // end of default constructor

    public String convert(String infixExpression) throws StackUnderflowException {
        table = new ArrayList<>();
        StringBuilder postfixExpression = new StringBuilder();
        Stack<String> operatorStack = new Stack<>();

        // checks if the given infix expression is correct
        if (!execute.validateString(infixExpression)) {
            JOptionPane.showMessageDialog(null, "Invalid Expression");
            return null;
        }

        for (int i = 0; i < infixExpression.length(); i++) {
            String symbol = String.valueOf(infixExpression.charAt(i));
            if (execute.isComparisonOperator(symbol)) {
                symbol = execute.getComparisonOperator(infixExpression, i);
                if (symbol.length() == 2) {
                    i++;
                }
            }
            if (execute.isOperand(symbol)) {
                postfixExpression.append(symbol);
            } else {
                while (!operatorStack.isEmpty() && execute.precedence(operatorStack.peek(), symbol)) {
                    postfixExpression.append(operatorStack.pop());
                }
                if (!symbol.equals(")")) {
                    operatorStack.push(symbol);
                } else {
                    while (!operatorStack.peek().equals("(")) {
                        postfixExpression.append(operatorStack.pop());
                    }
                    operatorStack.pop(); // pop the open parenthesis of the stack
                }
            }
            execute.updateTable(symbol, postfixExpression, operatorStack, table);
        }
        while (!operatorStack.isEmpty()) {
            postfixExpression.append(operatorStack.pop());
            execute.updateTable("", postfixExpression, operatorStack, table);
        }
        return postfixExpression.toString();
    } // end of convert method

    public ArrayList<String> getTable() {
        return table;
    }
} // end of InfixToPostfix class
