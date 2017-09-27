package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    //ActivityDatabase myDB;
    ActivityDatabase2 myDB2;
    Button bt_add;
    String name;
    EditText setview, weightview, repview, timeview, speedview;
    Spinner weight_sp, time_sp, speed_sp, sp_type;

    // to choose existing exercise
    private static String[]info_priority = {"","Bicep Curls", "Leg Press", "Planks", "Treadmill"};
    // choose units
    String[] weight_units = {"lbs", "kg"};
    String[] speed_units = {"mph", "kph"};
    String[] time_units = {"hrs", "mins", "secs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // variable definitions
        bt_add = (Button) findViewById(R.id.button_add);
        setview = (EditText) findViewById(R.id.editSet);
        weightview = (EditText) findViewById(R.id.editWeight);
        repview = (EditText) findViewById(R.id.editRep);
        timeview = (EditText) findViewById(R.id.editTime);
        speedview = (EditText) findViewById(R.id.editSpeed);
        sp_type = (Spinner) findViewById(R.id.choose_exercise_sp);
        weight_sp = (Spinner) findViewById(R.id.weight_unit_sp);
        time_sp = (Spinner) findViewById(R.id.time_unit_sp);
        speed_sp = (Spinner) findViewById(R.id.speed_unit_sp);
        //myDB = new ActivityDatabase(this);
        myDB2 = new ActivityDatabase2(this);

        // adds an exercise and brings user back to workout activity listview
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chosen_exercise = name;

                String exercise_b1 = "";
                String exercise_b2 = "";
                int box1_pos = 0;

                // priority of information going in boxes of multiple column listview
                EditText[] categories = {weightview, setview, repview, timeview, speedview};
                int len_categories = categories.length;

                for (int i = 0; i <= len_categories - 1; i++) {
                    if ((categories[i].getText().toString()).length() != 0) {
                        exercise_b1 = categories[i].getText().toString();
                        box1_pos = i;
                        break;
                    }
                }
                box1_pos++;
                for (int j = box1_pos; j <= len_categories - 1; j++) {
                    if ((categories[j].getText().toString()).length() != 0) {
                        exercise_b2 = categories[j].getText().toString();
                        break;
                    }
                }

                if (chosen_exercise.length() != 0) {
                    //add_data(chosen_exercise);
                    add_data2(chosen_exercise, exercise_b1, exercise_b2);
                    weightview.setText("");
                    setview.setText("");
                    timeview.setText("");
                    speedview.setText("");
                    repview.setText("");
                } else {
                    Toast.makeText(AddActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
                Intent addActivityIntent = new Intent(AddActivity.this, WorkoutActivity.class);
                startActivity(addActivityIntent);
            }
        });

        // adapter for spinner of different exercise types and units
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                AddActivity.this,
                android.R.layout.simple_spinner_item,
                info_priority);
        ArrayAdapter<String> weight_adapter = new ArrayAdapter<String>(
                AddActivity.this,
                android.R.layout.simple_spinner_item,
                weight_units);
        ArrayAdapter<String> time_adapter = new ArrayAdapter<String>(
                AddActivity.this,
                android.R.layout.simple_spinner_item,
                time_units);
        ArrayAdapter<String> speed_adapter = new ArrayAdapter<String>(
                AddActivity.this,
                android.R.layout.simple_spinner_item,
                speed_units);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(adapter);
        //sp_type.setOnItemSelectedListener(this);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: make properties show up in sequence, not based on relative positions
                name = info_priority[i];
                switch (i) {
                    // properties according to selected activity from spinner
                    case 1:
                        weightview.setVisibility(View.VISIBLE);
                        setview.setVisibility(View.VISIBLE);
                        repview.setVisibility(View.VISIBLE);
                        timeview.setVisibility(View.INVISIBLE);
                        speedview.setVisibility(View.INVISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        weight_sp.setVisibility(View.VISIBLE);
                        time_sp.setVisibility(View.INVISIBLE);
                        speed_sp.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        weightview.setVisibility(View.VISIBLE);
                        setview.setVisibility(View.VISIBLE);
                        repview.setVisibility(View.VISIBLE);
                        timeview.setVisibility(View.INVISIBLE);
                        speedview.setVisibility(View.INVISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        weight_sp.setVisibility(View.VISIBLE);
                        time_sp.setVisibility(View.INVISIBLE);
                        speed_sp.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        weightview.setVisibility(View.VISIBLE);
                        setview.setVisibility(View.VISIBLE);
                        repview.setVisibility(View.INVISIBLE);
                        timeview.setVisibility(View.INVISIBLE);
                        speedview.setVisibility(View.INVISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        weight_sp.setVisibility(View.VISIBLE);
                        time_sp.setVisibility(View.VISIBLE);
                        speed_sp.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        weightview.setVisibility(View.INVISIBLE);
                        setview.setVisibility(View.INVISIBLE);
                        repview.setVisibility(View.INVISIBLE);
                        timeview.setVisibility(View.VISIBLE);
                        speedview.setVisibility(View.VISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        weight_sp.setVisibility(View.INVISIBLE);
                        time_sp.setVisibility(View.VISIBLE);
                        speed_sp.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        weight_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weight_sp.setAdapter(weight_adapter);

        speed_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speed_sp.setAdapter(speed_adapter);

        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_sp.setAdapter(time_adapter);
    }

    /*
    public void add_data(String chosen_exercise) {
        boolean insert_data = myDB.addData(chosen_exercise);

        if (insert_data == true) {
            Toast.makeText(AddActivity.this, "Successfully added Data.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AddActivity.this, "Oops, you messed up.", Toast.LENGTH_LONG).show();
        }
    }
    */
    public void add_data2(String name, String box1, String box2) {
        boolean insert_data2 = myDB2.add_Data2(name, box1, box2);

        if (insert_data2 == true) {
            Toast.makeText(AddActivity.this, "Data inserted successfully.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AddActivity.this,
                    "Error inserting data. Please contact app developer.", Toast.LENGTH_LONG).show();
        }
    }
}

