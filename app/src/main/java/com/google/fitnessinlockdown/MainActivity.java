package com.google.fitnessinlockdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorListener;
import android.os.CountDownTimer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.app.Activity;

import static android.hardware.Sensor.*;

public class MainActivity<sensorManager, sensor> extends AppCompatActivity {

    public int counter;
    Button start;
    TextView time;
    Button stop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        time = findViewById(R.id.textView7);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(600000,1000){
                    public void onTick(long millisUntilFinished){
                        time.setText(String.valueOf(counter));
                        counter++;
                    }

                    public void onFinish(){
                        time.setText("FINISH");
                    }

                }.start();

            }
        });
    }
//    private SensorManager sensorManager;
//    private Sensor sensor;
//    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//    sensor = sensorManager.getDefaultSensor(TYPE_STEP_COUNTER);


    }