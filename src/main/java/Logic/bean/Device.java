package main.java.Logic.bean;

public class Device {

    private String deviceName;

    private int number;

    private float shaftPower;   //Nothing to do

    private float motorPower;
    private float motorEfficiency;
    private float factorK1;

    private float devicesPower;

    private float[] factorK2;
    private float[] factorK0;

    private int loadType;

    private String motorRevSpeed;   //Nothing to do

    public Device() {
        factorK2 = new float[5];
        factorK0 = new float[5];
    }

    /**
     * getter methods
     */

    public String getDeviceName() { return deviceName; }

    public int getNumber() { return number; }

    public float getShaftPower() { return shaftPower; }

    public float getMotorPower() { return motorPower; }

    public float getMotorEfficiency() { return motorEfficiency; }

    public float getFactorK1() { return factorK1; }

    public float getDevicesPower() { return devicesPower; }

    public float getFactorK2(int status) { return factorK2[status]; }

    public float getFactorK0(int status) { return factorK0[status]; }

    public int getLoadType() { return loadType; }

    public String getMotorRevSpeed() { return motorRevSpeed; }

    /**
     * setter methods
     */

    public void setDeviceName(String name) { deviceName = name; }

    public void setNumber(int num) { number = num; }

    public void setShaftPower(float sp) { shaftPower = sp; }

    public void setMotorPower(float mp) { motorPower = mp; }

    public void setMotorEfficiency(float me) { motorEfficiency = me; }

    public void setFactorK1(float k1) { factorK1 = k1; }

    public void setDevicesPower(float dp) { devicesPower = dp; }

    public void setFactorK2(int status, float k2) { factorK2[status] = k2; }

    public void setFactorK0(int status, float k0) { factorK0[status] = k0; }

    public void setLoadType(int lt) { loadType = lt; }

    public void setMotorRevSpeed(String mrs) { motorRevSpeed = mrs; }

}
