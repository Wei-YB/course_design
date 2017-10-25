package main.java.UI;


import main.java.UI.panels.*;


import javax.swing.*;
import java.awt.*;

public class AppMain {

    private JFrame frame;

    private JPanel mainPanel;

    public static JPanel mainCenterPanel;
    public static LoginPanel loginPanel;
    public static WelcomePanel welcomePanel;
    public static DatabasePanel dbPanel;
    public static OperatorPanel operatorPanel;
    public static ResultPanel resultPanel;
    public static LogPanel logPanel;
    public static OptionPanel optionPanel;

    public static int loginStatus;
    public static int userPrivilege;

    public AppMain() {
        init();
    }

    private void init() {

        frame = new JFrame();

        frame.setBounds(UIConstants.APP_BOUNDS);
        frame.setTitle(UIConstants.APP_NAME);
        frame.setBackground(UIConstants.MAIN_COLOR);

        mainPanel = new JPanel(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        ToolBarPanel toolBarPanel = new ToolBarPanel();
        loginPanel = new LoginPanel();
        welcomePanel = new WelcomePanel();
        dbPanel = new DatabasePanel();
        operatorPanel = new OperatorPanel();
        resultPanel = new ResultPanel();
        logPanel = new LogPanel();
        optionPanel = new OptionPanel();

        mainPanel.add(toolBarPanel, BorderLayout.WEST); //location of toolBarPanel

        mainCenterPanel = new JPanel(true);
        mainCenterPanel.setLayout(new BorderLayout());
        mainCenterPanel.add(loginPanel, BorderLayout.CENTER);

        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AppMain app = new AppMain();
                    app.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


