package com.capstone.insuranceapp;

public class Claim {
    private String claimNumber, claimStatus, date, description, name, policyNum;
    private String[] location;

    public Claim(){

    }

    public Claim(String claimNumber, String claimStatus, String date, String description, String name, String policyNum, String[] location) {
        this.claimNumber = claimNumber;
        this.claimStatus = claimStatus;
        this.date = date;
        this.description = description;
        this.name = name;
        this.policyNum = policyNum;
        this.location = location;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolicyNum() {
        return policyNum;
    }

    public void setPolicyNum(String policyNum) {
        this.policyNum = policyNum;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }
}
