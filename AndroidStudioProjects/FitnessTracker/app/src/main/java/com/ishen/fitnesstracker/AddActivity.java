package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityDatabase myDB;
    Button bt_add;
    EditText nameview;
    EditText setview;
    EditText weightview;
    EditText repview;
    EditText timeview;

    private Spinner sp_type;

    // to choose existing exercise
    private static final String[]paths = {"","Bicep Curls", "Leg Press", "Planks"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // variable definitions
        bt_add = (Button) findViewById(R.id.button_add);
        nameview = (EditText) findViewById(R.id.editName);
        setview = (EditText) findViewById(R.id.editSet);
        weightview = (EditText) findViewById(R.id.editWeight);
        repview = (EditText) findViewById(R.id.editRep);
        timeview = (EditText) findViewById(R.id.editTime);

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
        //sp_type.setOnItemSelectedListener(this);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: make properties show up in sequence, not based on relative positions
                switch(i) {
                    // properties according to selected activity from spinner
                    case 1:
                        nameview.setVisibility(View.VISIBLE);
                        weightview.setVisibility(View.VISIBLE);
                        setview.setVisibility(View.VISIBLE);
                        repview.setVisibility(View.VISIBLE);
                        timeview.setVisibility(View.INVISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        nameview.setVisibility(View.VISIBLE);
                        weightview.setVisibility(View.VISIBLE);
                        setview.setVisibility(View.VISIBLE);
                        repview.setVisibility(View.VISIBLE);
                        timeview.setVisibility(View.INVISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        nameview.setVisibility(View.VISIBLE);
                        setview.setVisibility(View.VISIBLE);
                        timeview.setVisibility(View.VISIBLE);
                        repview.setVisibility(View.INVISIBLE);
                        weightview.setVisibility(View.VISIBLE);
                        bt_add.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    // displays information for activity based on spinner selection
    /*
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        // display name
        nameview.setVisibility(View.VISIBLE);
        switch(position) {
            case 0:
                weightview.setVisibility(View.VISIBLE);
                setview.setVisibility(View.VISIBLE);
                repview.setVisibility(View.VISIBLE);
                break;
            case 1:
                weightview.setVisibility(View.VISIBLE);
                setview.setVisibility(View.VISIBLE);
                repview.setVisibility(View.VISIBLE);
                break;
            case 2:
                setview.setVisibility(View.VISIBLE);
                timeview.setVisibility(View.VISIBLE);
                break;

        }
        bt_add.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        break;
    }
    */
}
