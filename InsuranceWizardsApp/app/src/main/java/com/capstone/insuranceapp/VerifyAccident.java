package com.capstone.insuranceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerifyAccident extends AppCompatActivity {

    private Button helpBtn, fineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_accident);

        helpBtn = findViewById(R.id.help_btn);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call for help
            }
        });

        fineBtn = findViewById(R.id.fine_btn);
        fineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // close activity
                finish();
            }
        });
    }
}
