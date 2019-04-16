package com.capstone.insuranceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MonitorDrive extends AppCompatActivity {

    private Button startBtn, stopBtn;
    private TextView message;
    private boolean resumeMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_drive);

        Bundle extras = getIntent().getExtras();
        resumeMonitor = extras.getBoolean("resumeMonitor");

        // Set up buttons and textview
        startBtn = findViewById(R.id.begin_drive_btn);
        stopBtn = findViewById(R.id.end_drive_btn);
        stopBtn.setVisibility(View.INVISIBLE);
        message = findViewById(R.id.message);
        message.setText(R.string.startDriveMessage);

        if(resumeMonitor){
            startService(new Intent(getApplicationContext(), DetectAccident.class));
            startBtn.setVisibility(View.INVISIBLE);
            stopBtn.setVisibility(View.VISIBLE);
            message.setText(R.string.drivingMonitorMessage);
        }
        else{
            stopService(new Intent(getApplicationContext(), DetectAccident.class));
            startBtn.setVisibility(View.VISIBLE);
            stopBtn.setVisibility(View.INVISIBLE);
            message.setText(R.string.startDriveMessage);
        }

        // Set up listeners
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start monitor service
                startService(new Intent(getApplicationContext(), DetectAccident.class));
                startBtn.setVisibility(View.INVISIBLE);
                stopBtn.setVisibility(View.VISIBLE);
                message.setText(R.string.drivingMonitorMessage);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // stop monitor service
                stopService(new Intent(getApplicationContext(), DetectAccident.class));

                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
            }
        });
    }
}
