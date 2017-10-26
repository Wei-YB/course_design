package main.java.UI;

import main.java.Tools.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {

    private JTextField inputUid;
    private JPasswordField inputPwd;
    private JButton btnLogin;

    public LoginFrame() {
        init();
        addComponent();
        addListener();
    }

    private void init() {

        this.setBackground(UIConstants.MAIN_COLOR);
        this.setTitle("Login System");
        this.setLayout(new BorderLayout());
        this.setBounds(UIConstants.SCREEN_WIDTH / 2 - 150, UIConstants.SCREEN_HEIGHT / 2 - 125, 300, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComponent() {
        this.add(upperPanel(), BorderLayout.NORTH);
        this.add(verifyPanel(), BorderLayout.CENTER);
    }

    private JPanel upperPanel() {
        JPanel panel = new JPanel();

        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel title = new JLabel("XXXXXX System");
        panel.add(title);

        return panel;
    }

    private JPanel verifyPanel() {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        panelMain.setBounds(450, 300, 250, 250);

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        btnLogin = new JButton("Login");
        JLabel labelUid = new JLabel("username:");
        JLabel labelPwd = new JLabel("password:");

        inputUid = new JTextField();
        inputPwd = new JPasswordField();

        btnLogin.setPreferredSize(new Dimension(80, 40));
        labelUid.setPreferredSize(UIConstants.LABEL_SIZE);
        labelPwd.setPreferredSize(UIConstants.LABEL_SIZE);
        inputUid.setPreferredSize(new Dimension(135, 27));
        inputPwd.setPreferredSize(new Dimension(135, 27));

        panelInput.add(labelUid);
        panelInput.add(inputUid);
        panelInput.add(labelPwd);
        panelInput.add(inputPwd);

        panelMain.add(panelInput, BorderLayout.CENTER);
        panelMain.add(btnLogin,BorderLayout.SOUTH);

        return panelMain;
    }

    private void addListener() {

        btnLogin.addActionListener((e) -> {
            try {
                DbUtil dbMySQL = DbUtil.getInstance();
                Connection conn = dbMySQL.getConnection();

                ResultSet rs = dbMySQL.execQuery("SELECT * FROM users;");
                int loginFlag = 0;

                while(rs.next()) {
                    if(inputUid.getText().equals("")) {
                        loginFlag = -1;
                    } else if(inputPwd.getText().equals("")) {
                        loginFlag = -2;
                    } else if (rs.getString("username").equals(inputUid.getText())
                            &&
                            !rs.getString("password").equals(inputPwd.getText())) {
                        loginFlag = -3;
                    } else if (rs.getString("username").equals(inputUid.getText())
                            &&
                            rs.getString("password").equals(inputPwd.getText())) {
                        loginFlag = 1;
                        break;
                    }
                }

                switch (loginFlag) {
                    case -1:
                        System.out.println("Username blank empty");
                        JOptionPane.showMessageDialog(
                                null,
                                "Username blank empty",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case -2:
                        System.out.println("Password blank empty");
                        JOptionPane.showMessageDialog(
                                null,
                                "Password blank empty",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case -3:
                        System.out.println("Password incorrect");
                        JOptionPane.showMessageDialog(
                                null,
                                "Password incorrect",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case 1:
                        System.out.println("Login successfully");

                        AppMain.userPrivilege = rs.getInt("privilege");
//                            System.out.println(AppMain.userPrivilege);
                        AppMain app = AppMain.getInstance();
                        app.switchFrame();
                        break;
                    default:
                        System.out.println("Error");
                        JOptionPane.showMessageDialog(
                                null,
                                "Username or password wrong",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
