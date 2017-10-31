package main.java.UI.panels;

import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {

    public AboutPanel() {
        init();
        addComponent();
    }

    private void init() {
        this.setLayout(new BorderLayout());
    }

    private void addComponent() {
        this.add(upperPanel(), BorderLayout.NORTH);
        this.add(midPanel(), BorderLayout.CENTER);
        this.add(lowerPanel(), BorderLayout.SOUTH);
    }

    private JPanel upperPanel() {
        JPanel panel = new JPanel();

        panel.setBackground(UIConstants.SSUB_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        JLabel label = new JLabel("关于本软件");
        label.setFont(new Font("font", Font.PLAIN, 22));

        panel.add(label);
        return panel;
    }

    private JPanel midPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel label = new JLabel("古ggugugu咕咕gg咕咕g， 这是一个互相甩锅的垃圾东西哈哈哈哈草拟ma");
        label.setFont(new Font("font", Font.PLAIN, 15));

        panel.add(label);
        return panel;
    }

    private JPanel lowerPanel() {
        JPanel panel = new JPanel();

        panel.setBackground(UIConstants.SSUB_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JLabel label = new JLabel("@copyright 摸鱼小组-   王豪放  韦一波  王圣凯  韦吉振  李静德  江炜洲");
        label.setFont(new Font("font", Font.PLAIN, 18));

        panel.add(label);
        return panel;
    }
}
