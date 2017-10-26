package main.java.UI.panels;

import main.java.Tools.DbUtil;
import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.Vector;

public class DatabasePanel extends JPanel{

    public static JTable dbTable;

    private static Vector<Vector<Object>> dbDatas;
    private static Vector<String> dbHeads;

    private JButton btnPrint;

    public DatabasePanel() {
        init();
        initDbData();
        addComponent();
        addListener();
    }

    private void init() {
        this.setBackground(UIConstants.MAIN_COLOR);
        this.setLayout(new BorderLayout());

        dbDatas = new Vector<>();
        dbHeads = new Vector<>();

        dbHeads.addAll(Arrays.asList(UIConstants.TABLE_HEADS));
    }

    private void addComponent() {
        this.add(upperPanel(), BorderLayout.NORTH);
        this.add(midPanel(), BorderLayout.CENTER);
        this.add(lowerPanel(), BorderLayout.SOUTH);
    }

    private JPanel upperPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel title = new JLabel("Database Panel");
        title.setFont(new Font("font", Font.BOLD, 20));
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

    private JPanel lowerPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 25, 5));

        btnPrint = new JButton("print");
        panel.add(btnPrint);
        return panel;
    }

    private JPanel dbPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new BorderLayout());

        dbTable = new JTable(dbDatas, dbHeads) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dbTable.getTableHeader().setBackground(new Color(37, 157, 221));
        dbTable.getTableHeader().setFont(new Font("font", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        dbTable.setRowHeight(30);
        dbTable.setGridColor(new Color(229, 229, 229));
        dbTable.setSelectionBackground(Color.LIGHT_GRAY);

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
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                Vector<Object> obj = new Vector<>();
                obj.add(rs.getInt("id"));
                obj.add(rs.getString("username"));
                obj.add(rs.getString("password"));
                obj.add(rs.getInt("privilege"));

                dbDatas.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addListener() {
        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
