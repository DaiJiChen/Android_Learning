package com.example.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void play(View view) {
        mediaPlayer.start();
    }

    public void pause(View view) {
            mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.airplane_landing);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolumn = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeSeekBar = (SeekBar) findViewById(R.id.volumeSeekBar);
        volumeSeekBar.setMax(maxVolume);
        volumeSeekBar.setProgress(currentVolumn);

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("SeekBar changed", Integer.toString(i));
                audioManager.setStreamVolume(audioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        SeekBar scrubSeekBar = (SeekBar) findViewById(R.id.scrubSeekBar);
        scrubSeekBar.setMax(mediaPlayer.getDuration());
        scrubSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("scrubSeekBar changed", Integer.toString(i));
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);

    }
}