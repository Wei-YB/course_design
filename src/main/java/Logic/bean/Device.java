package main.java.Logic.bean;

public class Device {

    private Long id;

    private String deviceName;

    private Integer number;

    private Float shaftPower;   //Nothing to do

    private Float motorPower;
    private Float motorEfficiency;
    private Float factorK1;

    private Float devicesPower;

    private Float[] factorK2;
    private Float[] factorK0;

    private String loadType;

    private String motorRevSpeed;   //Nothing to do

    public Device() {
        factorK2 = new Float[5];
        factorK0 = new Float[5];
    }

    /**
     * getter methods
     */

    public Long getId() { return id; }

    public String getDeviceName() { return deviceName; }

    public Integer getNumber() { return number; }

    public Float getShaftPower() { return shaftPower; }

    public Float getMotorPower() { return motorPower; }

    public Float getMotorEfficiency() { return motorEfficiency; }

    public Float getFactorK1() { return factorK1; }

    public Float getDevicesPower() { return devicesPower; }

    public Float getFactorK2(int status) { return factorK2[status]; }

    public Float getFactorK0(int status) { return factorK0[status]; }

    public String getLoadType() { return loadType; }

    public String getMotorRevSpeed() { return motorRevSpeed; }

    /**
     * setter methods
     */

    public void setId(Long id) { this.id = id; }

    public void setDeviceName(String name) { deviceName = name; }

    public void setNumber(Integer num) { number = num; }

    public void setShaftPower(Float sp) { shaftPower = sp; }

    public void setMotorPower(Float mp) { motorPower = mp; }

    public void setMotorEfficiency(Float me) { motorEfficiency = me; }

    public void setFactorK1(Float k1) { factorK1 = k1; }

    public void setDevicesPower(Float dp) { devicesPower = dp; }

    public void setFactorK2(int status, Float k2) { factorK2[status] = k2; }

    public void setFactorK0(int status, Float k0) { factorK0[status] = k0; }

    public void setLoadType(String lt) { loadType = lt; }

    public void setMotorRevSpeed(String mrs) { motorRevSpeed = mrs; }

}