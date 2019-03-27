package com.capstone.insuranceapp;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Client implements Serializable {
    public String applicationNum;
    public String name;
    public String gender;
    public String ssn;
    public String address;
    public String city;
    public String state;
    public String zip;
    public String dlnumber;
    public String dob;
    public String drivertype;
    public String phone;
    public String marital;
    public String email;
    public boolean prevAccident;
    public String prevInsurance;
    public String appStatus;

    public Client() {
       // generateAppNum();
        // Default
    }
    public Client(String applicationNum, String name, String gender, String ssn, String address, String city, String state, String zip,
                  String dlnumber, String dob, String drivertype, String phone, String marital, String appStatus) { // , Drivers[] drivers, Minors[] minors
        this.applicationNum = applicationNum;
        this.name = name;
        this.gender = gender;
        this.ssn = ssn;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.dlnumber = dlnumber;
        this.dob = dob;
        this.drivertype = drivertype;
        this.phone = phone;
        this.marital = marital;
        this.appStatus = appStatus;
//        this.drivers = drivers;
//        this.minors = minors;
    }

    public String getApplicationNum() {
        return applicationNum;
    }

    public void setApplicationNum(String applicationNum) {
        this.applicationNum = applicationNum;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public boolean isPrevAccident() {
        return prevAccident;
    }

    public void setPrevAccident(boolean prevAccident) {
        this.prevAccident = prevAccident;
    }

    public String getPrevInsurance() {
        return prevInsurance;
    }

    public void setPrevInsurance(String prevInsurance) {
        this.prevInsurance = prevInsurance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDlnumber() {
        return dlnumber;
    }

    public void setDlnumber(String dlnumber) {
        this.dlnumber = dlnumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDrivertype() {
        return drivertype;
    }

    public void setDrivertype(String drivertype) {
        this.drivertype = drivertype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // We will just use document ID as application number

    // Heres the generate application number function
    // wasnt sure where to put it, can move whereever
    /*
    public void generateAppNum(){
        Boolean newApp = false;
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String genString = "";

        while(newApp){
            genString = getNewAppNum();
            Query result = database.collection("clients").whereEqualTo("applicationNum", genString);
            if(result != null){
                // document exists, generate new string
            }
            else {
                // We have unique ApplicationNumber
                newApp = true;
            }
        }

        this.applicationNum = genString;

    }
    protected String getNewAppNum(){
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randString = new StringBuilder();
        Random rand = new Random();
        while(randString.length() < 10){
            int index = (int) (rand.nextFloat() * CHARS.length());
            randString.append(CHARS.charAt(index));
        }
        String retString = randString.toString();
        return retString;
    }
    */
}
