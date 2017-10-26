package main.java.UI.panels;

import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class OperatorPanel extends JPanel {

    private JButton btnAddDevice;
    private JButton btnEditDevice;

    public OperatorPanel() {
        init();
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
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel title = new JLabel("Operator Panel");
        title.setFont(new Font("font", Font.BOLD, 20));
        panel.add(title);

        return panel;
    }

    private JPanel midPanel() {
        JPanel panelMain = new JPanel();

        JPanel panelNorth = new JPanel();

        JPanel panelCenter = new JPanel();
        JPanel[] panelItems = new JPanel[4];

        panelMain.setBackground(UIConstants.MAIN_COLOR);
        panelMain.setLayout(new BorderLayout());

        panelNorth.setBackground(UIConstants.MAIN_COLOR);
        panelNorth.setLayout(new GridLayout(1, 2));

        btnAddDevice = new JButton("Add Device");
        btnEditDevice = new JButton("Edit Device");

        panelNorth.add(btnAddDevice);
        panelNorth.add(btnEditDevice);

        panelCenter.setBackground(UIConstants.MAIN_COLOR);
        panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        for (int i = 0; i < 4; i++) {
            panelItems[i] = new JPanel();
            panelItems[i].setBackground(UIConstants.MAIN_COLOR);
        }
//        panelItems[3] = new JPanel();
//        panelItems[3].setBackground(UIConstants.MAIN_COLOR);

        panelItems[0].setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelItems[1].setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelItems[2].setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelItems[3].setLayout(new GridLayout(2, 3, 12, 12));


        JLabel labelDeviceName = new JLabel("  Device Name:     ");
        JLabel labelDeviceNumber = new JLabel("  Device Number:      ");

        JTextField inputDeviceName = new JTextField();
        JTextField inputDeviceNumber = new JTextField();

        inputDeviceName.setPreferredSize(new Dimension(100, 35));
        inputDeviceNumber.setPreferredSize(new Dimension(70, 35));

        panelItems[0].add(labelDeviceName);
        panelItems[0].add(inputDeviceName);
        panelItems[0].add(labelDeviceNumber);
        panelItems[0].add(inputDeviceNumber);

        JLabel labelShaftEfficiency = new JLabel("   Shaft Efficiency: ");
        JLabel labelGeneratorPower = new JLabel("  Generator Power:  ");
        JLabel labelGeneratorEfficiency = new JLabel("Generator Efficiency:");
        JLabel labelUtilizationFactor = new JLabel("Utilization Factor K1:");
        JLabel labelGeneratorRevSpeed = new JLabel("Generator Rev Speed:");

        JTextField inputShaftEfficiency = new JTextField();
        JTextField inputGeneratorPower = new JTextField();
        JTextField inputGeneratorEfficiency = new JTextField();
        JTextField inputUtilizationFactor = new JTextField();
        JTextField inputGeneratorRevSpeed = new JTextField();

        inputShaftEfficiency.setPreferredSize(new Dimension(100, 35));
        inputGeneratorPower.setPreferredSize(new Dimension(70, 35));
        inputGeneratorEfficiency.setPreferredSize(new Dimension(70, 35));
        inputUtilizationFactor.setPreferredSize(new Dimension(70, 35));
        inputGeneratorRevSpeed.setPreferredSize(new Dimension(70, 35));

        panelItems[1].add(labelShaftEfficiency);
        panelItems[1].add(inputShaftEfficiency);
        panelItems[1].add(labelGeneratorPower);
        panelItems[1].add(inputGeneratorPower);
        panelItems[2].add(labelGeneratorEfficiency);
        panelItems[2].add(inputGeneratorEfficiency);
        panelItems[2].add(labelUtilizationFactor);
        panelItems[2].add(inputUtilizationFactor);
        panelItems[2].add(labelGeneratorRevSpeed);
        panelItems[2].add(inputGeneratorRevSpeed);

        panelItems[3].add(statusPanel("Navigating"));
        panelItems[3].add(statusPanel("Weighing"));
        panelItems[3].add(statusPanel("Berthing"));
        panelItems[3].add(statusPanel("Stevedoring"));
        panelItems[3].add(statusPanel("Emergency"));

        for (int i = 0; i < 4; i++) {
            panelCenter.add(panelItems[i]);
        }

        panelMain.add(panelNorth, BorderLayout.NORTH);
        panelMain.add(panelCenter, BorderLayout.CENTER);

        return panelMain;
    }

    private JPanel statusPanel(String statusName) {
        JPanel panel = new JPanel();

        JPanel[] panelItems = new JPanel[4];

        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new GridLayout(4, 1));

        for (int i = 0; i < 4; i++) {
            panelItems[i] = new JPanel();
            panelItems[i].setBackground(Color.LIGHT_GRAY);
            panelItems[i].setLayout(new FlowLayout(FlowLayout.LEFT, 15, 8));
        }


        JLabel labelNavigating = new JLabel(statusName);
        labelNavigating.setFont(new Font("font", Font.BOLD, 20));
        JLabel labelMachineLoadFactorK2 = new JLabel("Machine Load Factor K2:    ");
        JLabel labelGeneratorLoadFactorK3 = new JLabel("Generator Load Factor K3:");
        JLabel labelLoadType = new JLabel("Load Type:                           ");

        JTextField inputMachineLoadFactorK2 = new JTextField();
        JTextField inputGeneratorLoadFactorK3 = new JTextField();

        inputMachineLoadFactorK2.setPreferredSize(new Dimension(60, 30));
        inputGeneratorLoadFactorK3.setPreferredSize(new Dimension(60, 30));

        JComboBox<String> comboBoxLoadType = new JComboBox<>();
        comboBoxLoadType.addItem(" I      ");
        comboBoxLoadType.addItem(" II     ");
        comboBoxLoadType.addItem(" III    ");

        panelItems[0].add(labelNavigating);
        panelItems[1].add(labelMachineLoadFactorK2);
        panelItems[1].add(inputMachineLoadFactorK2);
        panelItems[2].add(labelGeneratorLoadFactorK3);
        panelItems[2].add(inputGeneratorLoadFactorK3);
        panelItems[3].add(labelLoadType);
        panelItems[3].add(comboBoxLoadType);

        for (int i = 0; i < 4; i++) {
            panel.add(panelItems[i]);
        }

        return panel;
    }

    private void addListener() {

    }
}
