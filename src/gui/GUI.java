package gui;

import javax.swing.*;

public class GUI extends JFrame {
    private final GraphicEditor edit = new GraphicEditor();
    JPanel mainPanel;
    JFrame frame;
    InformationPanel informationPanel;

    public GUI() {
        frame = new JFrame("Expression Notation Converter and Calculator");
        mainPanel = new MainPanel(frame);
        informationPanel = new InformationPanel(frame);
    } // end of default constructor

    public void run() {
        setFrame();
    }

    public void setFrame() {
        frame.setLayout(null);
        frame.setSize(750, 800);
        edit.setFrameGraphic(frame);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    } // end of setFrame method
} // end of Frame class
