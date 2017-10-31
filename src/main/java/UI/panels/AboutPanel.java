package main.java.UI.panels;

import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

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

        panel.setLayout(new GridLayout(8, 1, 20, -3));

        Vector<JLabel> labels = new Vector<>();

        labels.add(new JLabel("开发人员：江炜洲, 李静德, 韦一波, 王圣凯, 王豪放, 韦吉振"));
        labels.add(new JLabel("开发时间：2017-10-31"));
        labels.add(new JLabel("版本：1.0.0 "));
        labels.add(new JLabel("未经许可，禁止商用。"));

        Enumeration labelsEnum = labels.elements();

        while(labelsEnum.hasMoreElements()) {
            JLabel element = (JLabel) labelsEnum.nextElement();
            element.setFont(new Font("font", Font.PLAIN, 16));
            panel.add(element);
        }

        return panel;
    }

    private JPanel lowerPanel() {
        JPanel panel = new JPanel();

        panel.setBackground(UIConstants.SSUB_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JLabel label = new JLabel("@copyright 摸鱼小组");
        label.setFont(new Font("font", Font.PLAIN, 18));

        panel.add(label);
        return panel;
    }
}
