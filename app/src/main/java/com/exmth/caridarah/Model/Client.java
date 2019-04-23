package com.exmth.caridarah.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

public class Client {

    @PropertyName("email")
    private String mEmail;

    @PropertyName("name")
    private String mName;

    @PropertyName("phone")
    private String mPhone;

    @Exclude
    private String mPassword;

    public Client(String email, String password, String name, String phone){
        mEmail = email;
        mPassword = password;
        mName = name;
        mPhone = phone;
    }

    @PropertyName("email")
    public String getEmail() {
        return mEmail;
    }

    @PropertyName("email")
    public void setEmail(String email) {
        mEmail = email;
    }

    @Exclude
    public String getPassword() {
        return mPassword;
    }

    @Exclude
    public void setPassword(String password) {
        mPassword = password;
    }

    @PropertyName("name")
    public String getName() {
        return mName;
    }

    @PropertyName("name")
    public void setName(String name) {
        mName = name;
    }

    @PropertyName("phone")
    public String getPhone() {
        return mPhone;
    }

    @PropertyName("phone")
    public void setPhone(String phone) {
        mPhone = phone;
    }
}
