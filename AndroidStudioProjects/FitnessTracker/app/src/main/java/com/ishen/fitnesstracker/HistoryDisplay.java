package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class HistoryDisplay extends AppCompatActivity {

    DailyExercises prev_exercise_DB;
    ArrayList<Exercise> exercise_list;
    ListView listview;
    Exercise exercise;
    String chosen_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_display);

        listview = (ListView) findViewById(R.id.history_listview);

        Intent incoming_intent = getIntent();
        chosen_date = incoming_intent.getStringExtra("chosen_date");

        exercise_list = new ArrayList<>();

        Cursor data = prev_exercise_DB.getListContents();

        // populate exercise_list with data from DB
        if ((data.getCount()) == 0) {
            Toast.makeText(HistoryDisplay.this,
                    "data count == 0",
                    Toast.LENGTH_LONG).show();
        } else {
            while(data.moveToNext()) {
                if (data.getString(4).equals(chosen_date)) {
                    exercise = new Exercise(data.getString(1), data.getString(2), data.getString(3));
                    exercise_list.add(exercise);
                }
            }

            three_part_list_adapter adapter = new three_part_list_adapter(
                    this,
                    R.layout.activity_workout_layout,
                    exercise_list);
            listview.setAdapter(adapter);
        }

    }
}
