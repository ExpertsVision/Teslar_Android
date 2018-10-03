package com.eveati.sajid.teslar.Model;

public class GetPowerMonth_Model {
    int month;
    int power;
    int year;
    int date;
    int time_spand;


    public GetPowerMonth_Model(int month, int power,int year,int date,int time_spand) {
        this.month = month;
        this.power = power;
        this.year = year;
        this.date = date;
        this.time_spand= time_spand;
    }

    public int getTime_spand() {
        return time_spand;
    }

    public void setTime_spand(int time_spand) {
        this.time_spand = time_spand;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
