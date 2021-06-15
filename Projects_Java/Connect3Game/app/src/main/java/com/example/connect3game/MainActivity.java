package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean redTurn = true;
    boolean gameActive = true;

    //red:1, yellow: -1, empty: 0
    private int[] gameState = {0,0,0,0,0,0,0,0,0};

    public void click(View view) {
        if(!gameActive) return;

        ImageView image = (ImageView) view;
        int tag = Integer.valueOf(image.getTag().toString());

        if(gameState[tag-1] != 0) return;

        gameState[tag-1] = redTurn ? 1:-1;
        Log.i("Info", gameState.toString());

        if(redTurn) {
            Log.i("Info", image.getTag()+" pressed "+"set to red");

            image.setImageResource(R.drawable.red);
            image.setTranslationY(-1500);
            image.animate().alpha(255);
            image.animate().translationYBy(1500).rotation(3600).setDuration(300);

            redTurn = false;
        }
        else {
            Log.i("Info", image.getTag()+" pressed "+"set to yellow");

            image.setImageResource(R.drawable.yellow);
            image.setTranslationY(-1500);
            image.animate().alpha(255);
            image.animate().translationYBy(1500).rotation(3600).setDuration(300);

            redTurn = true;
        }

        int result = calResult();
        Log.i("info", "result: "+result);
        if(result == 1) {
            Toast.makeText(this, "Red has won", Toast.LENGTH_LONG).show();
            gameActive = false;
        }
        else if(result == -1) {
            Toast.makeText(this, "Yellow has won", Toast.LENGTH_LONG).show();
            gameActive = false;
        }

    }

    //return 1: red win, -1, yellow win, 0 no one win
    private int calResult() {
        int[][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        boolean redWin = false;
        boolean yellowWin = false;
        for(int[] winPos : winningPosition) {
            if(gameState[winPos[0]] == 1 && gameState[winPos[1]] == 1 && gameState[winPos[2]] == 1)
                redWin = true;
            else if(gameState[winPos[0]] == -1 && gameState[winPos[1]] == -1 && gameState[winPos[2]] == -1)
                yellowWin = true;
        }

        if(redWin && !yellowWin) return 1;
        else if(!redWin && yellowWin) return -1;
        else return 0;
    }

    public void playAgain(View viwe) {
        LinearLayout lines = findViewById(R.id.board);
        for(int i = 0; i < lines.getChildCount(); i++) {
            LinearLayout line = (LinearLayout) lines.getChildAt(i);
            for (int j = 0; j < line.getChildCount(); j++) {
                ImageView image = (ImageView) line.getChildAt(j);
                redTurn = true;
                gameActive = true;
                gameState = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
                image.animate().alpha(0);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}