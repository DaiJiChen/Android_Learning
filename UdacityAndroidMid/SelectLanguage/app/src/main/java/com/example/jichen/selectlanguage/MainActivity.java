package com.example.jichen.selectlanguage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SharedPreferences sharedPreferences;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.language_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.English:
                setLanguage("English");
                return true;
            case R.id.Chinese:
                setLanguage("Chinese");
                return true;
            default:
                setLanguage("Unknown language");
                return false;

        }
    }

    public void setLanguage(String language) {
        sharedPreferences.edit().putString("language", language).apply();

        if(language.equals("English"))
            textView.setText(R.string.English_Welcome);
        else if(language.equals("Chinese"))
            textView.setText(R.string.Chinese_Welcome);
        else
            textView.setText("unknown language");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.example.jichen.selectlanguage", Context.MODE_PRIVATE);

        String language = sharedPreferences.getString("language", "");

        if(language.equals("")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.sym_def_app_icon)
                    .setTitle("Language Selection")
                    .setMessage("Which language do you prefer")
                    .setPositiveButton(R.string.English, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("Emglish");
                        }
                    })
                    .setNegativeButton(R.string.Chinese, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("Chinese");
                        }
                    })
                    .show();
        }
        else {
            setLanguage(language);
        }

    }
}