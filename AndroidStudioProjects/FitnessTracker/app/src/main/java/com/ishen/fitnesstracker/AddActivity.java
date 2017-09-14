package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button bt_add;

    private Spinner sp_type;

    // to choose existing exercise
    private static final String[]paths = {"item 1", "item 2", "item 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        bt_add = (Button) findViewById(R.id.button_add);

        // adds an exercise to workout activity
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addActivityIntent = new Intent(AddActivity.this, WorkoutActivity.class);
                startActivity(addActivityIntent);
            }
        });

        // adapter for spinner of different exercise types
        sp_type = (Spinner) findViewById(R.id.exercise_type_sp);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(
                AddActivity.this,
                android.R.layout.simple_spinner_item,
                paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(adapter);
        sp_type.setOnItemSelectedListener(this);

    }
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch(position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
