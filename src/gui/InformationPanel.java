package gui;

import javax.swing.*;
import java.awt.*;

public class InformationPanel {
    private final GraphicEditor edit = new GraphicEditor();
    private JPanel infoPanel;
    private JLabel appHeading, supportedOperatorsLB, booleanInterpretationLB, additionalNotesLB;

    public InformationPanel(JFrame frame) {
        initializeComponents();
        setBounds();
        setGraphics();
        addComponentsToPanel();
        frame.add(infoPanel);
    } // end of default constructor

    public void initializeComponents() {
        // info panel
        infoPanel = new JPanel();

        // labels
        appHeading = new JLabel();
        appHeading.setText("<html><div style='text-align: center;'>EXPRESSION NOTATION<br>CONVERTER AND<br>EVALUATOR</div></html>");

        supportedOperatorsLB = new JLabel(
                "<html>" +
                        "Supported Operators:<br>" +
                        "<ol> " +
                        "<li>Arithmetic Operators (excluding increment and decrement)</li>" +
                        "<li>Comparison Operators</li>" +
                        "</ol>" +
                        "</html>");

        booleanInterpretationLB = new JLabel(
                "<html>" +
                        "Boolean Interpretation for the Stack:<br>" +
                        "<ul>" +
                        "<li>1.0001 represents \"True\"</li>" +
                        "<li>1.00001 represents \"False\"</li>" +
                        "</ul>" +
                        "</html>");

        additionalNotesLB = new JLabel(
                "<html>" +
                        "Additional Note:<br>" +
                        "1. Infix expressions in the conversion should not contain any spaces (e.g., \"(A+B)-(C-D)\"<br><br>" +
                        "2. Expressions in the evaluation should be separated by spaces (e.g., \"2 4 + 8 - \" or \"!= + 10 10 - 15 5\") for both postfix and prefix notation" +
                        "</html>");
    } // end of initializeComponents method

    public void setBounds() {
        // info panel
        infoPanel.setLayout(null);
        infoPanel.setBounds(0,0,280, 800);

        // labels
        appHeading.setBounds(15, 10, 260, 100);
        supportedOperatorsLB.setBounds(10, 130, 260, 115);
        booleanInterpretationLB.setBounds(10, 280, 260, 140);
        additionalNotesLB.setBounds(10, 440, 260, 300);
    } // end of setBounds method

    public void setGraphics() {
        // panel
        edit.setInfoPanelGraphic(infoPanel);

        // label
        edit.setAppHeadingFormat(appHeading);
        edit.setInfoFormat(supportedOperatorsLB);
        edit.setInfoFormat(booleanInterpretationLB);
        edit.setInfoFormat(additionalNotesLB);
    } // end of setGraphics method

    public void addComponentsToPanel() {
        infoPanel.add(appHeading);
        infoPanel.add(supportedOperatorsLB);
        infoPanel.add(booleanInterpretationLB);
        infoPanel.add(additionalNotesLB);
    } // end of addComponentsToPanel method
} // end of InformationPanel class
