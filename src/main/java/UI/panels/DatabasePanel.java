package main.java.UI.panels;

import main.java.Tools.DbUtil;
import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePanel extends JPanel{

    public static JTable dbTable;

    private static Object[][] dbDatas;

    public DatabasePanel() {
        init();
        initDbData();
        addComponent();
        addListener();
    }

    private void init() {
        this.setBackground(UIConstants.MAIN_COLOR);
        this.setLayout(new BorderLayout());
    }

    private void addComponent() {
        this.add(upperPanel(), BorderLayout.NORTH);
        this.add(midPanel(), BorderLayout.CENTER);
    }

    private JPanel upperPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));

        JLabel title = new JLabel("......");
        panel.add(title);

        return panel;
    }

    private JPanel midPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new GridLayout(1, 1));

        panel.add(dbPanel());

        return panel;
    }

    private JPanel dbPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new BorderLayout());

        dbTable = new JTable(dbDatas, UIConstants.TABLE_HEADS);
        dbTable.getTableHeader().setBackground(UIConstants.TOOL_BAR_COLOR);
        dbTable.setRowHeight(30);
        dbTable.setGridColor(new Color(229, 229, 229));
        dbTable.setSelectionBackground(UIConstants.TOOL_BAR_COLOR);

        dbTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        dbTable.getColumnModel().getColumn(0).setMaxWidth(50);

        JScrollPane scrollPane = new JScrollPane(dbTable);
        scrollPane.setBackground(UIConstants.MAIN_COLOR);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private static void initDbData() {
        try {
            DbUtil dbMySQL = DbUtil.getInstance();
            Connection conn = dbMySQL.getConnection();

            ResultSet rs = dbMySQL.execQuery("SELECT * FROM users;");
            int columns = rs.getMetaData().getColumnCount();
            dbDatas = new Object[columns][4];
//            System.out.println(columns);

            for (int i = 1; i < columns; i++) {
                rs.next();
                dbDatas[i] = new Object[] {
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("privilege")
                };
//                    System.out.println(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addListener() {

    }
}
