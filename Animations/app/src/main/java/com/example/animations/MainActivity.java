package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private boolean image1_isShowing = true;

    public void fade(View view) {
        Log.i("Info", "ImageView has been tapped");

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView);
//        imageView1.animate().alpha(0).setDuration(1500);
//        imageView1.animate().alpha(1).setDuration(1500);
//
//        imageView1.animate().translationXBy(-1000).setDuration(1500);
//        imageView1.animate().translationXBy(1000).setDuration(1500);

        imageView1.animate().rotation(1800).scaleX(0.5f).scaleY(0.5f).alpha(0).setDuration(3000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}