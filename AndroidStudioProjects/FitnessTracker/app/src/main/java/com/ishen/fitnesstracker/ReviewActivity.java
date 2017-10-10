package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {

    Button edit, delete;
    TextView name, weight, set, rep, time, speed, rest;
    String chosen_name;
    int id, chosen_date;
    SQLiteDbHelper workoutDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        edit = (Button) findViewById(R.id.button_edit);
        delete = (Button) findViewById(R.id.button_add);
        name = (TextView) findViewById(R.id.review_name);
        weight = (TextView) findViewById(R.id.review_weight);
        set = (TextView) findViewById(R.id.review_set);
        rep = (TextView) findViewById(R.id.review_reps);
        time = (TextView) findViewById(R.id.review_time);
        speed = (TextView) findViewById(R.id.review_speed);
        rest = (TextView) findViewById(R.id.review_rest);
        Intent workoutIntent = getIntent();
        id = workoutIntent.getIntExtra("id", -1);
        chosen_name = workoutIntent.getStringExtra("name");
        chosen_date = workoutIntent.getIntExtra("date", -1);

        name.setText(chosen_name);

        final Cursor input_data = workoutDB.getWKTListContents();
        if ((input_data.getCount()) == 0) {
            Toast.makeText(ReviewActivity.this,
                    "No workouts found",
                    Toast.LENGTH_LONG).show();
        } else {
            return;
            /*
            while (input_data.moveToNext()) {
                if ((chosen_name == input_data.getString()) && chosen_date == input_data.getString()) {
                    name.setText(input_data.getString());
                }
                exercise = new ExerciseProperties(activity_data.getString(1), activity_data.getInt(2),
                        activity_data.getInt(3), activity_data.getInt(4), activity_data.getInt(5),
                        activity_data.getInt(6), activity_data.getInt(7));
                listof_exercises.add(num_activities, activity_data.getString(1));
                num_activities++;
                properties.add(exercise);
            }
            Toast.makeText(AddActivity.this,
                    num_activities + "New exercises added to spinner",
                    Toast.LENGTH_LONG).show();
            */
        }


    }
}
