package main.java.UI.panels;

import main.java.Logic.Operator;
import main.java.Tools.RegExpForTextField;
import main.java.UI.IconButton;
import main.java.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class OperatorPanel extends JPanel {

    private IconButton btnAddDevice;
    private IconButton btnEditDevice;

    private JTextField inputDeviceName;
    private JTextField inputDeviceNumber;

    private JTextField inputShaftPower;
    private JTextField inputGeneratorPower;
    private JTextField inputGeneratorEfficiency;
    private JTextField inputUtilizationFactor;
    private JTextField inputGeneratorRevSpeed;

    private JPanel[] panelItems;    //TextFields in [1](K0) and [2](K2)
                                    //ComboBox in [3]


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
//        this.add(upperPanel(), BorderLayout.NORTH);
        this.add(midPanel(), BorderLayout.CENTER);
    }

    private JPanel upperPanel() {
        JPanel panel = new JPanel();

        panel.setBackground(UIConstants.SUB_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 13));

        JLabel title = new JLabel("Operating Panel");
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

        panelNorth.setBackground(new Color(201, 201, 201));
        panelNorth.setLayout(new GridLayout(1, 2, 20, 15));

        btnAddDevice = new IconButton(UIConstants.ICON_OPERATOR_ADD,
                "Add Device",
                "Add Device into the database");
        btnEditDevice = new IconButton(UIConstants.ICON_OPERATOR_EDIT,
                "Edit Device",
                "Edit Device from the database");

        panelNorth.add(btnAddDevice);
        panelNorth.add(btnEditDevice);

        panelCenter.setBackground(UIConstants.SSUB_COLOR);
        panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        for (int i = 0; i < 4; i++) {
            panelItems[i] = new JPanel();
            panelItems[i].setBackground(UIConstants.SSUB_COLOR);
        }
//        panelItems[3] = new JPanel();
//        panelItems[3].setBackground(UIConstants.MAIN_COLOR);

        panelItems[0].setLayout(new FlowLayout(FlowLayout.CENTER, 22, 13));
        panelItems[1].setLayout(new FlowLayout(FlowLayout.LEFT, 15, 12));
        panelItems[2].setLayout(new FlowLayout(FlowLayout.LEFT, 15, 12));
        panelItems[3].setLayout(new GridLayout(2, 3, 12, 12));

        JLabel labelDeviceName = new JLabel("      Device Name:    ");
        JLabel labelDeviceNumber = new JLabel("     Device Number:   ");

        inputDeviceName = new JTextField();
        inputDeviceNumber = new JTextField();

        inputDeviceName.setPreferredSize(new Dimension(125, 30));
        inputDeviceNumber.setPreferredSize(new Dimension(125, 30));

        inputDeviceNumber.setDocument(new RegExpForTextField("^(([1-9])|([1-9][0-9]))$"));
        inputDeviceNumber.setHorizontalAlignment(JTextField.CENTER);

        panelItems[0].add(labelDeviceName);
        panelItems[0].add(inputDeviceName);
        panelItems[0].add(labelDeviceNumber);
        panelItems[0].add(inputDeviceNumber);

        JLabel labelShaftPower = new JLabel("      Shaft Power:       ");
        JLabel labelGeneratorPower = new JLabel("      Generator Power:  ");
        JLabel labelGeneratorEfficiency = new JLabel("Generator Efficiency:");
        JLabel labelUtilizationFactor = new JLabel("Utilization Factor K1:");
        JLabel labelGeneratorRevSpeed = new JLabel("Generator Rev Speed:");

        JLabel labelSPUnit = new JLabel("kW");
        JLabel labelGPUnit = new JLabel("kW");
        JLabel labelGEUnit = new JLabel("%");
        JLabel labelGRSUnit = new JLabel("rpm");

        labelSPUnit.setFont(new Font("font", Font.ITALIC, 15));
        labelGPUnit.setFont(new Font("font", Font.ITALIC, 15));
        labelGEUnit.setFont(new Font("font", Font.ITALIC, 15));
        labelGRSUnit.setFont(new Font("font", Font.ITALIC, 15));

        inputShaftPower = new JTextField();
        inputGeneratorPower = new JTextField();
        inputGeneratorEfficiency = new JTextField();
        inputUtilizationFactor = new JTextField();
        inputGeneratorRevSpeed = new JTextField();

        inputShaftPower.setPreferredSize(new Dimension(90, 30));
        inputGeneratorPower.setPreferredSize(new Dimension(90, 30));
        inputGeneratorEfficiency.setPreferredSize(new Dimension(60, 30));
        inputUtilizationFactor.setPreferredSize(new Dimension(70, 30));
        inputGeneratorRevSpeed.setPreferredSize(new Dimension(60, 30));

        inputShaftPower.setDocument(new RegExpForTextField("^(([1-9])|([1-9][0-9]))$"));
        inputGeneratorPower.setDocument(new RegExpForTextField("^(([1-9])|([1-9][0-9]))$"));
        inputGeneratorEfficiency.setDocument(new RegExpForTextField("^(([1-9])|([1-9][0-9])|(100))$"));
        inputUtilizationFactor.setDocument(new RegExpForTextField(
                "^((1)|(0)|(0\\.)|(0\\.[0-9])|(0\\.[0-9][1-9])|(0\\.[1-9][0-9]))$"));
        inputGeneratorRevSpeed.setDocument(new RegExpForTextField(
                "^(([1-9])|([1-9][0-9])|([1-9][0-9][0-9]|([1-2][0-9][0-9][0-9])|(3000)))$"));

        inputShaftPower.setHorizontalAlignment(JTextField.CENTER);
        inputGeneratorPower.setHorizontalAlignment(JTextField.CENTER);
        inputGeneratorEfficiency.setHorizontalAlignment(JTextField.CENTER);
        inputUtilizationFactor.setHorizontalAlignment(JTextField.CENTER);
        inputGeneratorRevSpeed.setHorizontalAlignment(JTextField.CENTER);

        inputUtilizationFactor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ifInputValueInvalid(inputUtilizationFactor.getText());
                inputUtilizationFactor.setText("");
            }
        });

        panelItems[1].add(labelShaftPower);
        panelItems[1].add(inputShaftPower);
        panelItems[1].add(labelSPUnit);

        panelItems[1].add(labelGeneratorPower);
        panelItems[1].add(inputGeneratorPower);
        panelItems[1].add(labelGPUnit);

        panelItems[2].add(labelGeneratorEfficiency);
        panelItems[2].add(inputGeneratorEfficiency);
        panelItems[2].add(labelGEUnit);

        panelItems[2].add(labelGeneratorRevSpeed);
        panelItems[2].add(inputGeneratorRevSpeed);
        panelItems[2].add(labelGRSUnit);

        panelItems[2].add(labelUtilizationFactor);
        panelItems[2].add(inputUtilizationFactor);

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

        panelItems = new JPanel[4];

        panel.setBackground(UIConstants.MAIN_COLOR);
        panel.setLayout(new GridLayout(4, 1));

        for (int i = 0; i < 4; i++) {
            panelItems[i] = new JPanel();
            panelItems[i].setBackground(Color.LIGHT_GRAY);
            panelItems[i].setLayout(new FlowLayout(FlowLayout.LEFT, 15, 8));
        }


        JLabel labelNavigating = new JLabel(statusName);
        labelNavigating.setFont(new Font("font", Font.BOLD, 20));
        JLabel labelCoincidenceFactorK0 = new JLabel("Coincidence Factor K0:       ");
        JLabel labelMachineLoadFactorK2 = new JLabel("Machine Load Factor K2:    ");
        JLabel labelLoadType = new JLabel("Load Type:                           ");

        JTextField inputCoincidenceFactorK0 = new JTextField();
        JTextField inputMachineLoadFactorK2 = new JTextField();

        inputCoincidenceFactorK0.setPreferredSize(new Dimension(60, 30));
        inputMachineLoadFactorK2.setPreferredSize(new Dimension(60, 30));

        inputCoincidenceFactorK0.setDocument(new RegExpForTextField(
                "^((1)|(0)|(0\\.)|(0\\.[0-9])|(0\\.[0-9][1-9])|(0\\.[1-9][0-9]))$"));
        inputMachineLoadFactorK2.setDocument(new RegExpForTextField(
                "^((1)|(0)|(0\\.)|(0\\.[0-9])|(0\\.[0-9][1-9])|(0\\.[1-9][0-9]))$"));

        inputCoincidenceFactorK0.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ifInputValueInvalid(inputCoincidenceFactorK0.getText());
                inputCoincidenceFactorK0.setText("");
            }
        });

        inputMachineLoadFactorK2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ifInputValueInvalid(inputMachineLoadFactorK2.getText());
                inputMachineLoadFactorK2.setText("");
            }
        });

        inputCoincidenceFactorK0.setHorizontalAlignment(JTextField.CENTER);
        inputMachineLoadFactorK2.setHorizontalAlignment(JTextField.CENTER);

        JComboBox<String> comboBoxLoadType = new JComboBox<>();
        comboBoxLoadType.addItem(" I      ");
        comboBoxLoadType.addItem(" II     ");
        comboBoxLoadType.addItem(" III    ");

        panelItems[0].add(labelNavigating);
        panelItems[1].add(labelCoincidenceFactorK0);
        panelItems[1].add(inputCoincidenceFactorK0);
        panelItems[2].add(labelMachineLoadFactorK2);
        panelItems[2].add(inputMachineLoadFactorK2);
        panelItems[3].add(labelLoadType);
        panelItems[3].add(comboBoxLoadType);

        for (int i = 0; i < 4; i++) {
            panel.add(panelItems[i]);
        }

        return panel;
    }

    private void ifInputValueInvalid(String text) {
        if (text.equals("0.") || text.equals("0.0")) {
            System.out.println("Invalid value");
            JOptionPane.showMessageDialog(
                    null,
                    "invalid value",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addListener() {

        btnAddDevice.addActionListener((e -> {
            Operator operator = new Operator();
        }));

        btnEditDevice.addActionListener((e -> {

        }));
    }
}
