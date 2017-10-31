package main.java.UI;

import main.java.Tools.DbUtil;
import main.java.Tools.RegExpForTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {

    private JTextField inputUid;
    private JPasswordField inputPwd;
    private IconButton btnLogin;

    public LoginFrame() {
        init();
        addComponent();
        addListener();
    }

    private void init() {

        this.setBackground(UIConstants.SUB_COLOR);
        this.setTitle(UIConstants.APP_NAME + " " + UIConstants.APP_VERSION);
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

        panel.setBackground(UIConstants.SUB_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel title = new JLabel("登陆验证系统");
        title.setFont(new Font("font", Font.PLAIN, 15));
        panel.add(title);

        return panel;
    }

    private JPanel verifyPanel() {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        panelMain.setBounds(450, 300, 250, 250);

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelInput.setBackground(new Color(100, 100, 100));

        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(UIConstants.SSUB_COLOR);
        panelLogin.setLayout(new FlowLayout(FlowLayout.CENTER));

        btnLogin = new IconButton(UIConstants.ICON_LOGIN_LOGIN,
                "",
                "login");

        IconButton labelUid = new IconButton(UIConstants.ICON_LOGIN_USER,
                "",
                "6-16 Characters(including all letters and number)");

        IconButton labelPwd = new IconButton(UIConstants.ICON_LOGIN_PASSWORD,
                "",
                "6-16 Any Characters");

        labelUid.setEnabled(false);
        labelPwd.setEnabled(false);

        inputUid = new JTextField();
        inputPwd = new JPasswordField();

        inputUid.setBackground(new Color(233, 230, 229));
        inputPwd.setBackground(new Color(233, 230, 229));

        btnLogin.setPreferredSize(new Dimension(110, 40));
        labelUid.setPreferredSize(UIConstants.LABEL_SIZE);
        labelPwd.setPreferredSize(UIConstants.LABEL_SIZE);
        inputUid.setPreferredSize(new Dimension(155, 34));
        inputPwd.setPreferredSize(new Dimension(155, 34));

        inputUid.setDocument(new RegExpForTextField("[a-zA-Z0-9]{1,16}"));
        inputPwd.setDocument(new RegExpForTextField(".{1,16}"));

        panelInput.add(labelUid);
        panelInput.add(inputUid);
        panelInput.add(labelPwd);
        panelInput.add(inputPwd);

        panelLogin.add(btnLogin);

        panelMain.add(panelInput, BorderLayout.CENTER);
        panelMain.add(panelLogin,BorderLayout.SOUTH);

        return panelMain;
    }

    private void addListener() {

        btnLogin.addActionListener((e) -> {
            try {
                DbUtil dbMySQL = DbUtil.getInstance();
                Connection conn = dbMySQL.getConnection();

                ResultSet rs = dbMySQL.execQuery("SELECT * FROM users;");
                int loginFlag = 0;

                while (rs.next()) {
                    if (inputUid.getText().equals("")) {
                        loginFlag = -1;
                    } else if (inputUid.getText().length() < 4) {
                        loginFlag = -11;
                    }
                    else if (String.valueOf(inputPwd.getPassword()).equals("")) {
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
                                "请输入用户名",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case -11:
                        System.out.println("Username length less than 4");
                        JOptionPane.showMessageDialog(
                                null,
                                "用户名长度不能少于4位",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case -2:
                        System.out.println("Password blank empty");
                        JOptionPane.showMessageDialog(
                                null,
                                "请输入密码",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case -3:
                        System.out.println("Password incorrect");
                        JOptionPane.showMessageDialog(
                                null,
                                "密码验证失败",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case 1:
                        System.out.println("Login successfully");

//                        AppMain.userPrivilege = rs.getInt("privilege");

                        AppMain app = AppMain.getInstance();
                        app.switchFrame();
                        break;
                    default:
                        System.out.println("Error");
                        JOptionPane.showMessageDialog(
                                null,
                                "用户名或密码错误",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                }

                dbMySQL.closeConnection();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
