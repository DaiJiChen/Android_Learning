package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout gameLayout;

    Button startButton;
    TextView time;
    TextView question;
    TextView score;
    TextView result;
    Button playAgain;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    List<Integer> potentialAnswers = new ArrayList<>();
    int locationOfCorrectAnswer;

    int numCorrect = 0;
    int numWrong = 0;

    public void chooseAnswer(View view) {
        if (locationOfCorrectAnswer == Integer.valueOf((view.getTag().toString()))) {
            Log.i("Correct!", "");
            result.setText("Correct!");
            numCorrect++;
            score.setText(numCorrect + "/" + (numWrong+numCorrect));
        }
        else {
            Log.i("Wrong:(", "");
            result.setText("Wrong!");
            numWrong++;
            score.setText(numCorrect + "/" + (numWrong+numCorrect));
        }
        resetQuestion();
    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view) {
        numCorrect = 0;
        numWrong = 0;
        time.setText("30s");
        score.setText(numCorrect + "/" + (numWrong+numCorrect));
        playAgain.setVisibility(View.INVISIBLE);

        resetQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                time.setText((l/1000) + "s");
            }

            @Override
            public void onFinish() {
                result.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);

        startButton = findViewById(R.id.start);
        time = findViewById(R.id.time);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        result = findViewById(R.id.result);
        playAgain = findViewById(R.id.playAgain);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

        playAgain(findViewById(R.id.playAgain));
    }

    public void resetQuestion() {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        question.setText(a + " + " + b);

        locationOfCorrectAnswer = random.nextInt(4);

        potentialAnswers.clear();
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

        button0.setText(Integer.toString(potentialAnswers.get(0)));
        button1.setText(Integer.toString(potentialAnswers.get(1)));
        button2.setText(Integer.toString(potentialAnswers.get(2)));
        button3.setText(Integer.toString(potentialAnswers.get(3)));
    }
}