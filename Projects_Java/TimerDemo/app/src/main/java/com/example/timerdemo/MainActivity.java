package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Handler handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                Log.i("Run info", "running");
//            }
//        };
//
//        handler.post(run);
//        handler.postDelayed(run, 5000);

        CountDownTimer timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                Log.i("Seconds Left", String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                Log.i("finished", "!");
            }
        };

        timer.start();
    }
}