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

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.reflect.Array.getLength;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityDatabase myDB;
    ActivityDatabase2 myDB2;
    Button bt_add;
    String name;
    EditText setview;
    EditText weightview;
    EditText repview;
    EditText timeview;
    EditText speedview;

    private Spinner sp_type;

    // to choose existing exercise
    private static final String[]paths = {"","Bicep Curls", "Leg Press", "Planks", "Treadmill"};

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
        //myDB = new ActivityDatabase(this);
        myDB2 = new ActivityDatabase2(this);

        // adds an exercise to workout activity
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chosen_exercise = name;

                // new

                String exercise_b1 = "";
                String exercise_b2 = "";
                int box1_pos=0;

                // priority of information going in boxes of multiple column listview
                EditText[] categories = {weightview, setview, repview, timeview, speedview};
                int len_categories = getLength(categories);

                for (int i=0; i<len_categories-1; i++) {
                    if (categories[i].getText().toString() != null) {
                        exercise_b1 = categories[i].getText().toString();
                        box1_pos = i;
                        break;
                    }
                }
                box1_pos++;
                for (int j=box1_pos; j<len_categories-1; j++) {
                    if (categories[j].getText().toString() != null) {
                        exercise_b2 = categories[j].getText().toString();
                        break;
                    }
                }


                // new up until here

                if (chosen_exercise.length() != 0) {
                    //add_data(chosen_exercise);
                    add_data2(chosen_exercise, exercise_b1, exercise_b2);

                }
                Intent addActivityIntent = new Intent(AddActivity.this, WorkoutActivity.class);
                startActivity(addActivityIntent);
            }
        });

        // adapter for spinner of different exercise types
        sp_type = (Spinner) findViewById(R.id.choose_exercise_sp);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(
                AddActivity.this,
                android.R.layout.simple_spinner_item,
                paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(adapter);
        //sp_type.setOnItemSelectedListener(this);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: make properties show up in sequence, not based on relative positions
                name = paths[i];
                switch(i) {
                    // properties according to selected activity from `inner
                    case 1:
                        weightview.setVisibility(View.VISIBLE);
                        setview.setVisibility(View.VISIBLE);
                        repview.setVisibility(View.VISIBLE);
                        timeview.setVisibility(View.INVISIBLE);
                        speedview.setVisibility(View.INVISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        weightview.setVisibility(View.VISIBLE);
                        setview.setVisibility(View.VISIBLE);
                        repview.setVisibility(View.VISIBLE);
                        timeview.setVisibility(View.INVISIBLE);
                        speedview.setVisibility(View.INVISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        setview.setVisibility(View.VISIBLE);
                        timeview.setVisibility(View.VISIBLE);
                        repview.setVisibility(View.INVISIBLE);
                        weightview.setVisibility(View.VISIBLE);
                        speedview.setVisibility(View.INVISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        speedview.setVisibility(View.VISIBLE);
                        setview.setVisibility(View.INVISIBLE);
                        repview.setVisibility(View.INVISIBLE);
                        weightview.setVisibility(View.INVISIBLE);
                        timeview.setVisibility(View.VISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void add_data(String chosen_exercise) {
        boolean insert_data = myDB.addData(chosen_exercise);

        if (insert_data == true) {
            Toast.makeText(AddActivity.this, "Successfully added Data", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AddActivity.this, "Oops, you messed up", Toast.LENGTH_LONG).show();
        }
    }

    public void add_data2(String chosen_exercise, String box1, String box2) {
        boolean insert_data2 = myDB2.addData(chosen_exercise, box1, box2);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
