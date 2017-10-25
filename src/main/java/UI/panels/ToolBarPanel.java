package main.java.UI.panels;

import main.java.UI.IconButton;
import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class ToolBarPanel extends JPanel {

    private static IconButton btnA;
    private static IconButton btnB;
    private static IconButton btnC;

    public ToolBarPanel() {
        initPanel();
        initButton();
        addListener();
    }

    private void initPanel() {
        Dimension preferredSize = new Dimension(UIConstants.TOOL_BAR_WIDTH, UIConstants.APP_BOUNDS.height);

        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);

        this.setBackground(UIConstants.TOOL_BAR_COLOR);
        this.setLayout(new GridLayout(2, 1));
    }

    private void initButton() {
        JPanel panelUp = new JPanel();
        panelUp.setBackground(UIConstants.TOOL_BAR_COLOR);
        panelUp.setLayout(new FlowLayout(-2, -2, -4));

        JPanel panelDown = new JPanel();
        panelDown.setBackground(UIConstants.TOOL_BAR_COLOR);
        panelDown.setLayout(new BorderLayout(0, 0));

        btnA = new IconButton(
                UIConstants.ICON_A,
                UIConstants.ICON_A,
                UIConstants.ICON_A,
                "database",
                "buttonA");

        panelUp.add(btnA);

        this.add(panelUp);
        this.add(panelDown);
    }

    private void addListener() {

    }

}
