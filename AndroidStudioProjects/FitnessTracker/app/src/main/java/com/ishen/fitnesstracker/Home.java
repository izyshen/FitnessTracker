package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    Button bt_today, bt_history, bt_workoutplan, bt_settings;
    SQLiteDbHelper workoutDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bt_today = (Button) findViewById(R.id.button);
        bt_history = (Button) findViewById(R.id.button2);
        bt_workoutplan = (Button) findViewById(R.id.button3);
        bt_settings = (Button) findViewById(R.id.button4);
        workoutDB = new SQLiteDbHelper(this);

        // takes user to workout activity
        bt_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Cursor cursor = workoutDB.getWKTListContents();
                if (cursor.getCount() == 0) {
                    Intent workoutIntent = new Intent(Home.this, StartWorkout.class);
                    startActivity(workoutIntent);
                }
                else {
                    Intent existing_workoutIntent = new Intent(Home.this, WorkoutActivity.class);
                    startActivity(existing_workoutIntent);
                }
            }
        });

        // allows user to review workouts from previous days
        bt_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent historyIntent = new Intent(Home.this, HistoryCalendarView.class);
                startActivity(historyIntent);
            }
        });

        bt_workoutplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent workoutIntent = new Intent(Home.this, AltCalendarView.class);
                startActivity(workoutIntent);
            }
        });
    }
}
