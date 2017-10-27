package main.java.UI.panels;

import main.java.UI.AppMain;
import main.java.UI.IconButton;
import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarPanel extends JPanel {

    private static IconButton btnDb;
    private static IconButton btnOperator;
    private static IconButton btnResult;
    private static IconButton btnLogger;
    private static IconButton btnAbout;

    private static IconButton curActivatedBtn;

    public ToolBarPanel() {
        init();
        initButton();
        addListener();
    }

    private void init() {
        Dimension preferredSize = new Dimension(UIConstants.TOOL_BAR_WIDTH, UIConstants.APP_BOUNDS.height);

        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);

        this.setBackground(UIConstants.TOOL_BAR_COLOR);
        this.setLayout(new BorderLayout());

        curActivatedBtn = null;
    }

    private void initButton() {
        JPanel panelUp = new JPanel();
        panelUp.setBackground(UIConstants.TOOL_BAR_COLOR);
        panelUp.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JPanel panelDown = new JPanel();
        panelDown.setBackground(UIConstants.TOOL_BAR_COLOR);
        panelDown.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));


        JPanel panelBtnDb = new JPanel();
        JPanel panelBtnOperator = new JPanel();
        JPanel panelBtnResult = new JPanel();
        JPanel panelBtnLogger = new JPanel();
        JPanel panelBtnAbout = new JPanel();

        panelBtnDb.setLayout(new GridLayout(1, 1));
        panelBtnOperator.setLayout(new GridLayout(1, 1));
        panelBtnResult.setLayout(new GridLayout(1, 1));
        panelBtnLogger.setLayout(new GridLayout(1, 1));
        panelBtnAbout.setLayout(new GridLayout(1, 1));

        panelBtnDb.setBackground(UIConstants.TOOL_BAR_COLOR);
        panelBtnOperator.setBackground(UIConstants.TOOL_BAR_COLOR);
        panelBtnResult.setBackground(UIConstants.TOOL_BAR_COLOR);
        panelBtnLogger.setBackground(UIConstants.TOOL_BAR_COLOR);
        panelBtnAbout.setBackground(UIConstants.TOOL_BAR_COLOR);

        btnDb = new IconButton(
                UIConstants.ICON_DATABASE,
                "Database",
                "show database");

        btnOperator = new IconButton(
                UIConstants.ICON_OPERATOR,
                "Operator ",
                "add/edit any devices");

        btnResult = new IconButton(
                UIConstants.ICON_RESULT,
                "Calculate ",
                "calculate the expected result");

        btnLogger = new IconButton(
                UIConstants.ICON_LOGGER,
                "Log          ",
                "the log of all operation(s)");

        btnAbout = new IconButton(
                UIConstants.ICON_ABOUT,
                "About       ",
                "About this software");

        panelBtnDb.add(btnDb);
        panelBtnOperator.add(btnOperator);
        panelBtnResult.add(btnResult);
        panelBtnLogger.add(btnLogger);
        panelBtnAbout.add(btnAbout);

        panelUp.add(panelBtnDb);
        panelUp.add(panelBtnOperator);
        panelUp.add(panelBtnResult);
        panelUp.add(panelBtnLogger);

        panelDown.add(panelBtnAbout);

        this.add(panelUp, BorderLayout.CENTER);
        this.add(panelDown, BorderLayout.SOUTH);
    }

    private void switchPanel(JPanel target) {

    }

    private void addListener() {
        btnDb.addActionListener((e)-> {
            if(curActivatedBtn != null) {
                curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_COLOR);
            }
            curActivatedBtn = btnDb;
            curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_ACTIVATED_COLOR);
            AppMain app = AppMain.getInstance();
            app.switchPanel(AppMain.dbPanel);
        });

        btnOperator.addActionListener((e)-> {
            if(curActivatedBtn != null) {
                curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_COLOR);
            }
            curActivatedBtn = btnOperator;
            curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_ACTIVATED_COLOR);
            AppMain app = AppMain.getInstance();
            app.switchPanel(AppMain.operatorPanel);
        });

        btnResult.addActionListener((e)-> {
            if(curActivatedBtn != null) {
                curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_COLOR);
            }
            curActivatedBtn = btnResult;
            curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_ACTIVATED_COLOR);
            AppMain app = AppMain.getInstance();
            app.switchPanel(AppMain.resultPanel);
        });

        btnLogger.addActionListener((e)-> {
            if(curActivatedBtn != null) {
                curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_COLOR);
            }
            curActivatedBtn = btnLogger;
            curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_ACTIVATED_COLOR);
            AppMain app = AppMain.getInstance();
            app.switchPanel(AppMain.logPanel);
        });

        btnAbout.addActionListener((e)-> {
            if(curActivatedBtn != null) {
                curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_COLOR);
            }
            curActivatedBtn = btnAbout;
            curActivatedBtn.getParent().setBackground(UIConstants.TOOL_BAR_ACTIVATED_COLOR);
            AppMain app = AppMain.getInstance();
            app.switchPanel(AppMain.aboutPanel);
        });
    }

    public IconButton getButton(String target) {
        switch (target) {
            case "Database":
                return btnDb;
            case "Operator":
                return btnOperator;
            case "Result":
                return btnResult;
            case "Logger":
                return btnLogger;
            case "About":
                return btnAbout;
            default:
                return null;
        }
    }

    public void setCurActivatedBtn(IconButton target) {
        curActivatedBtn = target;
    }
}
