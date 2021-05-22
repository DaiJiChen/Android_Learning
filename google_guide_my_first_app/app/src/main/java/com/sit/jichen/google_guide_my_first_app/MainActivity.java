package com.sit.jichen.google_guide_my_first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.sit.jichen.google_guide_my_first_app.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // onClick method must have public access and a View as the only parameter, this view is the button you clicked.
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editTextTextPersonName2);
        String message = editText.getText().toString();

        // An intent can carry data types as key-value pair called extra
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}