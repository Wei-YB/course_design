package main.java.UI.panels;

import main.java.Tools.DbUtil;
import main.java.UI.IconButton;
import main.java.UI.UIConstants;
import main.java.UI.Utils.ColumnGroup;
import main.java.UI.Utils.GroupableTableHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
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

    private IconButton btnPrint;

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
        dbTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel title = new JLabel("xxxxxxxxxxxxxx");
        title.setFont(new Font("font", Font.BOLD, 14));

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
                "Print",
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
        dbTableHeader.setBackground(new Color(37, 157, 221));
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
            column.setWidth((int) ((width + myTable.getIntercellSpacing().width) * 1.0f));
        }
    }

    private static void initDbData() {
        try {
            dbMySQL = DbUtil.getInstance();
            Connection conn = dbMySQL.getConnection();

            ResultSet rs = dbMySQL.execQuery("select" +
                    " `参数`.`序号`,`参数`.`用电设备名称`," +
                    "`参数`.`数量`,`参数`.`机械轴功率/kW`," +
                    "`参数`.`电机功率/kW`," +
                    "`参数`.`电机效率/%`," +
                    "`参数`.`利用系数K1`," +
                    "`参数`.`设备总功率/kW`," +
                    "`航行`.`K2`," +
                    "`航行`.`K0`," +
                    "`起锚`.`K2`," +
                    "`起锚`.`K0`," +
                    "`停泊`.`K2`," +
                    "`停泊`.`K0`," +
                    "`装卸货`.`K2`," +
                    "`装卸货`.`K0`," +
                    "`应急`.`K2`," +
                    "`应急`.`K0`," +
                    "`参数`.`负荷类别`," +
                    "`参数`.`电机转速/（r/m）` from `参数`," +
                    "`航行`,`起锚`," +
                    "`停泊`,`装卸货`," +
                    "`应急` where" +
                    " `参数`.`序号`=`航行`.`序号` and" +
                    " `参数`.`序号`=`起锚`.`序号` and" +
                    " `参数`.`序号`=`停泊`.`序号` and " +
                    "`参数`.`序号`=`装卸货`.`序号` and " +
                    "`参数`.`序号`=`应急`.`序号`;");

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
