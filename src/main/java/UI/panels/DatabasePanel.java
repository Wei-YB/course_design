package main.java.UI.panels;

import main.java.Tools.DbUtil;
import main.java.UI.UIConstants;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

public class DatabasePanel extends JPanel {

    public static JTable dbTable;

    private static Vector<Vector<Object>> dbDatas;
    private static Vector<String> dbHeads;

    private static DbUtil dbMySQL;

    private JButton btnPrint;

    public DatabasePanel() {
        init();
        initDbData();
        addComponent();
        addListener();
        try {
            dbMySQL.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void init() {
        this.setBackground(UIConstants.MAIN_COLOR);
        this.setLayout(new BorderLayout());

        dbDatas = new Vector<>();
        dbHeads = new Vector<>();
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
        panel.setLayout(new BorderLayout());

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
        dbTable.getTableHeader().setReorderingAllowed(false);
        dbTable.getTableHeader().setBackground(new Color(37, 157, 221));
        dbTable.getTableHeader().setFont(new Font("font", Font.PLAIN, 14));
        dbTable.setRowHeight(30);
        dbTable.setGridColor(new Color(229, 229, 229));
        dbTable.setSelectionBackground(Color.LIGHT_GRAY);

//        for (int i = 0; i < dbTable.getColumnCount(); i++) {
//            dbTable.getColumnModel().getColumn(i).setPreferredWidth(50);
//            dbTable.getColumnModel().getColumn(i).setMaxWidth(100);
//        }
        FitTableColumns(dbTable);

        JScrollPane scrollPane = new JScrollPane(dbTable);
        scrollPane.setBackground(UIConstants.MAIN_COLOR);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void FitTableColumns(JTable myTable) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();

        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();

            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier(),
                            false, false, -1, col).getPreferredSize().getWidth();

            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col),
                        false, false,
                        row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }

            header.setResizingColumn(column);
            column.setWidth((int) ((width + myTable.getIntercellSpacing().width) * 1.85f));
        }
    }

    private static void initDbData() {
        try {
            dbMySQL = DbUtil.getInstance();
            Connection conn = dbMySQL.getConnection();

            ResultSet rs = dbMySQL.execQuery("SELECT * FROM T;");
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                dbHeads.add(rsmd.getColumnName(i));
            }

            while (rs.next()) {
                Vector<Object> obj = new Vector<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    obj.add(rs.getObject(i));
                }
                dbDatas.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addListener() {
        btnPrint.addActionListener((e) -> {

        });
    }
}
