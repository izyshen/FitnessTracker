package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.ishen.fitnesstracker.R.id.add_activity;

public class WorkoutActivity extends AppCompatActivity {

    Button add_btn;
    //ActivityDatabase myDB;
    ActivityDatabase2 myDB2;
    ArrayList<Exercise> exercise_list;
    ListView listview;
    Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        add_btn = (Button) findViewById(add_activity);
        listview = (ListView) findViewById(R.id.activity_listview);
        //myDB = new ActivityDatabase(this);
        myDB2 = new ActivityDatabase2(this);

        // add brings you to add an existing activity
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addActivityIntent = new Intent(WorkoutActivity.this, AddActivity.class);
                startActivity(addActivityIntent);
            }
        });

        // obtain contents of DB
        //ArrayList<String> activity_list = new ArrayList<>();
        //Cursor data = myDB.getListContents();
        exercise_list = new ArrayList<>();
        Cursor data = myDB2.getListContents();

        // populate list with data from DB
        if ((data.getCount()) == 0) {
            Toast.makeText(WorkoutActivity.this,
                    "Please add your first exercise of the day",
                    Toast.LENGTH_LONG).show();
        } else {
            /*while(data.moveToNext()) {
                activity_list.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        activity_list);
                list_view.setAdapter(listAdapter);
            }*/
            while(data.moveToNext()) {
                exercise = new Exercise(data.getString(1), data.getString(2), data.getString(3));
                exercise_list.add(exercise);
            }
            three_part_list_adapter adapter = new three_part_list_adapter(
                    this,
                    R.layout.activity_workout_layout,
                    exercise_list);
            listview.setAdapter(adapter);
        }
    }
// TODO: make added exercises editable
    // TODO: change listadapter to show multiple columns
}

