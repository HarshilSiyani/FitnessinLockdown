package com.google.fitnessinlockdown;

import androidx.appcompat.app.AppCompatActivity;
import android.os.CountDownTimer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int counter;
    Button start;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
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

}