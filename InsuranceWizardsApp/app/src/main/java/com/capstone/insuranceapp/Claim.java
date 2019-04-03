package com.capstone.insuranceapp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Random;

public class Claim {
    private String claimNumber, claimStatus, date, description, name, policyNum, location;

    public Claim() {
        generatePolicyNum();
        generateClaimNum();
    }

    public Claim(String claimNumber, String claimStatus, String date, String description, String name, String policyNum, String location) {
        this.claimNumber = claimNumber;
        this.claimStatus = claimStatus;
        this.date = date;
        this.description = description;
        this.name = name;
        this.policyNum = policyNum;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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

    public void generatePolicyNum() {
        Boolean newApp = false;
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String genString = calcNum();

        Query result = database.collection("claims").whereEqualTo("policyNumber", genString);
        result.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (!task.getResult().isEmpty()) {
                        generatePolicyNum();
                    }
                }
            }
        });

        this.policyNum = genString;
    }

    public void generateClaimNum() {
        Boolean newApp = false;
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String genString = calcNum();

        Query result = database.collection("claims").whereEqualTo("claimNumber", genString);
        result.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (!task.getResult().isEmpty()) {
                        generateClaimNum();
                    }
                }

            }
        });

        this.claimNumber = genString;

    }

    protected String calcNum() {
        Random rnd = new Random();
        char[] digits = new char[10];
        digits[0] = (char) (rnd.nextInt(9) + '1');
        for (int i = 1; i < digits.length; i++) {
            digits[i] = (char) (rnd.nextInt(10) + '0');
        }
        return new String(digits);
    }

}
