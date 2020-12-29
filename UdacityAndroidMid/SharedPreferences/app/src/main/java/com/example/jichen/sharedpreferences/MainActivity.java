package com.example.jichen.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.jichen.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<>();
        friends.add("Fido");
        friends.add("Jone");
        friends.add("Sara");

        sharedPreferences.edit().putString()

//        sharedPreferences.edit().putString("username", "Jichen").apply();
//
//        String username = sharedPreferences.getString("username", "");
//
//        Log.i("Username", username);
    }
}