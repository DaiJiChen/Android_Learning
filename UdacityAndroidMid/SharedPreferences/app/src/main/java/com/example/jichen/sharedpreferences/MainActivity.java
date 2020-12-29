package com.example.jichen.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.jichen.sharedpreferences", Context.MODE_PRIVATE);

        // 1. put array
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Fido");
        friends.add("Jone");
        friends.add("Sara");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
            Log.i("Serialized friends", ObjectSerializer.serialize(friends));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newFriends = new ArrayList<>();
        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("new Friends", newFriends.toString());


        // 2. put string
        sharedPreferences.edit().putString("username", "Jichen").apply();

        String username = sharedPreferences.getString("username", "");

        Log.i("Username", username);
    }
}