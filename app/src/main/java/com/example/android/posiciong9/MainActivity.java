package com.example.android.posiciong9;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensorAccelerometer;
    Sensor sensorGravity_;
    Sensor sensorMagneticFiel;
    SensorEventListener sensorEventListener;

    private TextView position;
    private String txtposition="";
    int whip=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // sensores
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorGravity_ = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorMagneticFiel = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        position = (TextView) findViewById(R.id.posicion);
/*
        if(sensorMagneticFiel ==null){
            System.out.println(" Su dispositivo no cuenta con el sensor CAMPO MAGNÉTICO");
            finish();
        }
        if(sensorGravity ==null) {
            System.out.println(" Su dispositivo no cuenta con el sensor GRAVITY");
            finish();
        }
        if(sensorGravity ==null) {
            System.out.println(" Su dispositivo no cuenta con el sensor GRAVITY");
            finish();
        }

  */     if(sensorAccelerometer ==null) {
            System.out.println(" Su dispositivo no cuenta con el sensor ACCELEROMETER");
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                double li = 5;
                double lj = 10;

                float x=sensorEvent.values[0];
                float y=sensorEvent.values[1];
                float z=sensorEvent.values[2];
                System.out.println(" position X: "+ x + " Y: "+ y +" Z: "+ z);
                //position.setText(" position X: "+ x + " Y: "+ y +" Z: "+ z);
                if(x>=0 && x<=li && y >0 && y <=lj ){ //&& whip==0
                    //whip++;
                    position.setText("VERTICAL 1");
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }else if(x>=-li && x<=0 && y >-lj && y <=0 ){
                    //whip++;
                    position.setText("VERTICAL 2");
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }else if(x>=-lj && x<=0 && y >0 && y <=li ){
                    //whip++;
                    position.setText("HORIZONTAL 1");
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }else if(x>=0 && x<=lj && y >-li && y <=0 ){
                    //whip++;
                    position.setText("HORIZONTAL 2");
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
                /*
                else{
                    position.setText(" NI VERTICAL NI HORIZONTAL");
                    getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                }*/
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    private void start(){
        sensorManager.registerListener(sensorEventListener, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }
    protected void onPause(){
        stop();
        super.onPause();
    }
    protected void onResume(){
        start();
        super.onResume();
    }
}