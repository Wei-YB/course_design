package main.java.Logic;

import main.java.Logic.bean.Device;
import main.java.Tools.DbUtil;
import main.java.UI.AppMain;
import main.java.UI.UIConstants;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


public class DatabaseManager {

    private static DatabaseManager instance;

    private static DbUtil dbMySQL;

    private static ResultSet resultSet;

    private static Vector<Vector<Object>> dbDatas;
    private static Vector<String> dbHeads;

    public Vector<Device> devices;

    private DatabaseManager() {
        init();
        initDbData();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public DbUtil getDbMySQL() {
        return dbMySQL;
    }

    public Vector<String> getDbHeads() {
        return dbHeads;
    }

    public Vector<Vector<Object>> getDbDatas() {
        return dbDatas;
    }

    private void init() {

        dbMySQL = DbUtil.getInstance();

        dbHeads = new Vector<>();
        dbDatas = new Vector<>();

        devices = new Vector<>();
    }


    private void getDatabaseData() {
        try {
            Connection conn = dbMySQL.getConnection();

            if(resultSet != null) {
                resultSet = null;
            }

            resultSet = dbMySQL.execQuery("select" +
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
                    "`参数`.`电机转速/(rpm)` from `参数`," +
                    "`航行`,`起锚`," +
                    "`停泊`,`装卸货`," +
                    "`应急` where" +
                    " `参数`.`序号`=`航行`.`序号` and" +
                    " `参数`.`序号`=`起锚`.`序号` and" +
                    " `参数`.`序号`=`停泊`.`序号` and " +
                    "`参数`.`序号`=`装卸货`.`序号` and " +
                    "`参数`.`序号`=`应急`.`序号`;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initDbData() {

        try {
            getDatabaseData();
            ResultSetMetaData rsmd = resultSet.getMetaData();

            init();

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                dbHeads.add(rsmd.getColumnName(i));
            }

            while (resultSet.next()) {
                Vector<Object> obj = new Vector<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    obj.add(resultSet.getObject(i));
                }
                dbDatas.add(obj);
            }

            setDevicesData();

            dbMySQL.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setDevicesData() {

        try {
            getDatabaseData();
            ResultSetMetaData rsmd = resultSet.getMetaData();

            while (resultSet.next()) {

                Device device = new Device();

                for (int i = 1, status = 0; i <= rsmd.getColumnCount(); i++) {
                    Object obj = resultSet.getObject(i);
                    if (obj == null) {
                        switch (i) {
                            case 3:
                                obj = 1;
                                break;
                            case 6:
                                obj = 100.000001f;
                                break;
                            case 4: case 5: case 7: case 8:
                                obj = 1.000001f;
                                break;
                            case 20:
                                obj = "";
                                break;
                            default:
                                obj = 0.000001f;
                        }
                    }
                    if (i >= 9 && i <= 18) {
                        OperatorManager.setter(
                                device, OperatorManager.deviceAttrs[i - 1],
                                status, obj, int.class, obj.getClass());
                        status += (i + 1) % 2;
                    } else {
                        OperatorManager.setter(device, OperatorManager.deviceAttrs[i - 1], obj, obj.getClass());
                    }
                }
                devices.add(device);
            }
//            System.out.println(devices);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertDevice(Device device) {

        devices.add(device);
        try {

            Connection conn = dbMySQL.getConnection();

            float shaftPower = device.getShaftPower();
            float motorPower = device.getMotorPower();
            float motorEfficiency = device.getMotorEfficiency();
            float factorK1 = device.getFactorK1();
            float devicesPower = device.getDevicesPower();
            String loadType = device.getLoadType();
            String motorRevSpeed = device.getMotorRevSpeed();

            float[] factorK0 = new float[5];
            float[] factorK2 = new float[5];

            String[] items = new String[7];
            String[][] factors = new String[5][2];

            for (int i = 0; i < 5; i++) {
                factorK0[i] = device.getFactorK0(i);
                factorK2[i] = device.getFactorK2(i);

                if (factorK0[i] == 0.000001f) {
                    factors[i][0] = "NULL";
                } else {
                    factors[i][0] = String.valueOf(factorK0[i]);
                }

                if (factorK2[i] == 0.000001f) {
                    factors[i][1] = "NULL";
                } else {
                    factors[i][1] = String.valueOf(factorK2[i]);
                }
            }

            if (shaftPower == 1.000001f) {
                items[0] = "NULL";
            } else {
                items[0] = String.valueOf(shaftPower);
            }

            if (motorPower == 1.000001f) {
                items[1] = "NULL";
            } else {
                items[1] = String.valueOf(motorPower);
            }

            if (motorEfficiency == 100.000001f) {
                items[2] = "NULL";
            } else {
                items[2] = String.valueOf(motorEfficiency);
            }

            if (factorK1 == 1.000001f) {
                items[3] = "NULL";
            } else {
                items[3] = String.valueOf(factorK1);
            }

            if (devicesPower == 1.000001f) {
                items[4] = "NULL";
            } else {
                items[4] = String.valueOf(devicesPower);
            }

            if (loadType.equals("")) {
                items[5] = "NULL";
            } else {
                items[5] = loadType;
            }

            if (motorRevSpeed.equals("")) {
                items[6] = "NULL";
            } else {
                items[6] = "'" + motorRevSpeed + "'";
            }


            dbMySQL.execUpdate(
                    "INSERT INTO `参数` " +
                            "VALUES(" +
                            "NULL, " +
                            "'" + device.getDeviceName() + "', " +
                            device.getNumber() + ", " +
                            items[0] + ", " +
                            items[1] + ", " +
                            items[2] + ", " +
                            items[3] + ", " +
                            items[4] + ", " +
                            "'" + items[5] + "'," +
                            items[6] + ");");

            String[] statusStr = {"航行", "起锚", "停泊", "装卸货", "应急"};

            for (int i = 0; i < 5; i++) {
                dbMySQL.execUpdate(
                        "INSERT INTO `" + statusStr[i] + "` " +
                                "VALUES(" +
                                "NULL, " +
                                factors[i][0] + ", " +
                                factors[i][1] + ");");
            }

            AppMain.toolBarPanel.getButton("Operator")
                    .getParent().setBackground(UIConstants.TOOL_BAR_COLOR);

            AppMain.toolBarPanel.getButton("Database")
                    .getParent().setBackground(UIConstants.TOOL_BAR_ACTIVATED_COLOR);

            AppMain.toolBarPanel.setCurActivatedBtn(AppMain.toolBarPanel.getButton("Database"));

            AppMain app = AppMain.getInstance();
            AppMain.dbPanel.refreshTable();
            app.switchPanel(AppMain.dbPanel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateDevice(Device device, int row) {

        try {

            Connection conn = dbMySQL.getConnection();

            float shaftPower = device.getShaftPower();
            float motorPower = device.getMotorPower();
            float motorEfficiency = device.getMotorEfficiency();
            float factorK1 = device.getFactorK1();
            float devicesPower = device.getDevicesPower();
            String loadType = device.getLoadType();
            String motorRevSpeed = device.getMotorRevSpeed();

            float[] factorK0 = new float[5];
            float[] factorK2 = new float[5];

            String[] items = new String[7];
            String[][] factors = new String[5][2];

            for (int i = 0; i < 5; i++) {
                factorK0[i] = device.getFactorK0(i);
                factorK2[i] = device.getFactorK2(i);

                if (factorK0[i] == 0.000001f) {
                    factors[i][0] = "NULL";
                } else {
                    factors[i][0] = String.valueOf(factorK0[i]);
                }

                if (factorK2[i] == 0.000001f) {
                    factors[i][1] = "NULL";
                } else {
                    factors[i][1] = String.valueOf(factorK2[i]);
                }
            }

            if (shaftPower == 1.000001f) {
                items[0] = "NULL";
            } else {
                items[0] = String.valueOf(shaftPower);
            }

            if (motorPower == 1.000001f) {
                items[1] = "NULL";
            } else {
                items[1] = String.valueOf(motorPower);
            }

            if (motorEfficiency == 100.000001f) {
                items[2] = "NULL";
            } else {
                items[2] = String.valueOf(motorEfficiency);
            }

            if (factorK1 == 1.000001f) {
                items[3] = "NULL";
            } else {
                items[3] = String.valueOf(factorK1);
            }

            if (devicesPower == 1.000001f) {
                items[4] = "NULL";
            } else {
                items[4] = String.valueOf(devicesPower);
            }

            if (loadType.equals("")) {
                items[5] = "NULL";
            } else {
                items[5] = loadType;
            }

            if (motorRevSpeed.equals("")) {
                items[6] = "NULL";
            } else {
                items[6] = "'" + motorRevSpeed + "'";
            }


            dbMySQL.execUpdate(
                    "UPDATE `参数` " +
                            "SET " +
                            "`用电设备名称` = '" + device.getDeviceName() + "', " +
                            "`数量` = " + device.getNumber() + ", " +
                            "`机械轴功率/kW` = " + items[0] + ", " +
                            "`电机功率/kW` = " + items[1] + ", " +
                            "`电机效率/%` = " + items[2] + ", " +
                            "`利用系数K1` = " + items[3] + ", " +
                            "`设备总功率/kW` = " + items[4] + ", " +
                            "`负荷类别` = '" + items[5] + "', " +
                            "`电机转速/(rpm)` = " + items[6] + " " +
                            "WHERE " +
                            "`序号` = " + row + ";");

            String[] statusStr = {"航行", "起锚", "停泊", "装卸货", "应急"};

            for (int i = 0; i < 5; i++) {
                dbMySQL.execUpdate(
                        "UPDATE `" + statusStr[i] + "` " +
                                "SET " +
                                "`K2` = " + factors[i][0] + ", " +
                                "`K0` = " + factors[i][1] + " " +
                                "WHERE " +
                                "`序号` = " + row + ";");
            }

            AppMain.toolBarPanel.getButton("Operator")
                    .getParent().setBackground(UIConstants.TOOL_BAR_COLOR);

            AppMain.toolBarPanel.getButton("Database")
                    .getParent().setBackground(UIConstants.TOOL_BAR_ACTIVATED_COLOR);

            AppMain.toolBarPanel.setCurActivatedBtn(AppMain.toolBarPanel.getButton("Database"));

            AppMain app = AppMain.getInstance();
            AppMain.dbPanel.refreshTable();
            app.switchPanel(AppMain.dbPanel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDevice(int row) {

        try {

            Connection conn = dbMySQL.getConnection();

            dbMySQL.execUpdate("DELETE FROM `参数` WHERE `序号` = " + row);
            String[] statusStr = {"航行", "起锚", "停泊", "装卸货", "应急"};

            for (int i = 0; i < 5; i++) {
                dbMySQL.execUpdate("DELETE FROM `" + statusStr[i] + "` " +
                        " WHERE `序号` = " + row);
            }

            DatabaseManager.getInstance().devices.remove(row);

            AppMain app = AppMain.getInstance();
            AppMain.dbPanel.refreshTable();
            app.switchPanel(AppMain.dbPanel);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
