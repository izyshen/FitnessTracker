package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    Button bt_workout, bt_history, bt_button, bt_settings;
    SQLiteDbHelper workoutDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bt_workout = (Button) findViewById(R.id.button);
        bt_history = (Button) findViewById(R.id.button2);
        bt_button = (Button) findViewById(R.id.button3);
        bt_settings = (Button) findViewById(R.id.button4);
        workoutDB = new SQLiteDbHelper(this);

        // takes user to workout activity
        bt_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Cursor cursor = workoutDB.getWKTListContents();
                if (cursor.getCount() == 0) {
                    Intent workoutIntent = new Intent(Home.this, StartWorkout.class);
                    startActivity(workoutIntent);
                }
                //TODO: if no workout activities for the day, do this
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
    }
}
