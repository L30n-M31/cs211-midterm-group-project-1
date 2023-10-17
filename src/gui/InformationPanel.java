package gui;

import javax.swing.*;

public class InformationPanel {
    private final GraphicEditor edit = new GraphicEditor();
    private JPanel infoPanel;
    private JLabel appHeading, infoLB, acceptedOperatorsLB, reminderLB;

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
        appHeading.setText("<html><div align='center'>EXPRESSION NOTATION<br>CONVERTER AND<br>EVALUATOR</div></html>");
    }

    public void setBounds() {
        // info panel
        infoPanel.setBounds(0,0,280, 800);

        // labels
        appHeading.setBounds(0, 10, 280, 60);
    }

    public void setGraphics() {
        // panel
        edit.setInfoPanelGraphic(infoPanel);

        // label
        edit.setAppHeadingFormat(appHeading);
    }

    public void addComponentsToPanel() {
        infoPanel.add(appHeading);
    }

} // end of InformationPanel class
