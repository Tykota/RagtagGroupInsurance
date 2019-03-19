package com.capstone.insuranceapp;

import android.app.AlertDialog;
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
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

public class DriverHouseholdForm extends AppCompatActivity {

    private Spinner stateSpinner, driverTypeSpinner;
    private String stateSelected, driverTypeSelected, genderSelected, maritalSelected, name;
    private String dateOfBirth, ssn, driversLicenseNum, namesUnder18, namesNonLicensed;
    private DatePickerDialog datePicker;
    private EditText dateOfBirthET, nameET, ssnET, driversLicenseET, namesUnder18ET, namesNonLicensedET;
    private Button continueBtn;
    private RadioGroup genderRG, maritalRG;
    private AlertDialog.Builder errorAlertBuilder;
    private AlertDialog errorAlert;
    private Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_household_form);

        // set default stateSelected and driverType selected.
        stateSelected = "AL";
        driverTypeSelected = "M - Motorcycle";

        client = new Client(); // initialize client object

        // create alert dialog
        errorAlertBuilder = new AlertDialog.Builder(this);
        errorAlertBuilder.setTitle("Invalid Input");
        errorAlertBuilder.setPositiveButton("Okay", null);

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

        // set up radio groups and radio buttons
        genderRG = findViewById(R.id.radioGroupGender);
        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.male: genderSelected = "male";
                        break;
                    case R.id.female: genderSelected = "female";
                        break;
                    default: break;
                }
            }
        });

        maritalRG = findViewById(R.id.radioGroupMaritalStatus);
        maritalRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.married: maritalSelected = "married";
                        break;
                    case R.id.single: maritalSelected = "single";
                        break;
                    default: break;
                }
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

        driverTypeSpinner = findViewById(R.id.driverTypeSpinner);
        driverTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                if(validateInput()){
                    // Update client object
                    client.setName(name);
                    client.setDlnumber(driversLicenseNum);
                    client.setSsn(ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5));
                    client.setGender(genderSelected);
                    client.setDob(dateOfBirth);
                    client.setDrivertype(driverTypeSelected);
                    client.setState(stateSelected);
                    client.setMarital(maritalSelected);

                    // Start new activity
                    Intent intent = new Intent(getApplicationContext(), UserInfoForm.class);
                    intent.putExtra("client", client);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean validateInput(){
        // validate name
        if(name == null || name.equals("")){
            errorAlertBuilder.setMessage("You need to enter a name.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate date of birth
        else if(dateOfBirth == null || dateOfBirth.equals("")){
            errorAlertBuilder.setMessage("You need to enter a date of birth.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate gender selected
        else if(genderSelected == null || genderSelected.equals("")){
            errorAlertBuilder.setMessage("You need to choose a gender.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate marital status selected
        else if(maritalSelected == null || maritalSelected.equals("")){
            errorAlertBuilder.setMessage("You need to choose a marital status.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate ssn
        else if(ssn == null || ssn.equals("")){
            errorAlertBuilder.setMessage("You need to enter a social security number.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }
        else if(ssn.length() != 9){
            errorAlertBuilder.setMessage("You need to enter a nine digit social security number.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate drivers license number
        else if(driversLicenseNum == null || driversLicenseNum.equals("")){
            errorAlertBuilder.setMessage("You need to enter a driver's license number.");
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
