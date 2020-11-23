package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //User can tap a button to change the text color of the label.
        findViewById(R.id.text_color_botton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.text)).setTextColor(getResources().getColor(R.color.yellow));
            }
        });

        //User can tap a button to change the color of the background view
        findViewById(R.id.background_color_botton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (findViewById(R.id.background)).setBackgroundColor(getResources().getColor(R.color.teal_200));
            }
        });

        //User can tap a button to change the text string of the label - Android is Awesome!
        findViewById(R.id.change_text_botton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.text)).setText("Android is Awesome!");
            }
        });

        //User can tap on the background view to reset all views to default settings.
        findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                //1. reset text to - Hello from Jichen!
                ((TextView) findViewById(R.id.text)).setText("Hello from Jichen!");

                //2. reset background color
                //origin color is R.color.light_green
                (findViewById(R.id.background)).setBackgroundColor(getResources().getColor(R.color.light_green));

                //3. reset text color
                // origin color is R.color.black
                ((TextView) findViewById(R.id.text)).setTextColor(getResources().getColor(R.color.black));
            }
        });

        //User can update the label text with custom text entered into the text field.
        //a. User can enter text into a text field using the keyboard
        findViewById(R.id.change_custom_text_botton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEnteredText = ((EditText)findViewById(R.id.editText)).getText().toString();

                //b. User can tap the “Change text string” button to update the label with the text from the text field.
                //c. If the text field is empty, update label with default text string.
                if(userEnteredText.isEmpty())
                    ((TextView) findViewById(R.id.text)).setText("Enter your own text");
                else
                    ((TextView) findViewById(R.id.text)).setText(userEnteredText);
            }
        });

    }
}