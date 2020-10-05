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
    TextView displacement;
    TextView num_steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        time = (TextView) findViewById(R.id.textView7);
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

    public abstract class StepActivity extends Activity implements SensorEventListener {
        SensorManager sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor stepSensor = sManager.getDefaultSensor(TYPE_STEP_DETECTOR);

        @Override
        protected void onResume() {
            super.onResume();
            sManager.registerListener((SensorListener) this, SensorManager.SENSOR_DELAY_FASTEST);
        }

        @Override
        protected void onStop() {
            super.onStop();
            sManager.unregisterListener(this, stepSensor);
        }



        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Sensor sensor = sensorEvent.sensor;
            float[] values = sensorEvent.values;
            long steps = 0;
            if (sensor.getType() == TYPE_STEP_DETECTOR){
                steps += 1;
                num_steps = (TextView) findViewById(R.id.textView5);
                num_steps.setText(String.valueOf(steps));

            }
        }
        public void getDistanceRun(long steps){
            float distance = (float)(steps*78)/(float)100000;
            displacement = (TextView) findViewById(R.id.textView3);
            displacement.setText(String.valueOf(distance));
        }
    }
    }