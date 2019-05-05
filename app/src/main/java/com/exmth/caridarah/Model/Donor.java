package com.exmth.caridarah.Model;

public class Donor {
    private String bloodType;
    private String fullName;
    private String phoneNumber;
    private String date;
    private String time;

    public Donor(){
    }

    public Donor(String bloodType, String fullName, String phoneNumber, String date, String time) {
        this.bloodType = bloodType;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
