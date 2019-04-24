package com.capstone.insuranceapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

public class SubmitClaim extends AppCompatActivity {

    private Button takePicBtn, submitBtn;
    private Claim claim;
    private EditText nameET, descriptET, dateOfAccidentET, timeOfAccidentET;
    private String description, date, time, name, location, currentPhotoPath;
    private DatePickerDialog datePicker;
    private AlertDialog.Builder errorAlertBuilder;
    private AlertDialog errorAlert;
    private File photoFile = null;
    private boolean imageSaved = false;
    private Bitmap pic;


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
                }, hour, min, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        dateOfAccidentET = findViewById(R.id.doa);
        dateOfAccidentET.setInputType(InputType.TYPE_NULL);
        dateOfAccidentET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(SubmitClaim.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateOfAccidentET.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        date = (dayOfMonth + "/" + (month + 1) + "/" + year);
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
                takePhoto();
            }
        });

        submitBtn = findViewById(R.id.submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    location = getLocation();
                    // Update client object
                    claim.setName(name);
                    claim.setClaimStatus("submitted");
                    claim.setDate(date + " at " + time);
                    claim.setLocation(location);
                    claim.setDescription(description);

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference docRef = db.collection("claims").document();
                    docRef.set(claim);

                    // Add photo to storage
                    if(imageSaved){
                        uploadPhoto();
                    }

                    // Start new activity
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void uploadPhoto(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        String imageFileName = "claim";
        
        if (claim.getClaimNumber() != null) {
            imageFileName = imageFileName + claim.getClaimNumber() + ".jpg";
        } else {
            imageFileName = imageFileName + "Unknown.jpg";
        }

        StorageReference imageRef = storageReference.child(imageFileName);

        // This should add custom metadata to the imageRef 
        /*
        StorageMetadata metadata = new StorageMetadata.Builder().setCustomMetadata('claimNo', claim.getClaimNumber()).build();
        imageRef.updateMetadata(metadata).addOnSuccessListener(new addOnSuccessListener<StorageMetadata>(){
            @Override
            public void onSuccess(SotrageMetadata StorageMetadata){
                // Metadata successfully added
            }
        })
        .addOnFailureListener(new OnFailerListener() {
            @Override
            public void onFailure(@NonNull Exception exception){
                // error occured
            }
        })
        */

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pic.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        imageRef.putBytes(data);
    }

    public void takePhoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // error taking picture
            }

            if(photoFile != null){
                startActivityForResult(takePictureIntent, 1);
            }
        }
    }

    public File createImageFile() throws IOException {
        String imageFileName = "claim";
        if (claim.getClaimNumber() != null) {
            imageFileName = imageFileName + claim.getClaimNumber();
        } else {
            imageFileName = imageFileName + "Unknown";
        }

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public String getLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            DecimalFormat decimalFormat = new DecimalFormat("##0.000000");
            return decimalFormat.format(latitude) + " and " + decimalFormat.format(longitude);
        }
        return "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            imageSaved = true;
            Bundle extras = data.getExtras();
            pic = (Bitmap) extras.get("data");
        }
    }


    public boolean validateInput() {
        // validate name
        if (name == null || name.equals("")) {
            errorAlertBuilder.setMessage("You need to enter a name.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate time of accident
        else if (time == null || time.equals("")) {
            errorAlertBuilder.setMessage("You need to enter a time of accident.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate date of accident
        else if (date == null || date.equals("")) {
            errorAlertBuilder.setMessage("You need to enter a date of accident.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        // validate date of description
        else if (description == null || description.equals("")) {
            errorAlertBuilder.setMessage("You need to enter a description of accident.");
            errorAlert = errorAlertBuilder.create();
            errorAlert.show();
            resetErrorDialog();
            return false;
        }

        return true;
    }

    public void resetErrorDialog() {
        errorAlert = null;
        errorAlertBuilder = null;
        errorAlertBuilder = new AlertDialog.Builder(this);
        errorAlertBuilder.setTitle("Invalid Input");
        errorAlertBuilder.setPositiveButton("Okay", null);
    }

}
