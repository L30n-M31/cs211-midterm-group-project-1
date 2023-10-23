package util;

import gui.GraphicEditor;
import stack.Stack;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Operations {

    public boolean isOperand(String expressionChar) {
        char symbol = expressionChar.charAt(0);
        return Character.isLetterOrDigit(symbol);
    } // end of isAnOperand method

    public boolean isOperator(String expressionChar) {
        char operator = expressionChar.charAt(0);
        return operator == '^' || operator == '/' || operator == '*' || operator == '+' || operator == '-';
    } // end of isAnOperator method

    public boolean isComparisonOperator(String expressionChar) {
        char operator = expressionChar.charAt(0);
        return operator == '=' || operator == '!' || operator == '>' || operator == '<';
    } // end of isAComparisonOperator

    public String getComparisonOperator(String expression, int index) {
        String currentSymbol = String.valueOf(expression.charAt(index));
        String nextSymbol = String.valueOf(expression.charAt(index + 1));

        if (isComparisonOperator(nextSymbol)) {
            currentSymbol += nextSymbol;
        }
        return currentSymbol;
    } // end of getComparisonOperator

    public boolean precedence(String stackSymbol, String expressionSymbol) {
        return (stackSymbol.equals("^") || stackSymbol.equals("*") || stackSymbol.equals("/") || stackSymbol.equals("%"))
                && (expressionSymbol.equals("+") || expressionSymbol.equals("-"));
    } // end of precedence method

    public boolean isBooleanValue(String value) {
        return value.equals("1.0001") || value.equals("1.00001");
    } //end of isBooleanValue method

    public double evaluateOperands(double firstOperand, double secondOperand, String symbol) {
        switch (symbol) {
            case "^" -> { return (int) Math.pow(firstOperand, secondOperand); }
            case "*" -> { return firstOperand * secondOperand; }
            case "/" -> {
                if (secondOperand != 0) return firstOperand / secondOperand;
                throw new ArithmeticException("Division by zero");
            }
            case "+" -> { return  firstOperand + secondOperand; }
            case "-" -> { return firstOperand - secondOperand; }
            case "%" -> { return firstOperand % secondOperand; }
            case "==" -> { return firstOperand == secondOperand ? 1.0001 : 1.00001; }
            case "!=" -> { return firstOperand != secondOperand ? 1.0001 : 1.00001; }
            case "<" -> { return firstOperand < secondOperand ? 1.0001 : 1.00001; }
            case ">" -> { return firstOperand > secondOperand ? 1.0001 : 1.00001; }
            case "<=" -> { return firstOperand <= secondOperand ? 1.0001 : 1.00001; }
            case ">=" -> { return firstOperand >= secondOperand ? 1.0001 : 1.00001; }
            default -> { return 0; }
        }
    } // end of evaluateOperands

    public String getStringResult(double result) {
        return isBooleanValue(String.valueOf(result)) ? (result == 1.0001 ? "True" : "False") : String.valueOf(result);
    } // end of showFinalResult

    public boolean validateString(String expression) {
        int openParenthesis = 0;
        int closedParenthesis = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (isOperand(String.valueOf(expression.charAt(i))) && isOperator(String.valueOf(expression.charAt(i + 1))))
                return false;
            if (expression.charAt(i) == '(') openParenthesis++;
            if (expression.charAt(i) == ')') closedParenthesis++;
        }
        return openParenthesis == closedParenthesis;
    } // end of validateString method

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

    public boolean determineEvaluation(String expression) {
        String firstStr = String.valueOf(expression.charAt(0));
        return isOperand(firstStr);
    } // end of determineEvaluation method

    public void updateTable(String symbol, StringBuilder expression, Stack<String> operatorStack, ArrayList<String> table) {
        table.add(symbol + "~" + expression + "~" + operatorStack.stackToString(""));
    } // end of updateTable method

    public void updateTable(String symbol, double firstOperand, double secondOperand, double value, Stack<Double> operandStack, ArrayList<String> table) {
        String firstOperandStr = Double.isNaN(firstOperand) ? "" : String.valueOf(firstOperand);
        String secondOperandStr = Double.isNaN(secondOperand) ? "" : String.valueOf(secondOperand);
        String valueStr = Double.isNaN(value) ? "" : String.valueOf(value);

        table.add(symbol + "~" + firstOperandStr + "~" + secondOperandStr + "~" + valueStr + "~" + operandStack.stackToString(", "));
    } // end of updateTable method

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
                    if (!Objects.equals(array[3], "")) {
                        data[i][3] = getStringResult(Double.parseDouble(array[3]));
                    } else {
                        data[i][3] = array[3];
                    }
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
