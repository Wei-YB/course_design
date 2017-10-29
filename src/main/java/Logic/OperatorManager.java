package main.java.Logic;

import java.lang.reflect.Method;

public class OperatorManager {

    private static OperatorManager instance;

    public static final String[] deviceAttrs = {
            "Id",
            "DeviceName", "Number",
            "ShaftPower",
            "MotorPower", "MotorEfficiency", "FactorK1",
            "DevicesPower",
            "FactorK2", "FactorK0",
            "FactorK2", "FactorK0",
            "FactorK2", "FactorK0",
            "FactorK2", "FactorK0",
            "FactorK2", "FactorK0",
            "LoadType",
            "MotorRevSpeed"};

    private OperatorManager() {

    }

    public static OperatorManager getInstance() {
        if(instance == null) {
            instance = new OperatorManager();
        }
        return instance;
    }

    public static void setter(Object obj, String attr, Object value, Class<?> type) {
        try{
            Method met = obj.getClass().getMethod("set" + attr, type);
            met.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setter(Object obj, String attr, Object value1, Object value2, Class<?> type1, Class<?> type2) {
        try{
            Method met = obj.getClass().getMethod("set" + attr, type1, type2);
            met.invoke(obj, value1, value2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getter(Object obj, String attr) {
        try {
            Method met = obj.getClass().getMethod("get" + attr);
            return met.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getter(Object obj, String attr, Object arg, Class<?> type) {
        try {
            Method met = obj.getClass().getMethod("get" + attr, type);
            return met.invoke(obj, arg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
