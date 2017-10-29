package main.java.Logic;

import main.java.Logic.bean.Device;
import main.java.Tools.DbUtil;
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

        setDevicesData();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public DbUtil getDbMySQL() {
        if (dbMySQL == null) {
            initDbData();
        }
        return dbMySQL;
    }

    public Vector<String> getDbHeads() {
        if (dbHeads == null) {
            initDbData();
        }
        return dbHeads;
    }

    public Vector<Vector<Object>> getDbDatas() {
        if (dbDatas == null) {
            initDbData();
        }
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
                    "`参数`.`电机转速/（r/m）` from `参数`," +
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

    private void initDbData() {

        try {
            getDatabaseData();
            ResultSetMetaData rsmd = resultSet.getMetaData();

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
                                obj = 100.0f;
                                break;
                            case 4: case 5: case 7: case 8:
                                obj = 1.0f;
                                break;
                            case 20:
                                obj = "";
                                break;
                            default:
                                obj = 0.0f;
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
        try {
            Connection conn = dbMySQL.getConnection();

            int rs = dbMySQL.execUpdate(
                    "INSERT INTO `参数`(" +
                            "`用电设备名称`,`数量`,`机械轴功率/kW`,`电机功率/kW`," +
                            "`电机效率/%`,`利用系数K1`,`设备总功率/kW`,`负荷类别`," +
                            "` 电机转速/（r/m）`)" +
                            "VALUES(" +
                            "'" + device.getDeviceName() + "'," +
                            "'" + device.getNumber() + "'," +
                            "'" + device.getShaftPower() + "'," +
                            "'" + device.getShaftPower() + "'," +
                            "'" + device.getShaftPower() + "'," +
                            "'" + device.getShaftPower() + "'," +
                            "'" + device.getShaftPower() + "'," +
                            "'" + device.getShaftPower() + "'," +
                            "'" + device.getShaftPower() + "'," +
                            "'航海仪器','2',null,null,null,null,'1','II',null);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDevice() {

    }

}
