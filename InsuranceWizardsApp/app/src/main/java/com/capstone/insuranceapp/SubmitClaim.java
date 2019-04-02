package com.capstone.insuranceapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.location.Location;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class SubmitClaim extends AppCompatActivity {

    private Button takePicBtn, submitBtn, takeVidBtn;
    private Claim claim;
    private EditText nameET, descriptET, dateOfAccidentET, timeOfAccidentET;
    private String description, date, time, name, location;
    private DatePickerDialog datePicker;
    private AlertDialog.Builder errorAlertBuilder;
    private AlertDialog errorAlert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_claim);

        // create claim
        claim = new Claim();

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

        descriptET = findViewById(R.id.description);
        descriptET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                description = s.toString();
            }
        });

        // set up date and time of accident
        timeOfAccidentET = findViewById(R.id.toa);
        timeOfAccidentET.setInputType(InputType.TYPE_NULL);
        timeOfAccidentET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int min = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(SubmitClaim.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time = hourOfDay + ":" + minute;
                        timeOfAccidentET.setText(time);
                    }
                }, hour, min, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        dateOfAccidentET = findViewById(R.id.doa);
        dateOfAccidentET.setInputType(InputType.TYPE_NULL);
        dateOfAccidentET.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(SubmitClaim.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateOfAccidentET.setText(dayOfMonth + "/" +(month + 1) + "/" + year);
                        date = (dayOfMonth + "/" +(month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        // Set up buttons
        takePicBtn = findViewById(R.id.btn_takepicture);
        takePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePictureIntent.resolveActivity(getPackageManager())!=null)
                    startActivityForResult(takePictureIntent, 1);
            }
        });

        submitBtn = findViewById(R.id.submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    location = getLocation();
                    Log.d("HELLLO ITS MEEEEEEEEEE", "onClick: location is " + location + ".");
                    // Update client object
                    claim.setName(name);

                    /*
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference docRef = db.collection("claims").document();
                    docRef.set(claim);
                    */
                    // Start new activity
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);
                }
            }
        });

    }

    public String getLocation(){
        Log.d("YOOOOO", "getLocation: permission is " + checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION));
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Log.d("BROOOOO", "getLocation: in if");
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        return latitude + " X " + longitude;
        }
        return "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK){
            // save image here

        }
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

        // validate time of accident
        else if(time == null || time.equals("")){
            errorAlertBuilder.setMessage("You need to enter a time of accident.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate date of accident
        else if(date == null || date.equals("")){
            errorAlertBuilder.setMessage("You need to enter a date of accident.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate date of description
        else if(description == null || description.equals("")){
            errorAlertBuilder.setMessage("You need to enter a description of accident.");
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
