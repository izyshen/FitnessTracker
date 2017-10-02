package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.attr.name;

public class NewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityDatabase centralActivityDB;
    Button done;
    private Spinner muscle_spinner;
    CheckBox weight_box, set_box, rep_box, speed_box, time_box, rest_box;
    int weight, set, rep, speed, time, rest;
    EditText edit_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        muscle_spinner = (Spinner) findViewById(R.id.muscle_spinner);
        done = (Button) findViewById(R.id.done_create_activity);
        edit_name = (EditText) findViewById(R.id.exercise_name);
        weight_box = (CheckBox) findViewById(R.id.weight_checkbox);
        set_box = (CheckBox) findViewById(R.id.set_checkbox);
        rep_box  = (CheckBox) findViewById(R.id.rep_checkbox);
        speed_box = (CheckBox) findViewById(R.id.speed_checkbox);
        time_box = (CheckBox) findViewById(R.id.time_checkbox);
        rest_box = (CheckBox) findViewById(R.id.rest_checkbox);
        centralActivityDB = new ActivityDatabase(this);

        // keeps track of what new activity's fields are (0 = box unchecked, 1 = box checked)
        weight = set = rep = speed = time = rest = 0;


        // spinner to track muscle group
        muscle_spinner.setOnItemSelectedListener(this);

        // array adapter with string array and spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.muscle_type_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        muscle_spinner.setAdapter(adapter);


        // complete adding new activity, return to workout activity
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // keeps track of the parameters the user wants for each activity
                if (weight_box.isChecked()) {
                    weight=1;
                }
                if (set_box.isChecked()) {
                    set=1;
                }
                if (rep_box.isChecked()) {
                    rep=1;
                }
                if (speed_box.isChecked()) {
                    speed=1;
                }
                if (time_box.isChecked()) {
                    time = 1;
                }
                if (rest_box.isChecked()) {
                    rest=1;
                }
                centralActivityDB.add_new_activity(edit_name.getText().toString(),
                        weight, set, rep, speed, time, rest);

                String test = edit_name.getText().toString()+weight+set+rep+speed+time+rest;
                Toast.makeText(NewActivity.this,
                        test,
                        Toast.LENGTH_LONG).show();
                Intent doneNewActivityIntent = new Intent(NewActivity.this, AddActivity.class);
                startActivity(doneNewActivityIntent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    // TODO: find out how to store info from checked boxes and display in AddActivity
}
