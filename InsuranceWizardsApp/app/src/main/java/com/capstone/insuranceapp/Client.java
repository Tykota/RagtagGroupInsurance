package com.capstone.insuranceapp;

public class Client {
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
 //   public Drivers[] drivers;
 //   public Minors[] minors;

    public Client() {
        // Default
    }
    public Client(String applicationNum, String name, String gender, String ssn, String address, String city, String state, String zip,
                  String dlnumber, String dob, String drivertype, String phone) { // , Drivers[] drivers, Minors[] minors
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
//        this.drivers = drivers;
//        this.minors = minors;
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
}
