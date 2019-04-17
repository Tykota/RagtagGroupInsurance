package com.capstone.insuranceapp;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

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
        // Get current activity
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(1);
        String className = tasks.get(0).topActivity.getClassName();

        if(className.equals("com.capstone.insuranceapp.MonitorDrive")){
            Intent intent = new Intent(getApplicationContext(), VerifyAccident.class);
            startActivity(intent);
        }
    }

    public void onCreate(){
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(1);
        movementListener = new MovementListener(this);
        movementListener.setOnMovementListenter(this);
    }

    public void onDestroy(){
        super.onDestroy();
    }
}
