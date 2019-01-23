package com.exmth.caridarah.Model;

public class Client {
    private String mEmail;
    private String mPassword;
    private String mName;
    private String mPhone;

    public Client(){}

    public Client(String email, String password, String name, String phone){
        mEmail = email;
        mPassword = password;
        mName = name;
        mPhone = phone;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }
}
