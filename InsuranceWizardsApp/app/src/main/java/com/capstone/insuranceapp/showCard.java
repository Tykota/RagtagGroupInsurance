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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class showCard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        final TextView policyHolder = (TextView) findViewById(R.id.policyHolder);
        final TextView policyNumber = (TextView) findViewById(R.id.policyNumber);
        final TextView expDate = (TextView) findViewById(R.id.expDate);

        //Throw the policyholder/number/expdate in from firestore
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference docRef = database.collection("clients").document("ZJw3199HtxZeEAMOZdjY");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Client client = documentSnapshot.toObject(Client.class);
                String holder = policyHolder.getText() + client.getName();
                policyHolder.setText(holder);
                String num = policyNumber.getText() + client.getApplicationNum();
                policyNumber.setText(num);
                String date = expDate.getText() + "4/25/2022";
                expDate.setText(date);
            }
        });

    }

}
