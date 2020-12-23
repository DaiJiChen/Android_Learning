package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView time;
    TextView question;
    TextView score;
    TextView answer;

    List<Integer> potentialAnswers = new ArrayList<>();



    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start);
        time = findViewById(R.id.time);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        answer = findViewById(R.id.answer);

        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        question.setText(a + " + " + b);

        int locationOfCorrectAnswer = random.nextInt(4);

        for(int i = 0; i < 4; i++) {
            if(i == locationOfCorrectAnswer) {
                potentialAnswers.add(a+b);
            }
            else {
                int wrongAnswer = random.nextInt(41);
                while(wrongAnswer == a+b || potentialAnswers.contains(wrongAnswer)) {
                    wrongAnswer = random.nextInt(41);
                }
                potentialAnswers.add(wrongAnswer);
            }
        }

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button0.setText(potentialAnswers.get(0) + "");
        button1.setText(potentialAnswers.get(1) + "");
        button2.setText(potentialAnswers.get(2) + "");
        button3.setText(potentialAnswers.get(3) + "");

    }
}