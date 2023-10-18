package gui;

import stack.StackUnderflowException;
import util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private final GraphicEditor edit = new GraphicEditor();
    private final Operations execute = new Operations();
    private JPanel mainPanel;
    private JLabel convertTitle, evaluateTitle, convertExpressionLB, evaluateExpressionLB, convertResultLB, evaluateResultLB;
    private JTextField convertExpressionTF, evaluateExpressionTF, convertResultTF, evaluateResultTF;
    private JButton convertToPostfixBT, convertToPrefixBT, evaluatePostfixBT, evaluatePrefixBT;
    private final InfixToPrefixConverter infixToPrefix = new InfixToPrefixConverter();
    private final InfixToPostfixConverter infixToPostfix = new InfixToPostfixConverter();
    private final EvaluatePostfixExpression postfix = new EvaluatePostfixExpression();
    private final EvaluatePrefixExpression prefix = new EvaluatePrefixExpression();

    public MainPanel(JFrame frame) {
        initializeComponents();
        setBounds();
        setGraphics();
        addComponentsToPanel();
        addActionListeners();
        frame.add(mainPanel);
    } // end of default constructor

    public void initializeComponents() {
        // main panel
        mainPanel = new JPanel();

        // labels
        convertTitle = new JLabel("CONVERT EXPRESSIONS");
        evaluateTitle = new JLabel("EVALUATE EXPRESSIONS");
        convertExpressionLB = new JLabel("EXPRESSION");
        evaluateExpressionLB = new JLabel("EXPRESSION");
        convertResultLB = new JLabel("RESULT");
        evaluateResultLB = new JLabel("RESULT");

        // text fields
        convertExpressionTF = new JTextField();
        evaluateExpressionTF = new JTextField();
        convertResultTF = new JTextField();
        evaluateResultTF = new JTextField();

        // buttons
        convertToPostfixBT = new JButton("<html><div align='center'>CONVERT TO<br>POSTFIX</div></html>");
        convertToPrefixBT = new JButton("<html><div align='center'>CONVERT TO<br>PREFIX</div></html>");
        evaluatePostfixBT = new JButton("<html><div align='center'>EVALUATE<br>POSTFIX</div></html>");
        evaluatePrefixBT = new JButton("<html><div align='center'>EVALUATE<br>PREFIX</div></html>");
    } // end of initializeComponents method

    public void setBounds() {
        // main panel
        mainPanel.setLayout(null);
        mainPanel.setBounds(285, 0, 450, 800);

        // labels
        convertTitle.setBounds(0, 10, 450, 60);
        convertExpressionLB.setBounds(0, 150, 450, 30);
        convertResultLB.setBounds(0, 260, 450, 30);

        evaluateTitle.setBounds(0, 380, 450, 60);
        evaluateExpressionLB.setBounds(0, 520, 450, 30);
        evaluateResultLB.setBounds(0, 630, 450, 30);

        // text fields
        convertExpressionTF.setBounds(30, 90,390, 60);
        convertResultTF.setBounds(30, 200, 390, 60);
        convertResultTF.setEnabled(false);

        evaluateExpressionTF.setBounds(30, 460, 390, 60);
        evaluateResultTF.setBounds(30, 570, 390, 60);
        evaluateResultTF.setEnabled(false);
        // buttons
        convertToPrefixBT.setBounds(20, 310, 175, 60);
        convertToPostfixBT.setBounds(255, 310,175,60);

        evaluatePrefixBT.setBounds(20, 680, 175, 60);
        evaluatePostfixBT.setBounds(255, 680, 175, 60);
    } // end of setBounds method

    public void setGraphics() {
        // panel
        edit.setMainPanelGraphic(mainPanel);

        // title
        edit.setTitleFormat(convertTitle);
        edit.setTitleFormat(evaluateTitle);

        // labels
        edit.setFontFormat(convertExpressionLB);
        edit.setFontFormat(convertResultLB);
        edit.setFontFormat(evaluateExpressionLB);
        edit.setFontFormat(evaluateResultLB);

        // text fields
        edit.setFontFormat(convertExpressionTF);
        edit.setFontFormat(convertResultTF);
        edit.setFontFormat(evaluateExpressionTF);
        edit.setFontFormat(evaluateResultTF);

        // buttons
        edit.setFontFormat(convertToPrefixBT);
        edit.setFontFormat(convertToPostfixBT);
        edit.setFontFormat(evaluatePrefixBT);
        edit.setFontFormat(evaluatePostfixBT);
    } // end of setGraphics method

    public void addComponentsToPanel() {
        // labels
        mainPanel.add(convertTitle);
        mainPanel.add(evaluateTitle);
        mainPanel.add(convertExpressionLB);
        mainPanel.add(evaluateExpressionLB);
        mainPanel.add(convertResultLB);
        mainPanel.add(evaluateResultLB);

        // text fields
        mainPanel.add(convertExpressionTF);
        mainPanel.add(evaluateExpressionTF);
        mainPanel.add(convertResultTF);
        mainPanel.add(evaluateResultTF);

        // buttons
        mainPanel.add(convertToPostfixBT);
        mainPanel.add(convertToPrefixBT);
        mainPanel.add(evaluatePostfixBT);
        mainPanel.add(evaluatePrefixBT);
    } // end of addComponentsToPanel method

    public void addActionListeners() {
        convertToPostfixBT.addActionListener(new ButtonAction());
        convertToPrefixBT.addActionListener(new ButtonAction());
        evaluatePostfixBT.addActionListener(new ButtonAction());
        evaluatePrefixBT.addActionListener(new ButtonAction());
    } // end of addActionListeners method

    private class ButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String stringResult;
            double result;

            try {
                if (e.getSource() == convertToPostfixBT) {
                    stringResult = infixToPostfix.convert(convertExpressionTF.getText());
                    convertResultTF.setText(stringResult);
                    if (!infixToPostfix.getTable().isEmpty())
                        execute.displayTable(infixToPostfix.getTable(), 1);
                }
                if (e.getSource() == convertToPrefixBT) {
                    stringResult = infixToPrefix.convert(convertExpressionTF.getText());
                    convertResultTF.setText(stringResult);
                    if (!infixToPrefix.getTable().isEmpty())
                        execute.displayTable(infixToPrefix.getTable(), 1);
                }
                if (e.getSource() == evaluatePostfixBT) {
                    result = postfix.evaluate(evaluateExpressionTF.getText());
                    evaluateResultTF.setText(String.valueOf(result));
                    execute.displayTable(postfix.getTable(), 2);
                }
                if (e.getSource() == evaluatePrefixBT) {
                    result = prefix.evaluate(evaluateExpressionTF.getText());
                    evaluateResultTF.setText(String.valueOf(result));
                    execute.displayTable(prefix.getTable(), 2);
                }
            } catch (StackUnderflowException ex) {
                throw new RuntimeException(ex);
            }
        } // end of actionPerformed method
    } // end of ButtonAction class
} // end of MainPanel class