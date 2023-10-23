package util;

import stack.Stack;
import stack.StackUnderflowException;

import javax.swing.*;
import java.util.ArrayList;

public class InfixToPrefixConverter {
    private ArrayList<String> table;
    private final Operations execute;

    public InfixToPrefixConverter() {
        execute = new Operations();
    } // end of default constructor

    public String convert(String infixExpression) throws StackUnderflowException {
        table = new ArrayList<>();
        StringBuilder prefixExpression = new StringBuilder();
        Stack<String> operatorStack = new Stack<>();

        String reversedExpression = execute.reverseExpression(infixExpression);

        // checks if the given infix expression is correct
        if (!execute.validateString(infixExpression)) {
            JOptionPane.showMessageDialog(null, "Invalid Expression");
            return null;
        }

        for (int i = 0; i < reversedExpression.length(); i++) {
            String symbol = String.valueOf(reversedExpression.charAt(i));
            if (execute.isComparisonOperator(symbol)) {
                symbol = execute.getComparisonOperator(infixExpression, i);
                if (symbol.length() == 2) {
                    i++;
                }
            }
            if (execute.isOperand(symbol)) {
                prefixExpression.insert(0, symbol);
            } else {
                while (!operatorStack.isEmpty() && execute.precedence(operatorStack.peek(), symbol)) {
                    prefixExpression.insert(0, operatorStack.pop());
                }
                if (!symbol.equals("(")) {
                    operatorStack.push(symbol);
                } else {
                    while (!operatorStack.peek().equals(")")) {
                        prefixExpression.insert(0, operatorStack.pop());
                    }
                    operatorStack.pop(); // pop the closed parenthesis of the stack
                }
            }
            execute.updateTable(symbol, prefixExpression, operatorStack, table);
        }
        while (!operatorStack.isEmpty()) {
            prefixExpression.insert(0, operatorStack.pop());
            execute.updateTable("", prefixExpression, operatorStack, table);
        }
        return prefixExpression.toString();
    } // end of convert method

    public ArrayList<String> getTable() {
        return table;
    }
} // end of InfixToPrefixConverter
