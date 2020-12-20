package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Button button;
    CountDownTimer timer;
    boolean timing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTime(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timing) {
                    textView.setText("00:30");
                    seekBar.setProgress(30);
                    seekBar.setEnabled(true);
                    timer.cancel();
                    button.setText("GO");
                    timing = false;
                }
                else {
                    timing = true;
                    seekBar.setEnabled(false);
                    button.setText("STOP");
                    timer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                        @Override
                        public void onTick(long l) {
                            updateTime((int)(l / 1000));
                        }

                        @Override
                        public void onFinish() {
                            Log.i("Finished", "Timer all done");
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.air_horn);
                            mediaPlayer.start();
                        }
                    }.start();
                }
            }
        });

    }

    private void updateTime(int secondsLeft) {
        String secString = String.valueOf(secondsLeft % 60);
        if((secondsLeft % 60 < 10)) secString = "0" + secString;

        String minString = String.valueOf(secondsLeft / 60);
        if((secondsLeft / 60 < 10)) minString = "0" + minString;

        textView.setText(minString + ":" + secString);
        Log.i("Remaining time changed", "Curr time is: " + minString + ":" + secString);

    }
}