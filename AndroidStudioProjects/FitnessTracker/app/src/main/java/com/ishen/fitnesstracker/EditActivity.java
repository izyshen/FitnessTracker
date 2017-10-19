package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText weight, set, rep, time, speed, rest;
    Spinner weight_sp, time_sp, speed_sp;
    SQLiteDbHelper historyDB, workoutDB;
    String weight_val, time_val, speed_val, rest_val,
            weight_sp_val, time_sp_val, speed_sp_val,
            chosen_weight_unit, chosen_speed_unit, chosen_time_unit,
            ex_name, ex_weight, ex_set, ex_rep, ex_time, ex_speed, ex_rest,
            chosen_date;

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
        ex_name = prev_intent.getStringExtra("ex_name");
        ex_weight = prev_intent.getStringExtra("ex_weight");
        chosen_weight_unit = prev_intent.getStringExtra("weight_unit");
        ex_set = prev_intent.getStringExtra("ex_set");
        ex_rep = prev_intent.getStringExtra("ex_rep");
        ex_time = prev_intent.getStringExtra("ex_time");
        chosen_time_unit = prev_intent.getStringExtra("time_unit");
        ex_speed = prev_intent.getStringExtra("ex_speed");
        chosen_speed_unit = prev_intent.getStringExtra("speed_unit");
        ex_rest = prev_intent.getStringExtra("ex_rest");
        chosen_date = prev_intent.getStringExtra("chosen_date");
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

        // hides unnecessary text fields
        if (ex_set.length() == 0) {
            set.setVisibility(View.INVISIBLE);
        }
        if (ex_rep.length() == 0) {
            rep.setVisibility(View.INVISIBLE);
        }
        if (ex_rest.length() == 0) {
            rest.setVisibility(View.INVISIBLE);
        }

        // set hints
        String hint;
        hint = "Weight: " + weight_val;
        weight.setHint(hint);
        hint = "Set: " + ex_set;
        set.setHint(hint);
        hint = "Reps: " + ex_rep;
        rep.setHint(hint);
        hint = "Time: " + time_val;
        time.setHint(hint);
        hint = "Speed: " + speed_val;
        speed.setHint(hint);
        hint = "Rest Time: ";
        rest.setHint(hint);

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

        // if unit was stored, display spinner with same unit. else hide EditText and spinner
        if (weight_sp_val.length() != 0) {
            if (weight_sp_val.equals(weight_units[0])) {
                chosen_weight_unit = weight_units[0];
                weight_sp.setSelection(0);
            } else {
                chosen_weight_unit = weight_units[1];
                weight_sp.setSelection(1);
            }
        } else {
            weight_sp.setVisibility(View.INVISIBLE);
            weight.setVisibility(View.INVISIBLE);
        }

        if (time_sp_val.length() != 0) {
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
        } else {
            time_sp.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
        }

        if (speed_sp_val.length() != 0) {
            if (speed_sp_val.equals(speed_units[0])) {
                chosen_speed_unit = speed_units[0];
                speed_sp.setSelection(0);
            } else {
                chosen_speed_unit = speed_units[1];
                speed_sp.setSelection(1);
            }
        } else {
            speed_sp.setVisibility(View.INVISIBLE);
            speed.setVisibility(View.INVISIBLE);
        }


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

    @Override
    public void onBackPressed() {
        Intent reviewIntent= new Intent(EditActivity.this, ReviewActivity.class);
        reviewIntent.putExtra("name", ex_name);
        reviewIntent.putExtra("date", chosen_date);
        startActivity(reviewIntent);
    }
}