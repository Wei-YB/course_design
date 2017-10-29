package main.java.Logic;

import main.java.Logic.bean.Device;
import main.java.UI.AppMain;

import java.lang.reflect.Method;
import java.util.Enumeration;

public class Operator {

    public Operator() {

    }

    public float calculate(int status) {

        Enumeration devices = DatabaseManager.getInstance().devices.elements();

        float power = 0.0f;

        while (devices.hasMoreElements()) {

            Device device = (Device)devices.nextElement();

            float result = 1.0f;

            result *= (float)OperatorManager.getter(device, "MotorPower");
            result *= (float)OperatorManager.getter(device, "DevicesPower");
            result *= (float)OperatorManager.getter(device, "FactorK2", status, int.class);
            result *= (float)OperatorManager.getter(device, "FactorK0", status, int.class);
            result *= (float)OperatorManager.getter(device, "FactorK1");
            result *= (int)OperatorManager.getter(device, "Number");
            switch ((String)OperatorManager.getter(device, "LoadType")) {
                case "I":
                    result *= 0.9f;
                    break;
                case "II":
                    result *= 0.65f;
                    break;
                case "III":
                    result *= 1.0f;
                    break;
            }
            result /= (float)OperatorManager.getter(device, "MotorEfficiency") / 100;

            power += result;
        }

        return (float)(Math.round(power * 100)) / 100;
    }
}
