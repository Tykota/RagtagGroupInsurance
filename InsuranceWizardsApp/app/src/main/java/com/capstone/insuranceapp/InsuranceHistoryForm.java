package com.capstone.insuranceapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class InsuranceHistoryForm extends AppCompatActivity {

    private Button continueBtn;
    private EditText priorInsuranceCompET;
    private String priorInsuranceComp;
    private boolean prevClaimsResponse;
    private RadioGroup prevClaimsRG;
    private AlertDialog.Builder errorAlertBuilder;
    private AlertDialog errorAlert;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_history_form);

        client = (Client)getIntent().getSerializableExtra("client");

        // create alert dialog
        errorAlertBuilder = new AlertDialog.Builder(this);
        errorAlertBuilder.setTitle("Invalid Input");
        errorAlertBuilder.setPositiveButton("Okay", null);

        // add listener to editText
        priorInsuranceCompET = findViewById(R.id.prior_insurance_comp_name);
        priorInsuranceCompET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                priorInsuranceComp = s.toString();
            }
        });

        // set up radio group
        prevClaimsRG = findViewById(R.id.radioGroupPrevClaims);
        prevClaimsRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.yesRB: prevClaimsResponse = true;
                        break;
                    case R.id.noRB: prevClaimsResponse = false;
                        break;
                    default: break;
                }
            }
        });

        // set up continue button
        continueBtn = findViewById(R.id.submit);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    // update client
                    client.setPrevAccident(prevClaimsResponse);
                    client.setPrevInsurance(priorInsuranceComp);

                    // start next activity
                    Intent intent = new Intent(getApplicationContext(), VehicleInformationForm.class);
                    intent.putExtra("client", client);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean validateInput(){
        // validate prior insurance company
        if(priorInsuranceComp == null || priorInsuranceComp.equals("")){
            errorAlertBuilder.setMessage("You need to enter a prior insurance company.");
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
