package com.eveati.sajid.teslar.Model;

public class ApiValues_Model {
    private String system_id;
    private String date;
    private int timespan;
    private int total_power_ac;
    private int total_power_dc;
    private int voltage_ac;
    private int current_ac;
    private int voltage_dc;
    private int current_dc;
    private int humidity;
    private int temperature;



    public ApiValues_Model(String s){}

    public ApiValues_Model(String system_id, String date, int timespan, int total_power_ac, int total_power_dc, int voltage_ac, int current_ac, int voltage_dc, int current_dc,int humidity,int temperature) {

        this.system_id = system_id;
        this.date = date;
        this.timespan = timespan;
        this.total_power_ac = total_power_ac;
        this.total_power_dc = total_power_dc;
        this.voltage_ac = voltage_ac;
        this.current_ac = current_ac;
        this.voltage_dc = voltage_dc;
        this.current_dc = current_dc;
        this.humidity = humidity;
        this.temperature = temperature;
    }
    public String getSystem_id() {
        return system_id;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimespan() {
        return timespan;
    }

    public void setTimespan(int timespan) {
        this.timespan = timespan;
    }

    public int getTotal_power_ac() {
        return total_power_ac;
    }

    public void setTotal_power_ac(int total_power_ac) {
        this.total_power_ac = total_power_ac;
    }

    public int getTotal_power_dc() {
        return total_power_dc;
    }

    public void setTotal_power_dc(int total_power_dc) {
        this.total_power_dc = total_power_dc;
    }

    public int getVoltage_ac() {
        return voltage_ac;
    }

    public void setVoltage_ac(int voltage_ac) {
        this.voltage_ac = voltage_ac;
    }

    public int getCurrent_ac() {
        return current_ac;
    }

    public void setCurrent_ac(int current_ac) {
        this.current_ac = current_ac;
    }

    public int getVoltage_dc() {
        return voltage_dc;
    }

    public void setVoltage_dc(int voltage_dc) {
        this.voltage_dc = voltage_dc;
    }

    public int getCurrent_dc() {
        return current_dc;
    }

    public void setCurrent_dc(int current_dc) {
        this.current_dc = current_dc;
    }
    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

}



