package com.capstone.insuranceapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class UserInfoForm extends AppCompatActivity {

    private Spinner stateSpinner;
    private String stateSelected, zipCode, address, email, phoneNum;
    private EditText zipCodeET, addressET, emailET, phoneNumET;
    private Button continueBtn;
    private AlertDialog.Builder errorAlertBuilder;
    private AlertDialog errorAlert;
    private Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_form);

        client = (Client)getIntent().getSerializableExtra("client");

        // set default stateSelected and driverType selected.
        stateSelected = "AL";

        // create alert dialog
        errorAlertBuilder = new AlertDialog.Builder(this);
        errorAlertBuilder.setTitle("Invalid Input");
        errorAlertBuilder.setPositiveButton("Okay", null);

        // add listener to editTexts
        addressET = findViewById(R.id.address);
        addressET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                address = s.toString();
            }
        });

        zipCodeET = findViewById(R.id.zipcode);
        zipCodeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                zipCode = s.toString();
            }
        });

        emailET = findViewById(R.id.email);
        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                email = s.toString();
            }
        });

        phoneNumET = findViewById(R.id.phonenum);
        phoneNumET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneNum = s.toString();
            }
        });

        // add listener to spinners
        stateSpinner = findViewById(R.id.stateSpinner);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stateSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        continueBtn = findViewById(R.id.submit);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    // this will either send data to firebase or to next section of the form

                    Intent intent = new Intent(getApplicationContext(), InsuranceHistoryForm.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean validateInput(){
        // validate address
        if(address == null || address.equals("")){
            errorAlertBuilder.setMessage("You need to enter a address.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate zip code
        if(zipCode == null || zipCode.equals("")){
            errorAlertBuilder.setMessage("You need to enter a zip code.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate email
        if(email == null || email.equals("")){
            errorAlertBuilder.setMessage("You need to enter an email.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate zip code
        if(phoneNum == null || phoneNum.equals("")){
            errorAlertBuilder.setMessage("You need to enter a zip code.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        return true;
    }

    public void resetErrorDialog(){
        errorAlert = null;
        errorAlertBuilder = null;
        errorAlertBuilder = new AlertDialog.Builder(this);
        errorAlertBuilder.setTitle("Invalid Input");
        errorAlertBuilder.setPositiveButton("Okay", null);
    }
}
