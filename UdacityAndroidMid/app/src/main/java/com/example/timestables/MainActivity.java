package com.example.timestables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int ARRAY_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        ArrayList<Integer> array = new ArrayList<>();
        for(int i = 0; i < ARRAY_SIZE; i++)
            array.add(i+1);

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, array);

        listView.setAdapter(arrayAdapter);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(20);
        seekBar.setProgress(1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("SeekBar changed", "ratio = " + i);

                if(i < 1) i = 1;

                for(int index = 0; index < ARRAY_SIZE; index++)
                    array.set(index, (index+1) * i);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


    }
}