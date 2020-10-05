package com.google.fitnessinlockdown;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import static android.hardware.Sensor.TYPE_STEP_DETECTOR;


public class StepCounter {
    public abstract class StepActivity extends Activity implements SensorEventListener {
        SensorManager sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor stepSensor = sManager.getDefaultSensor(TYPE_STEP_DETECTOR);

        TextView displacement;
        TextView num_steps;
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
            long steps = 0;
            if (sensor.getType() == TYPE_STEP_DETECTOR){
                steps += 1;
                num_steps = findViewById(R.id.textView5);
                num_steps.setText(String.valueOf(steps));

            }
        }
        public void getDistanceRun(long steps){
            float distance = (float)(steps*78)/(float)100000;
            displacement = findViewById(R.id.textView3);
            displacement.setText(String.valueOf(distance));
        }
    }
}
