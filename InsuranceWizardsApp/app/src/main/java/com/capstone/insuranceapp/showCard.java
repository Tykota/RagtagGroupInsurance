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

        //Throw the policyholder/number/expdate in from firestore



    }







}
