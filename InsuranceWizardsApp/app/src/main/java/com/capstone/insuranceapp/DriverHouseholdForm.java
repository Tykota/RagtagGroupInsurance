package com.capstone.insuranceapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

public class DriverHouseholdForm extends AppCompatActivity {

    private Spinner stateSpinner, driverTypeSpinner;
    private boolean stateSelectedStatus = false, driverTypeSelectedStatus = false;
    private String stateSelected, driverTypeSelected, genderSelected, maritalSelected, name;
    private String dateOfBirth, ssn, driversLicenseNum, namesUnder18, namesNonLicensed;
    private DatePickerDialog datePicker;
    private EditText dateOfBirthET, nameET, ssnET, driversLicenseET, namesUnder18ET, namesNonLicensedET;
    private Button continueBtn;
    private RadioButton maleRB, femaleRB, marriedRB, singleRB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_household_form);

        // add listener to editTexts
        nameET = findViewById(R.id.name);
        nameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = s.toString();
            }
        });

        driversLicenseET = findViewById(R.id.driverLicenseNum);
        driversLicenseET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                driversLicenseNum = s.toString();
            }
        });

        ssnET = findViewById(R.id.SSN);
        ssnET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ssn = s.toString();
            }
        });

        namesUnder18ET = findViewById(R.id.names_under18);
        namesUnder18ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                namesUnder18 = s.toString();
            }
        });

        namesNonLicensedET = findViewById(R.id.names_nonLicensed);
        namesNonLicensedET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                namesNonLicensed = s.toString();
            }
        });

        // define radio buttons
        maleRB = findViewById(R.id.male);
        femaleRB = findViewById(R.id.female);
        marriedRB = findViewById(R.id.married);
        singleRB = findViewById(R.id.single);

        // add listener to spinners
        stateSpinner = findViewById(R.id.stateSpinner);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stateSelectedStatus = true;
                stateSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        driverTypeSpinner = findViewById(R.id.driverTypeSpinner);
        driverTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                driverTypeSelectedStatus = true;
                driverTypeSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // set up date of birth date picker
        dateOfBirthET = findViewById(R.id.dob);
        dateOfBirthET.setInputType(InputType.TYPE_NULL);
        dateOfBirthET.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(DriverHouseholdForm.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateOfBirthET.setText(dayOfMonth + "/" +(month + 1) + "/" + year);
                        dateOfBirth = (dayOfMonth + "/" +(month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        continueBtn = findViewById(R.id.submit);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
            }
        });
    }
}
