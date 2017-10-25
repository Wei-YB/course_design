package main.java.UI.panels;

import main.java.Tools.DbUtil;
import main.java.UI.AppMain;
import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginPanel extends JPanel {

    private JTextField inputUid;
    private JPasswordField inputPwd;
    private JButton btnLogin;

    public LoginPanel() {
        super(true);

        init();
        addComponent();
        addListener();

    }

    private void init() {
        this.setBackground(UIConstants.MAIN_COLOR);
        this.setLayout(null);
    }

    private void addComponent() {
        this.add(upperPanel());
        this.add(verifyPanel());
    }

    private JPanel upperPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel text = new JLabel("Welcome......");

        panel.add(text, BorderLayout.CENTER);
        panel.setBounds(25, 40, UIConstants.APP_BOUNDS.width - UIConstants.TOOL_BAR_WIDTH - 50, 48);
        return panel;
    }

    private JPanel verifyPanel() {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        panelMain.setBounds(450, 200, 250, 250);

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));

        btnLogin = new JButton("Login");
        JLabel labelUid = new JLabel("username:");
        JLabel labelPwd = new JLabel("password:");

        inputUid = new JTextField();
        inputPwd = new JPasswordField();

        btnLogin.setPreferredSize(new Dimension(80, 40));
        labelUid.setPreferredSize(UIConstants.LABEL_SIZE);
        labelPwd.setPreferredSize(UIConstants.LABEL_SIZE);
        inputUid.setPreferredSize(UIConstants.INPUT_FIELD_SIZE);
        inputPwd.setPreferredSize(UIConstants.INPUT_FIELD_SIZE);

        panelInput.add(labelUid);
        panelInput.add(inputUid);
        panelInput.add(labelPwd);
        panelInput.add(inputPwd);

        panelMain.add(panelInput, BorderLayout.CENTER);
        panelMain.add(btnLogin,BorderLayout.SOUTH);

        return panelMain;
    }

    private void addListener() {

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DbUtil dbMySQL = DbUtil.getInstance();
                    Connection conn = dbMySQL.getConnection();

                    ResultSet rs = dbMySQL.execQuery("SELECT * FROM users;");

                    while(rs.next()) {
                        if (rs.getString("username").equals(inputUid.getText())
                                &&
                                rs.getString("password").equals(inputPwd.getText())) {
                            System.out.println("Login successfully");

                            AppMain.mainCenterPanel.removeAll();
                            AppMain.mainCenterPanel.add(AppMain.welcomePanel, BorderLayout.CENTER);
                            AppMain.mainCenterPanel.updateUI();
                            break;
                        }
                        System.out.println("Login unsuccessfully");
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });

    }
}
