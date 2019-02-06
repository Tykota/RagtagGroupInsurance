package com.capstone.insuranceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText username, password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, HomePage.class);
        if(username.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "You must enter both a username and password.", Toast.LENGTH_LONG).show();
        }
        else if(!username.getText().toString().equals("admin") || !password.getText().toString().equals("password")){
            Toast.makeText(MainActivity.this, "Incorrect username or password.", Toast.LENGTH_LONG).show();
        }
        else{
            startActivity(intent);
        }
    }
}
