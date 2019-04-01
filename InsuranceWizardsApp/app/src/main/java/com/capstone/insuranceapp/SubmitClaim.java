package com.capstone.insuranceapp;

import android.content.Intent;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SubmitClaim extends AppCompatActivity {

    private Button takePicBtn;
    private ImageView imageView;
    private TextView textView;
    private CameraDevice cameraDevice;
    private CameraCaptureSession cameraCaptureSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_claim);

        takePicBtn = findViewById(R.id.btn_takepicture);
        takePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePictureIntent.resolveActivity(getPackageManager())!=null)
                    startActivityForResult(takePictureIntent, 1);
                Toast.makeText(SubmitClaim.this, "Picture saved", Toast.LENGTH_SHORT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK){
            // save image here
            Log.d("returned", "hello darkness");
            textView.setText("Picture saved!");
            Toast.makeText(SubmitClaim.this, "Picture saved", Toast.LENGTH_SHORT);

        }
    }




}
