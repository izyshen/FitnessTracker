package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static com.ishen.fitnesstracker.R.id.add_activity;
import static com.ishen.fitnesstracker.R.id.complete_workout;

public class WorkoutActivity extends AppCompatActivity {

    private static final String TAG = "WorkoutActivity";

    int date;
    Button add_btn, done;
    DailyExercises myDB;
    ArrayList<Exercise> exercise_list;
    ListView listview;
    Exercise exercise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        add_btn = (Button) findViewById(add_activity);
        done = (Button) findViewById(complete_workout);
        listview = (ListView) findViewById(R.id.activity_listview);
        myDB = new DailyExercises(this);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        date = (year*10000) + (month*100) + day;
        Log.d(TAG, "onCreate: date value yyyy/mm/dd is " + date);

        // add brings you to add an existing activity
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addActivityIntent = new Intent(WorkoutActivity.this, AddActivity.class);
                startActivity(addActivityIntent);
            }
        });

        // obtain contents of DB
        exercise_list = new ArrayList<>();
        Cursor data = myDB.getListContents();

        // populate list with data from DB
        if ((data.getCount()) == 0) {
            Toast.makeText(WorkoutActivity.this,
                    "Please add your first exercise of the day",
                    Toast.LENGTH_LONG).show();
        } else {
            while(data.moveToNext()) {
                if (Integer.parseInt(data.getString(4)) == date) {
                    exercise = new Exercise(data.getString(1), data.getString(2), data.getString(3));
                    exercise_list.add(exercise);
                }
            }
            three_part_list_adapter adapter = new three_part_list_adapter(
                    this,
                    R.layout.activity_workout_layout,
                    exercise_list);
            listview.setAdapter(adapter);

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    exercise_list.clear();
                    Intent return_main = new Intent(WorkoutActivity.this, Home.class);
                    startActivity(return_main);
                }
            });
        }
    }

// TODO: make added exercises editable/deletable
}

