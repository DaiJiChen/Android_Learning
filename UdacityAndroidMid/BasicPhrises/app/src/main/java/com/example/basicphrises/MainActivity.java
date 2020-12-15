package com.example.basicphrises;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//    public void playPhrase(View view) {
//        Button button = (Button) view;
//
//        Log.i("Button Pressed", button.getTag().toString());
//        String nameOfFile = button.getTag().toString();
//        //MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(nameOfFile, "raw", getPackageName()));
//        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(button.getTag().toString(), "raw", getPackageName()));
//        mediaPlayer.start();
//    }

    public void playPhrase(View view) {

        Button buttonPressed = (Button) view;

        Log.i("Button pressed", buttonPressed.getTag().toString());

        int resourceID = this.getResources().getIdentifier(buttonPressed.getTag().toString(), "raw", this.getPackageName());
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.hello);

        mediaPlayer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}