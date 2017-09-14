package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import static com.ishen.fitnesstracker.R.id.add_activity;

// List view: {views: strings.xml}

public class WorkoutActivity extends AppCompatActivity {

    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        add_btn = (Button) findViewById(add_activity);

        // add brings you to new activity
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addActivityIntent = new Intent(WorkoutActivity.this, AddActivity.class);
                startActivity(addActivityIntent);
            }
        });

        //populateListView();
    }


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

