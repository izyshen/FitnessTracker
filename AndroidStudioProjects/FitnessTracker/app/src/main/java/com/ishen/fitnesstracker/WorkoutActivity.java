package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.ishen.fitnesstracker.R.id.add_activity;

// List view: {views: strings.xml}

public class WorkoutActivity extends AppCompatActivity {

    Button add_btn;
    ActivityDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        add_btn = (Button) findViewById(add_activity);
        ListView list_view = (ListView) findViewById(R.id.activity_listview);
        myDB = new ActivityDatabase(this);

        // add brings you to add an existing activity
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addActivityIntent = new Intent(WorkoutActivity.this, AddActivity.class);
                startActivity(addActivityIntent);
            }
        });

        // obtain contents of DB
        ArrayList<String> activity_list = new ArrayList<>();
        Cursor data = myDB.getListContents();

        // populate list with data from DB
        if (data.getCount() == 0) {
            Toast.makeText(WorkoutActivity.this,
                    "Please add your first exercise of the day",
                    Toast.LENGTH_LONG).show();
        } else {
            while(data.moveToNext()) {
                activity_list.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        activity_list);
                list_view.setAdapter(listAdapter);
            }
        }
    }
// TODO: get adding activities to work :(  *****
    // TODO: change listadapter to show multiple columns

/*

    // produces a list of exercises done by the user that day
    private void populateListView() {
        // Create listof Activities
        String[] myItems = {"Blue", "Green"};

        // build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                       // context for activity
                R.layout.listof_activities, // Layout to use(create)
                myItems);                   // Items to be displayed

        // config listview
        ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setAdapter(adapter);
        */
}

