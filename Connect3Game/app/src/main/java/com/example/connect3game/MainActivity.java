package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private boolean redTurn = true;

    public void click(View view) {
        ImageView image = (ImageView) view;

        if(redTurn) {
            Log.i("Info", image.getTag()+" pressed "+"set to red");

            image.setImageResource(R.drawable.red);
            image.setTranslationY(-1500);
            image.animate().alpha(255);
            image.animate().translationYBy(1500).rotation(3600).setDuration(300);

            redTurn = false;
        }
        else {
            Log.i("Info", image.getTag()+" pressed "+"set to yellow");

            image.setImageResource(R.drawable.yellow);
            image.setTranslationY(-1500);
            image.animate().alpha(255);
            image.animate().translationYBy(1500).rotation(3600).setDuration(300);

            redTurn = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}