package main.java.UI;


import main.java.UI.panels.*;

import javax.swing.*;
import java.awt.*;

public class AppMain {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel mainCenterPanel;

    private static AppMain instance;

    public static WelcomePanel welcomePanel;
    public static DatabasePanel dbPanel;
    public static OperatorPanel operatorPanel;
    public static ResultPanel resultPanel;
    public static LogPanel logPanel;
    public static AboutPanel aboutPanel;

    public static int userPrivilege;

    private AppMain() {
//        initLoginFrame();
        initMainFrame();
    }

    public static AppMain getInstance() {
        if (instance == null) {
            instance = new AppMain();
        }
        return instance;
    }

    private void initLoginFrame() {
        frame = new LoginFrame();
        frame.setResizable(false);
    }

    public void switchFrame() {
        frame.setVisible(false);
        frame.dispose();
        frame = null;
        initMainFrame();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void switchPanel(JPanel panel) {
        mainCenterPanel.removeAll();
        mainCenterPanel.add(panel, BorderLayout.CENTER);
        mainCenterPanel.updateUI();
        frame.setResizable(false);
    }

    private void initMainFrame() {
        frame = null;
        frame = new JFrame();

        frame.setResizable(false);
        frame.setBounds(UIConstants.APP_BOUNDS);
        frame.setTitle(UIConstants.APP_NAME + "   " + UIConstants.APP_VERSION);
        frame.setBackground(UIConstants.MAIN_COLOR);

        mainPanel = new JPanel(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        ToolBarPanel toolBarPanel = new ToolBarPanel();
        welcomePanel = new WelcomePanel();
        dbPanel = new DatabasePanel();
        operatorPanel = new OperatorPanel();
        resultPanel = new ResultPanel();
        logPanel = new LogPanel();
        aboutPanel = new AboutPanel();

        mainPanel.add(toolBarPanel, BorderLayout.WEST); //location of toolBarPanel

        mainCenterPanel = new JPanel(true);
        mainCenterPanel.setLayout(new BorderLayout());
        mainCenterPanel.add(welcomePanel, BorderLayout.CENTER);

        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AppMain app = AppMain.getInstance();
                    app.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


