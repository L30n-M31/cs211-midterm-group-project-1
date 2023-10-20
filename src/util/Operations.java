package util;

import gui.GraphicEditor;
import stack.Stack;
import stack.StackUnderflowException;

import javax.swing.*;
import java.util.ArrayList;

public class Operations {

    public boolean isAnOperand(String expressionChar) {
        char token = expressionChar.charAt(0);
        return Character.isLetterOrDigit(token);
    } // end of isAnOperand method

    public boolean isAnOperator(String expressionChar) {
        char token = expressionChar.charAt(0);
        return token == '^' || token == '/' || token == '*' || token == '+' || token == '-';
    }

    public boolean precedence(String stackChar, String expressionChar) {
        char stackToken = stackChar.charAt(0);
        char expressionToken = expressionChar.charAt(0);
        return (stackToken == '^' || stackToken == '*' || stackToken == '/') && (expressionToken == '+' || expressionToken == '-');
    } // end of precedence method

    public double evaluateOperands(double firstOperand, double secondOperand, String token) {
        switch (token) {
            case "^" -> { return (int) Math.pow(firstOperand, secondOperand); }
            case "*" -> { return firstOperand * secondOperand; }
            case "/" -> { return firstOperand / secondOperand; }
            case "+" -> { return  firstOperand + secondOperand; }
            case "-" -> { return firstOperand - secondOperand; }
            default -> { return 0; }
        }
    } // end of evaluateOperands

    public boolean validateString(String expression) {
        int openParenthesis = 0;
        int closedParenthesis = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (isAnOperator(String.valueOf(expression.charAt(i))) && isAnOperator(String.valueOf(expression.charAt(i + 1))))
                return true;
            if (expression.charAt(i) == '(') openParenthesis++;
            if (expression.charAt(i) == ')') closedParenthesis++;
        }
        return openParenthesis != closedParenthesis;
    }

    public String reverseExpression(String expression) {
        StringBuilder reversedExpression = new StringBuilder(expression).reverse();
        return reversedExpression.toString();
    } // end of reverseExpression method

    public void reverseArray(String[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            String temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    } // end of reverseArray method

    public void updateTable(String token, StringBuilder expression, Stack<String> operatorStack, ArrayList<String> table) throws StackUnderflowException {
        table.add(token + "~" + expression + "~" + operatorStack.stackToString(""));
    } // end of updateTable method

    public void updateTable(String toke, String firstOperand, String secondOperand, String value, Stack<Double> operandStack, ArrayList<String> table) throws StackUnderflowException {
        table.add(toke + "~" + firstOperand + "~" + secondOperand + "~" + value + "~" + operandStack.stackToString(", "));
    } // end of updateTable method

    /**
     * Method to show the table for the Test class
     */
    public void showTable(ArrayList<String> table, int option) {
        if (option == 1) {
            System.out.printf("%-9s%-21s%-17s%n", "Symbol", "Postfix Expression", "Operator Stack");
            System.out.printf("%-9s%-21s%-17s%n", "=======", "===================", "===============");
            for (String s : table) {
                String[] array = s.split("~");
                if (array.length == 3) {
                    System.out.printf("%-9s%-21s%-17s%n", array[0], array[1], array[2]);
                } else {
                    System.out.printf("%-9s%-21s%n", array[0], array[1]);
                }
            }
        } else {
            System.out.printf("%-9s%-12s%-12s%-12s%-22s%n", "Symbol", "Operand 1", "Operand 2", "Value", "Operand Stack");
            System.out.printf("%-9s%-12s%-12s%-12s%-22s%n", "=======", "==========", "==========", "==========", "====================");
            for (String s : table) {
                String[] array = s.split("~");
                if (array.length == 5) {
                    System.out.printf("%-9s%-12s%-12s%-12s%-22s%n", array[0], array[1], array[2], array[3], array[4]);
                } else {
                    System.out.printf("%-9s%-12s%-12s%-12s%n", array[0], array[1], array[2], array[3]);
                }
            }
        }
    } // end of showTable method

    public void displayTable(ArrayList<String> table, int option) {
        GraphicEditor edit = new GraphicEditor();

        String tableTitle = null;
        String[] columns = new String[0];
        String[][] data = new String[0][0];

        switch (option) {
            case 1 -> {
                tableTitle = "Convert Table";
                columns = new String[]{"Symbol", "Expression", "Operator"};
                data = new String[table.size()][3];

                for (int i = 0; i < table.size(); i++) {
                    String[] array = table.get(i).split("~");

                    data[i][0] = array[0];
                    data[i][1] = array[1];
                    if (array.length == 3)
                        data[i][2] = array[2];
                    else
                        data[i][2] = "";
                }
            }
            case 2 -> {
                tableTitle = "Evaluate Table";
                columns = new String[]{"Symbol", "Operand 1", "Operand 2", "Value", "Operand Stack"};
                data = new String[table.size()][5];

                for (int i = 0; i < table.size(); i++) {
                    String[] array = table.get(i).split("~");

                    data[i][0] = array[0];
                    data[i][1] = array[1];
                    data[i][2] = array[2];
                    data[i][3] = array[3];
                    if (array.length == 5)
                        data[i][4] = array[4];
                    else
                        data[i][4] = "";
                }
            }
        }
        JOptionPane.showMessageDialog(null, edit.setTable(data, columns), tableTitle, JOptionPane.PLAIN_MESSAGE);
    } // end of displayTable method
} // end of Operations class
