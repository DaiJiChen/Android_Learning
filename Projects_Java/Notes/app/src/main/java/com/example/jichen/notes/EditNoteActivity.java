package com.example.jichen.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

public class EditNoteActivity extends AppCompatActivity {
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        EditText editText = findViewById(R.id.editText);

        Intent intent = getIntent();
        id = intent.getIntExtra("noteID", -1);

        if(id == -1) {
            MainActivity.notes.add("");
            id = MainActivity.notes.size() - 1;
        }
        else {
            editText.setText(MainActivity.notes.get(id));
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //update notes
                MainActivity.notes.set(id, String.valueOf(charSequence));
                //update listView
                MainActivity.arrayAdapter.notifyDataSetChanged();

                //update preference
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.jichen.notes", MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}