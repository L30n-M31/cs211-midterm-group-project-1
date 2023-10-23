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
        if (execute.validateString(infixExpression)) {
            JOptionPane.showMessageDialog(null, "Invalid Expression");
            return null;
        }

        for (int i = 0; i < infixExpression.length(); i++) {
            String token = String.valueOf(infixExpression.charAt(i));
            if (execute.isAComparisonOperator(token)) {
                token = execute.getComparisonOperator(infixExpression, i);
                if (token.length() == 2) {
                    i++;
                }
            }
            if (execute.isAnOperand(token)) {
                postfixExpression.append(token);
            } else {
                while (!operatorStack.isEmpty() && execute.precedence(operatorStack.peek(), token)) {
                    postfixExpression.append(operatorStack.pop());
                }
                if (!token.equals(")")) {
                    operatorStack.push(token);
                } else {
                    while (!operatorStack.peek().equals("(")) {
                        postfixExpression.append(operatorStack.pop());
                    }
                    operatorStack.pop(); // pop the open parenthesis of the stack
                }
            }
            execute.updateTable(token, postfixExpression, operatorStack, table);
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
