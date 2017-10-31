package main.java.UI.panels;

import main.java.Logic.DatabaseManager;
import main.java.Logic.OperatorManager;
import main.java.Tools.DbUtil;
import main.java.UI.AppMain;
import main.java.UI.IconButton;
import main.java.UI.UIConstants;
import main.java.UI.Utils.ColumnGroup;
import main.java.UI.Utils.GroupableTableHeader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

public class DatabasePanel extends JPanel {

    public static JTable dbTable;

    private static Vector<Vector<Object>> dbDatas;
    private static Vector<String> dbHeads;

    private static DbUtil dbMySQL;

    private IconButton btnPrint;

    public DatabasePanel() {
        init();
        initDb();
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

//        dbDatas = new Vector<>();
//        dbHeads = new Vector<>();
    }

    private void initDb() {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        dbManager.initDbData();

        dbMySQL = dbManager.getDbMySQL();
        dbHeads = dbManager.getDbHeads();
        dbDatas = dbManager.getDbDatas();
    }

    private void addComponent() {
//        this.add(upperPanel(), BorderLayout.NORTH);
        this.add(midPanel(), BorderLayout.CENTER);
        this.add(lowerPanel(), BorderLayout.SOUTH);
    }

    private JPanel upperPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel title = new JLabel("Database Panel");
        title.setFont(new Font("font", Font.BOLD, 13));
        panel.add(title);

        return panel;
    }

    private JPanel midPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new BorderLayout());

        JPanel dbTitlePanel = new JPanel();
        dbTitlePanel.setBackground(UIConstants.MAIN_COLOR);
        dbTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        JLabel title = new JLabel("某沿海货轮用电设备参数");
        title.setFont(new Font("font", Font.BOLD, 19));
        title.setForeground(Color.BLACK);

        dbTitlePanel.add(title);

        panel.add(dbTitlePanel, BorderLayout.NORTH);
        panel.add(dbPanel(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel lowerPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.SUB_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 25, 5));

        btnPrint = new IconButton(UIConstants.ICON_PRINT,
                "打印",
                "print(output) the table into a sheet file");

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

            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        TableColumnModel cm = dbTable.getColumnModel();
        ColumnGroup columnGroupStatus = new ColumnGroup("工作状态");
        ColumnGroup[] columnGroupsEachStatus = new ColumnGroup[5];

        String[] eachStatusStr = {"航行", "起锚", "停泊", "装卸货", "应急"};
        for (int i = 0, j = 8; i < 5; i++, j++) {
            columnGroupsEachStatus[i] = new ColumnGroup(eachStatusStr[i]);

            columnGroupsEachStatus[i].add(cm.getColumn(j + i));
            columnGroupsEachStatus[i].add(cm.getColumn(j + i + 1));

            columnGroupStatus.add(columnGroupsEachStatus[i]);
        }

//        JTableHeader dbTableHeader = dbTable.getTableHeader();
        GroupableTableHeader dbTableHeader = (GroupableTableHeader)dbTable.getTableHeader();

        dbTableHeader.addColumnGroup(columnGroupStatus);

        dbTableHeader.setReorderingAllowed(false);
        dbTableHeader.setBackground(new Color(57, 200, 231));
        dbTableHeader.setFont(new Font("font", Font.PLAIN, 11));
        dbTableHeader.setPreferredSize(new Dimension(dbTableHeader.getWidth(), 70));

        dbTable.setFont(new Font("font", Font.PLAIN, 13));
//        dbTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dbTable.setRowHeight(33);
        dbTable.setGridColor(new Color(229, 229, 229));
        dbTable.setSelectionBackground(Color.LIGHT_GRAY);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        dbTable.setDefaultRenderer(Object.class, dtcr);

//        for (int i = 0; i < dbTable.getColumnCount(); i++) {
//            dbTable.getColumnModel().getColumn(i).setPreferredWidth(50);
//            dbTable.getColumnModel().getColumn(i).setMaxWidth(100);
//        }

        FitTableColumns(dbTable);

        JScrollPane scrollPane = new JScrollPane(dbTable);
        scrollPane.setBackground(UIConstants.MAIN_COLOR);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void FitTableColumns(JTable myTable) {
//        JTableHeader header = myTable.getTableHeader();
        GroupableTableHeader header = (GroupableTableHeader)myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();

        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();

            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier(),
                            false, false, -1, col).getPreferredSize().getWidth();

            for (int row = 0; row < rowCount; row++) {
                int preferredWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col),
                        false, false,
                        row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferredWidth);
            }

            header.setResizingColumn(column);
            column.setWidth((int) ((width + myTable.getIntercellSpacing().width) * 1.15f));
        }
    }

    public void refreshTable() {
        ((JPanel)this.getComponent(0)).remove(1);
        initDb();
        ((JPanel)this.getComponent(0)).add(dbPanel());

        this.addListener();

        this.updateUI();
    }

    private void addListener() {
        btnPrint.addActionListener((e) -> {

        });

        dbTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    AppMain.toolBarPanel.getButton("Database")
                            .getParent().setBackground(UIConstants.TOOL_BAR_COLOR);

                    AppMain.toolBarPanel.getButton("Operator")
                            .getParent().setBackground(UIConstants.TOOL_BAR_ACTIVATED_COLOR);

                    AppMain.toolBarPanel.setCurActivatedBtn(AppMain.toolBarPanel.getButton("Operator"));

                    int row = ((JTable)e.getSource()).rowAtPoint(e.getPoint());
                    AppMain.operatorPanel.updateDataDisplay(row);

                    AppMain app = AppMain.getInstance();
                    app.switchPanel(AppMain.operatorPanel);
                } else if (e.isMetaDown()) {

                    if(JOptionPane.showConfirmDialog(null,
                            "是否删除该设备信息?",
                            "删除设备",
                            JOptionPane.YES_NO_OPTION) == 0) {

                        int row = ((JTable)e.getSource()).rowAtPoint(e.getPoint());

                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        Vector<Object> log = new Vector<>();
                        log.add(sdf.format(date));
                        log.add("删除设备 \"" + AppMain.databaseManager.devices.get(row).getDeviceName() + "\"");
                        log.add(AppMain.username);
                        AppMain.logPanel.data.add(log);
//                        System.out.println(row);
                        AppMain.databaseManager.deleteDevice(row);
                    }
                    return;
                } else { return; }
            }
        });

    }
}
