package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.ishen.fitnesstracker.R.id.add_activity;

public class WorkoutActivity extends AppCompatActivity {

    private static final String TAG = "WorkoutActivity";

    Button add_btn;
    DailyExercises myDB;
    ArrayList<Exercise> exercise_list;
    ListView listview;
    Exercise exercise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        add_btn = (Button) findViewById(add_activity);
        listview = (ListView) findViewById(R.id.activity_listview);
        myDB = new DailyExercises(this);

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
                exercise = new Exercise(data.getString(1), data.getString(2), data.getString(3));
                exercise_list.add(exercise);
            }
            three_part_list_adapter adapter = new three_part_list_adapter(
                    this,
                    R.layout.activity_workout_layout,
                    exercise_list);
            listview = (ListView) findViewById(R.id.activity_listview);
            listview.setAdapter(adapter);
        }
    }
// TODO: make added exercises editable/deletable
}

