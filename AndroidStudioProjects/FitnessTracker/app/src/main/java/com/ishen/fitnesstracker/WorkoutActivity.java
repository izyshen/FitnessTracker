package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// List view: {views: listof_activities.xml}

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        populateListView();
    }

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
    }
}
