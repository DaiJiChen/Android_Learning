package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        button = findViewById(R.id.buttonSave);
        editText = findViewById(R.id.editItem);

        getSupportActionBar().setTitle("Edit Item");

        editText.setText(getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an intent which will contain the result
                Intent intent = new Intent();
                //pass the data
                intent.putExtra(MainActivity.KEY_ITEM_TEXT, editText.getText().toString());
                intent.putExtra(MainActivity.KEY_ITEM_POSITION, getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POSITION));
                // set result for intent
                setResult(RESULT_OK, intent);
                // finish this activity and go back
                finish();
            }
        });
    }
}