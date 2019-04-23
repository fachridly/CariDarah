package com.exmth.caridarah.Model;

public class Donor {
    private String bloodType;
    private String fullName;
    private String phoneNumber;
    private String timestamp;

    public Donor(String bloodType, String fullName, String phoneNumber, String timestamp) {
        this.bloodType = bloodType;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.timestamp = timestamp;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
