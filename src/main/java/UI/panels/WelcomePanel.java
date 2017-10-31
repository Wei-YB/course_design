package main.java.UI.panels;

import main.java.UI.AppMain;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        init();
        addComponent();
    }

    private void init() {
        this.setLayout(new BorderLayout());
    }

    private void addComponent() {
        this.add(bgPanel(), BorderLayout.CENTER);
    }

    private JPanel bgPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(AppMain.class.getResource("/pic/bg.jpg"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };

        return panel;
    }

}
