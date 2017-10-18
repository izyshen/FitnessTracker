package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditActivity extends AppCompatActivity {

    EditText weight, set, rep, time, speed, rest;
    Spinner weight_sp, rep_sp, time_sp, speed_sp;
    SQLiteDbHelper historyDB, workoutDB;
    String weight_val, time_val, speed_val, rest_val,
            weight_sp_val, time_sp_val, speed_sp_val, rest_sp_val,
            chosen_weight_unit, chosen_speed_unit, chosen_time_unit;

    // Units for spinners
    String[] weight_units = {"lbs", "kg"};
    String[] speed_units = {"mph", "kph"};
    String[] time_units = {"hrs", "mins", "secs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // obtaining previously entered data for hints
        Intent prev_intent = getIntent();
        String ex_name = prev_intent.getStringExtra("ex_name");
        String ex_weight = prev_intent.getStringExtra("ex_weight");
        String ex_set = prev_intent.getStringExtra("ex_set");
        String ex_rep = prev_intent.getStringExtra("ex_rep");
        String ex_time = prev_intent.getStringExtra("ex_time");
        String ex_speed = prev_intent.getStringExtra("ex_speed");
        String ex_rest = prev_intent.getStringExtra("");
        setTitle(ex_name);

        weight = (EditText) findViewById(R.id.editWeight);
        set = (EditText) findViewById(R.id.editSet);
        rep = (EditText) findViewById(R.id.editRep);
        time = (EditText) findViewById(R.id.editTime);
        speed = (EditText) findViewById(R.id.editSpeed);
        rest = (EditText) findViewById(R.id.editRest);
        weight_sp = (Spinner) findViewById(R.id.weight_unit_sp);
        time_sp = (Spinner) findViewById(R.id.time_unit_sp);
        speed_sp = (Spinner) findViewById(R.id.speed_unit_sp);
        workoutDB = new SQLiteDbHelper(this);
        historyDB = new SQLiteDbHelper(this);

        // hint setup
        weight_val = ex_weight.replace("[^A-za-z]", "");
        weight_sp_val = ex_weight.replace("[0-9]", "");
        time_val = ex_time.replace("[^A-Za-z]", "");
        time_sp_val = ex_time.replace("[0-9]", "");
        speed_val = ex_speed.replace("[^A-Za-z]", "");
        speed_sp_val = ex_speed.replace("[0-9]", "");
        rest_val = ex_speed.replace("[^A-Za-z]", "");
        rest_sp_val = ex_rest.replace("[0-9]", "");

        // set hints
        weight.setHint(weight_val);
        set.setHint(ex_set);
        rep.setHint(ex_rep);
        time.setHint(time_val);
        speed.setHint(speed_val);
        rest.setHint(rest_val);

        if (weight_sp_val.equals(weight_units[0])) {
            chosen_weight_unit = weight_units[0];
            weight_sp.setSelection(0);
        } else {
            chosen_weight_unit = weight_units[1];
            weight_sp.setSelection(1);
        }
        if (time_sp_val.equals(time_units[0])) {
            chosen_time_unit = time_units[0];
            time_sp.setSelection(0);
        } else if (time_sp_val.equals(time_units[1])) {
            chosen_time_unit = time_units[1];
            time_sp.setSelection(1);
        } else {
            chosen_time_unit = time_units[2];
            time_sp.setSelection(2);
        }
        if (speed_sp_val.equals(speed_units[0])) {
            chosen_speed_unit = speed_units[0];
            speed_sp.setSelection(0);
        } else {
            chosen_speed_unit = speed_units[1];
            speed_sp.setSelection(1);
        }

        // adapters for unit spinners
        ArrayAdapter<String> weight_adapter = new ArrayAdapter<String>(
                EditActivity.this,
                android.R.layout.simple_spinner_item,
                weight_units);
        ArrayAdapter<String> time_adapter = new ArrayAdapter<String>(
                EditActivity.this,
                android.R.layout.simple_spinner_item,
                time_units);
        ArrayAdapter<String> speed_adapter = new ArrayAdapter<String>(
                EditActivity.this,
                android.R.layout.simple_spinner_item,
                speed_units);

        weight_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weight_sp.setAdapter(weight_adapter);

        weight_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chosen_weight_unit = weight_units[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        speed_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speed_sp.setAdapter(speed_adapter);

        speed_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chosen_speed_unit = speed_units[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_sp.setAdapter(time_adapter);

        time_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chosen_time_unit = time_units[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        }
}
