package main.java.UI.panels;

import main.java.Logic.DatabaseManager;
import main.java.UI.AppMain;
import main.java.UI.UIConstants;
import main.java.UI.Utils.GroupableTableHeader;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class LogPanel extends JPanel{

    public Vector<Vector<Object>> data;

    private Vector<String > head;

    public LogPanel() {
        init();
        addComponent();
    }

    private void init() {
        this.setBackground(UIConstants.MAIN_COLOR);
        this.setLayout(new BorderLayout());

        data = new Vector<>();
        head = new Vector<>();
        head.add("操作时间");
        head.add("操作");
        head.add("操作用户");
    }

    private void addComponent() {
        this.add(dbPanel(), BorderLayout.CENTER);
    }

    private JPanel dbPanel() {
        JPanel panel = new JPanel();

        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new BorderLayout());

        JTable table = new JTable(data, head) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        TableColumnModel cm = table.getColumnModel();

        JTableHeader tableHeader = table.getTableHeader();

        tableHeader.setReorderingAllowed(false);
        tableHeader.setBackground(new Color(57, 200, 231));
        tableHeader.setFont(new Font("font", Font.PLAIN, 18));
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 50));

        table.setFont(new Font("font", Font.PLAIN, 14));
        table.setRowHeight(33);
        table.setGridColor(new Color(229, 229, 229));
        table.setSelectionBackground(Color.LIGHT_GRAY);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dtcr);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

}
