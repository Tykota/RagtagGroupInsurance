package com.capstone.insuranceapp;

import android.content.Context;
import android.hardware.SensorListener;
import android.hardware.SensorManager;

@SuppressWarnings("deprecation")
public class MovementListener implements SensorListener {
    private static final int FORCE_THRESHOLD = 1;
    private static final int TIME_THRESHOLD = 50;
    private static final int SHAKE_TIMEOUT = 50;
    private static final int SHAKE_DURATION = 1;
    private static final int SHAKE_COUNT = 1;

    private SensorManager sensorManager;
    private float lastX = -1, lastY = -1, lastZ = -1;
    private long lastTime;
    private OnMovementListener onMovementListener;
    private Context context;
    private int moveCount = 0;
    private long lastMovement, lastForce;

    public interface OnMovementListener{
        public void onMove();
    }

    public MovementListener(Context context){
        this.context = context;

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager == null){
            throw new UnsupportedOperationException("Sensors not supported on device");
        }
        if(!sensorManager.registerListener(this, SensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME)){
            sensorManager.unregisterListener(this, SensorManager.SENSOR_DELAY_FASTEST);
            throw new UnsupportedOperationException("Accelerometer not supported on device");
        }
    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if(sensor != SensorManager.SENSOR_ACCELEROMETER)
            return;

        long now = System.currentTimeMillis();

        if((now - lastForce) > SHAKE_TIMEOUT){
            moveCount = 0;
        }

        if((now - lastTime) > TIME_THRESHOLD){
            long difference = now - lastTime;
            float speed = values[SensorManager.DATA_X] + values[SensorManager.DATA_Y] + values[SensorManager.DATA_Z] - lastX - lastY - lastZ;
            speed = Math.abs(speed) / difference * 10000;

            if(speed > FORCE_THRESHOLD){
                if((++moveCount >= SHAKE_COUNT) && (now - lastMovement > SHAKE_DURATION)){
                    lastMovement = now;
                    moveCount = 0;
                    if(onMovementListener != null){
                        onMovementListener.onMove();
                    }
                }
                lastForce = now;
            }

            lastTime = now;
            lastX = values[SensorManager.DATA_X];
            lastY = values[SensorManager.DATA_Y];
            lastZ = values[SensorManager.DATA_Z];
        }

    }

    public void setOnMovementListenter(OnMovementListener listener){
        onMovementListener = listener;
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }
}
