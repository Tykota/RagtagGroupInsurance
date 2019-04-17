package com.capstone.insuranceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    private Button manageBtn, submitClaimBtn, startMonitorBtn, viewCardBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        manageBtn = (Button) findViewById(R.id.manage_policy_btn);
        manageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), managePolicy.class);
                startActivity(intent);
            }
        });

        submitClaimBtn = findViewById(R.id.submit_claim_btn);
        submitClaimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubmitClaim.class);
                startActivity(intent);
            }
        });

        viewCardBtn = findViewById(R.id.view_card_btn);
        viewCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), showCard.class);
                startActivity(intent);
            }
        });

        startMonitorBtn = findViewById(R.id.monitor_drive_btn);
        startMonitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MonitorDrive.class);
                intent.putExtra("resumeMonitor", false);
                startActivity(intent);
            }
        });
    }



}
