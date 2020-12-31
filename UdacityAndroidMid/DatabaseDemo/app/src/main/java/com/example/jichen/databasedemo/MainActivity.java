package com.example.jichen.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            // create table and insert
//        database.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            database.execSQL("CREATE TABLE IF NOT EXISTS newusers (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");
            database.execSQL("INSERT INTO newusers (name, age) VALUES ('Xinyu', 24)");
            database.execSQL("INSERT INTO newusers (name, age) VALUES ('Jichen', 25)");

            //delete
//        database.execSQL("DELETE FROM newusers WHERE id = 2");


            // query
            Cursor c = database.rawQuery("SELECT * FROM newusers", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();
            while(!c.isAfterLast()) {
                Log.i("name", c.getString(nameIndex));
                Log.i("age", c.getString(ageIndex));
                Log.i("id", c.getString(idIndex));
                c.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}