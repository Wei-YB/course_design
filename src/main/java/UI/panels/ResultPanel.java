package main.java.UI.panels;

import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {


    public ResultPanel() {
        init();
        addComponent();
        addListener();
    }

    private void init() {
        this.setBackground(UIConstants.MAIN_COLOR);
        this.setLayout(new BorderLayout());
    }

    private void addComponent() {
        this.add(upperPanel(), BorderLayout.NORTH);
    }

    private JPanel upperPanel() {
        JPanel panel = new JPanel();

        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new FlowLayout());

        JLabel title = new JLabel("Result Panel");

        panel.add(title);

        return panel;
    }

    private void addListener() {

    }
}
