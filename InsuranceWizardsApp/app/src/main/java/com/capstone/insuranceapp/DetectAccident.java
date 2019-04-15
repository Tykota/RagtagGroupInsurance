package com.capstone.insuranceapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class DetectAccident extends Service implements MovementListener.OnMovementListener {

    private MovementListener movementListener;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onMove() {
        // Accident detected
        Intent intent = new Intent(getApplicationContext(), VerifyAccident.class);
        startActivity(intent);
    }

    public void onCreate(){
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(1);
        movementListener = new MovementListener(this);
        movementListener.setOnMovementListenter(this);

    }
}
