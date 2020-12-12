package com.example.currencyconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void onClick(View view) {
        Log.i("info", "button pressed");
        String input = ((EditText)findViewById(R.id.editAmount)).getText().toString();
        int amount = Integer.valueOf(input);
        int dollarAmount = amount*7;
        Toast.makeText(this, dollarAmount+"", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}