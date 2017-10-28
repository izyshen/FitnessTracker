package com.ishen.fitnesstracker;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.HashSet;

public class AltCalendarView extends AppCompatActivity {

    SQLiteDbHelper WorkoutDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_calendar_view);

        HashSet<Calendar> events = new HashSet<>();
        WorkoutDB = new SQLiteDbHelper(this);

        Cursor cursor = WorkoutDB.getHISTListContents();
        int db_date_str = 8;
        while (cursor.moveToNext()) {
            //events.add(cursor.getString(db_date_str));
        }
    }
}
