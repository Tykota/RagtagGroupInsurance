package com.capstone.insuranceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class showCard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        TextView Policyholder = (TextView) findViewById(R.id.policyHolder);
        TextView PolicyNumber = (TextView) findViewById(R.id.policyNumber);
        TextView ExpDate = (TextView) findViewById(R.id.expDate);

        String holderVal;
        String policyNum;
        String expiration;


        //Throw the policyholder/number/expdate in from firestore
        var clientsRef = db.collection("clients").doc();
        clientsRef.get().then(function(doc) {
            if (doc.exists) {
                console.log("Document data:", doc.data());
                holderVal = doc.data().name;
                policyNum = doc.data().applicationNum;
                //unsure where to pull this from
                expiration = "4/25/2022";
            } else {
                // doc.data() will be undefined in this case
                console.log("No such document!");
            }
        }).catch(function(error) {
            console.log("Error getting document:", error);
        });



        //Add in the holder name, policy number and Expiration date
        Policyholder.setText(Policyholder.getText() + " " + holderVal);
        PolicyNumber.setText(PolicyNumber.getText() + " " + policyNum);
        ExpDate.setText(ExpDate.getText() + " " + expiration);


    }







}
