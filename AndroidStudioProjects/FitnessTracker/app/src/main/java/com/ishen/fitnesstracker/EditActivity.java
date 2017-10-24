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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private static final String TAG = "EditActivity";

    EditText weight, set, rep, time, speed, rest;
    Spinner weight_sp, time_sp, speed_sp;
    SQLiteDbHelper historyDB;
    String chosen_weight_unit, chosen_speed_unit, chosen_time_unit,
            ex_name, ex_weight_val, ex_set_val, ex_rep_val, ex_time_val, ex_speed_val, ex_rest_val,
            chosen_date;
    Button save;
    int chosen_id;
    ArrayList<ExerciseProperties> properties;

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
        ex_weight_val = prev_intent.getStringExtra("ex_weight").replaceAll("[A-Za-z]", "");
        chosen_weight_unit = prev_intent.getStringExtra("weight_unit");
        ex_set_val = prev_intent.getStringExtra("ex_set");
        ex_rep_val = prev_intent.getStringExtra("ex_rep");
        ex_time_val = prev_intent.getStringExtra("ex_time").replaceAll("[A-Za-z]", "");
        chosen_time_unit = prev_intent.getStringExtra("time_unit");
        ex_speed_val = prev_intent.getStringExtra("ex_speed").replaceAll("[A-Za-z]]", "");
        chosen_speed_unit = prev_intent.getStringExtra("speed_unit");
        ex_rest_val = prev_intent.getStringExtra("ex_rest");
        chosen_date = prev_intent.getStringExtra("chosen_date");
        chosen_id = prev_intent.getIntExtra("id", -1);
        setTitle(ex_name);

        Log.d(TAG, "onCreate: replaced old ex_weight_val: " + ex_weight_val);
        Log.d(TAG, "onCreate: replaced old ex_time_val: " + ex_time_val);
        Log.d(TAG, "onCreate: replaced old ex_speed_val: " + ex_speed_val);

        weight = (EditText) findViewById(R.id.editWeight);
        set = (EditText) findViewById(R.id.editSet);
        rep = (EditText) findViewById(R.id.editRep);
        time = (EditText) findViewById(R.id.editTime);
        speed = (EditText) findViewById(R.id.editSpeed);
        rest = (EditText) findViewById(R.id.editRest);
        weight_sp = (Spinner) findViewById(R.id.weight_unit_sp);
        time_sp = (Spinner) findViewById(R.id.time_unit_sp);
        speed_sp = (Spinner) findViewById(R.id.speed_unit_sp);
        historyDB = new SQLiteDbHelper(this);
        save = (Button) findViewById(R.id.button_save);

        // hides unnecessary text fields
        if (ex_set_val.length() == 0) {
            set.setVisibility(View.INVISIBLE);
        }
        if (ex_rep_val.length() == 0) {
            rep.setVisibility(View.INVISIBLE);
        }
        if (ex_rest_val.length() == 0) {
            rest.setVisibility(View.INVISIBLE);
        }

        // set hints
        String hint;
        hint = "Weight: " + ex_weight_val;
        weight.setHint(hint);
        hint = "Set: " + ex_set_val;
        set.setHint(hint);
        hint = "Reps: " + ex_rep_val;
        rep.setHint(hint);
        hint = "Time: " + ex_time_val;
        time.setHint(hint);
        hint = "Speed: " + ex_speed_val;
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

        // TODO: Correction-changing unit selection
        // if unit was stored, display spinner with same unit. else hide EditText and spinner
        if (chosen_weight_unit.length() != 0) {
            if (chosen_weight_unit.equals(weight_units[0])) {
                weight_sp.setSelection(0);
            } else {
                weight_sp.setSelection(1);
            }
        } else {
            weight_sp.setVisibility(View.INVISIBLE);
            weight.setVisibility(View.INVISIBLE);
        }

        if (chosen_time_unit.length() != 0) {
            if (chosen_time_unit.equals(time_units[0])) {
                time_sp.setSelection(0);
            } else if (chosen_time_unit.equals(time_units[1])) {
                time_sp.setSelection(1);
            } else {
                time_sp.setSelection(2);
            }
        } else {
            time_sp.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
        }

        if (chosen_speed_unit.length() != 0) {
            if (chosen_speed_unit.equals(speed_units[0])) {
                speed_sp.setSelection(0);
            } else {
                speed_sp.setSelection(1);
            }
        } else {
            speed_sp.setVisibility(View.INVISIBLE);
            speed.setVisibility(View.INVISIBLE);
        }


        // saves changes and overrides entry in HistoryDB
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder new_weight_str = new StringBuilder();
                StringBuilder new_set_str = new StringBuilder();
                StringBuilder new_rep_str = new StringBuilder();
                StringBuilder new_time_str = new StringBuilder();
                StringBuilder new_speed_str = new StringBuilder();
                StringBuilder new_rest_str = new StringBuilder();

                // new strings are either empty or contain values with selected units
                if (weight.getText().toString().length() > 0) {
                    new_weight_str.append(weight.getText().toString() + chosen_weight_unit);
                }
                new_set_str.append(set.getText().toString());
                new_rep_str.append(rep.getText().toString());
                if (time.getText().toString().length() > 0) {
                    new_time_str.append(time.getText().toString() + chosen_time_unit);
                }
                if (speed.getText().toString().length() > 0) {
                    new_speed_str.append(speed.getText().toString() + chosen_speed_unit);
                }
                new_rest_str.append(rest.getText().toString());

                // Check empty
                // updates the item at chosen_id with new values
                historyDB.updateHistory(ex_weight_val, new_weight_str.toString(),
                        ex_set_val, new_set_str.toString(), ex_rep_val, new_rep_str.toString(),
                        ex_time_val, new_time_str.toString(), ex_speed_val, new_speed_str.toString(),
                        ex_rest_val, new_rest_str.toString(), chosen_id, ex_name);

                Log.d(TAG, "onClick: passing name: " + ex_name);
                Log.d(TAG, "onClick: passing date: " + Integer.parseInt(chosen_date));
                Log.d(TAG, "onClick: passing id: " + chosen_id);

                // Obtain display id and update display table

                int exercise_display_id = -1;
                Cursor display_data = historyDB.getDisplayID(ex_name, chosen_date);
                while (display_data.moveToNext()) {
                    exercise_display_id = display_data.getInt(0);
                }
                if (exercise_display_id > -1) {
                    Toast.makeText(EditActivity.this, "itemID retrieved: " + exercise_display_id,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditActivity.this, "No ID assoc. with name and date",
                            Toast.LENGTH_SHORT).show();
                }
                // obtain contents of box1 and box2
                EditText properties[] = {weight, set, rep, time, speed, rest};
                int len_properties = properties.length;
                StringBuilder box1 = new StringBuilder();
                StringBuilder box2 = new StringBuilder();
                int box1_pos = 0;

                // sets box1 value
                for (int i=0; i<len_properties; i++) {
                    if (properties[i].getText().toString().length() > 0) {
                        box1_pos = i;
                        box1.append(properties[i].getText().toString());
                        if (properties[i] == weight) {
                            box1.append(chosen_weight_unit);
                        } else if (properties[i] == time) {
                            box1.append(chosen_time_unit);
                        } else if (properties[i] == speed) {
                            box1.append(chosen_speed_unit);
                        } else if (properties[i] == set) {
                            if (rep.getText().toString().length() > 0) {
                                box1.append(" x ");
                                box1.append(rep.getText().toString());
                            }
                            box1_pos++;
                        }
                        break;
                    }
                    box1_pos++;
                }
                // sets box2 value
                for (int j=box1_pos+1; j<len_properties; j++) {
                    if(properties[j].getText().toString().length() > 0) {
                        box2.append(properties[j].getText().toString());
                        if (properties[j] == speed) {
                            box2.append(chosen_speed_unit);
                        } else if (properties[j] == time) {
                            box2.append(chosen_time_unit);
                        } else if (properties[j] == set) {
                            if (rep.getText().toString().length()>0) {
                                box2.append(" x ");
                                box2.append(rep.getText().toString());
                            }
                        }
                        break;
                    }
                }

                Log.d(TAG, "onClick: updated box1 val : " + box1);
                Log.d(TAG, "onClick: updated box2 val: " + box2);

                historyDB.updateDisplay(exercise_display_id, chosen_date, box1.toString(), box2.toString());

                Intent reviewIntent = new Intent(EditActivity.this, ReviewActivity.class);
                reviewIntent.putExtra("name", ex_name);
                reviewIntent.putExtra("date", Integer.parseInt(chosen_date));
                reviewIntent.putExtra("id", chosen_id);
                startActivity(reviewIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent reviewIntent = new Intent(EditActivity.this, ReviewActivity.class);
        reviewIntent.putExtra("name", ex_name);
        reviewIntent.putExtra("date", Integer.parseInt(chosen_date));
        reviewIntent.putExtra("id", chosen_id);
        startActivity(reviewIntent);
    }
}