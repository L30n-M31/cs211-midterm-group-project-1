package util;

import stack.Stack;
import stack.StackUnderflowException;

import java.util.ArrayList;

public class Operations {

    public boolean isAnOperand(String expressionChar) {
        char token = expressionChar.charAt(0);
        return Character.isLetterOrDigit(token);
    } // end of isAnOperand method

    public boolean precedence(String stackChar, String expressionChar) {
        char stackToken = stackChar.charAt(0);
        char expressionToken = expressionChar.charAt(0);
        return ((stackToken == '^' || stackToken == '*' || stackToken == '/') && (expressionToken == '+' || expressionToken == '-'));
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

    public void updateTable(String token, StringBuilder expression, Stack<String> operatorStack, ArrayList<String> table) throws StackUnderflowException {
        table.add(token + "~" + expression + "~" + operatorStack.stackToString(""));
    } // end of updateTable method

    public void updateTable(String toke, String firstOperand, String secondOperand, String value, Stack<Double> operandStack, ArrayList<String> table) throws StackUnderflowException {
        table.add(toke + "~" + firstOperand + "~" + secondOperand + "~" + value + "~" + operandStack.stackToString(", "));
    } // end of updateTable method

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
} // end of Operations class
