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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    ActivityDatabase activityDB;
    DailyExercises myDB;
    Button bt_add;
    String name;
    EditText setview, weightview, repview, timeview, speedview;
    Spinner weight_sp, time_sp, speed_sp, sp_type;
    ArrayList<ExerciseProperties> properties;
    ExerciseProperties exercise;
    int num_activities;

    // TODO: choose how many items are displayed on screen; scrollable

    // choose units
    String[] weight_units = {"lbs", "kg"};
    String[] speed_units = {"mph", "kph"};
    String[] time_units = {"hrs", "mins", "secs"};

    String chosen_weight_unit;
    String chosen_speed_unit;
    String chosen_time_unit;

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
        myDB = new DailyExercises(this);
        activityDB = new ActivityDatabase(this);

        // adds an exercise and brings user back to workout activity listview
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chosen_exercise = name;

                StringBuilder exercise_b1 = new StringBuilder();
                StringBuilder exercise_b2 = new StringBuilder();
                int box1_pos = 0;

                // priority of information going in boxes of multiple column listview
                EditText[] categories = {weightview, setview, repview, timeview, speedview};
                int len_categories = categories.length;

                for (int i = 0; i <= len_categories - 1; i++) {
                    if ((categories[i].getText().toString()).length() != 0) {
                        exercise_b1.append(categories[i].getText().toString());
                        if (categories[i] == weightview) {
                            exercise_b1.append(chosen_weight_unit);
                        } else if (categories[i] == timeview) {
                            exercise_b1.append(chosen_time_unit);
                        } else if (categories[i] == speedview) {
                            exercise_b1.append(chosen_speed_unit);
                        } else if (categories[i] == setview) {      // gives sets x reps in workout_activity
                            exercise_b1.append(" x ");
                            exercise_b1.append(repview.getText().toString());
                            box1_pos++;
                        }
                        box1_pos += i;
                        break;
                    }
                }
                box1_pos++;
                for (int j = box1_pos; j <= len_categories - 1; j++) {
                    if ((categories[j].getText().toString()).length() != 0) {
                        exercise_b2.append(categories[j].getText().toString());
                        if (categories[j] == weightview) {
                            exercise_b2.append(chosen_weight_unit);
                        } else if (categories[j] == timeview) {
                            exercise_b2.append(chosen_time_unit);
                        } else if (categories[j] == speedview) {
                            exercise_b2.append(chosen_speed_unit);
                        } else if (categories[j] == setview) {      // gives sets x reps in workout_activity
                            exercise_b2.append(" x ");
                            exercise_b2.append(repview.getText().toString());
                        }
                        break;
                    }
                }

                if (chosen_exercise.length() != 0) {
                    add_data2(chosen_exercise, exercise_b1.toString(), exercise_b2.toString());
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

        // Creates arraylist for items in spinner
        final ArrayList<String> listof_exercises = new ArrayList<String>();
        listof_exercises.add("");
        listof_exercises.add("Bicep Curls");
        listof_exercises.add("Leg Press");
        listof_exercises.add("Planks");
        listof_exercises.add("Treadmill");
        listof_exercises.add("Custom Exercise");
        num_activities = 5;

        // Pre-added exercises (default)
        properties = new ArrayList<>();
        /* parameters for ExerciseProperties:
        String name, int weight, int sets, int reps, int time, int speed, int rest */
        exercise = new ExerciseProperties("Bicep Curls", 1, 1, 1, 0, 0, 0);
        properties.add(exercise);
        exercise = new ExerciseProperties("Leg Press", 1, 1, 1, 0, 0, 0);
        properties.add(exercise);
        exercise = new ExerciseProperties("Planks", 1, 1, 0, 1, 0, 0);
        properties.add(exercise);
        exercise = new ExerciseProperties("Treadmill", 0, 0, 0, 1, 1, 0);
        properties.add(exercise);

        // dynamically add new elements to listof_exercises based on user input in NewActivity
        final Cursor activity_data = activityDB.getListContents();
        if ((activity_data.getCount()) == 0) {
            Toast.makeText(AddActivity.this,
                    "No new activities added",
                    Toast.LENGTH_LONG).show();
        } else {
            while (activity_data.moveToNext()) {
                exercise = new ExerciseProperties(activity_data.getString(1), activity_data.getInt(2),
                        activity_data.getInt(3), activity_data.getInt(4), activity_data.getInt(5),
                        activity_data.getInt(6), activity_data.getInt(7));
                listof_exercises.add(num_activities, activity_data.getString(1));
                num_activities++;
                properties.add(exercise);
            }
            Toast.makeText(AddActivity.this,
                    num_activities + "New exercises added to spinner",
                    Toast.LENGTH_LONG).show();

        }

        // adapter for spinner of different exercise types and units
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                AddActivity.this,
                android.R.layout.simple_spinner_item,
                listof_exercises);
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
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: make properties show up in sequence, not based on relative positions
                name = listof_exercises.get(i);

                if (i == num_activities) {
                    Intent newActivityIntent = new Intent(AddActivity.this, NewActivity.class);
                    startActivity(newActivityIntent);
                    return;
                }

                // displays desired properties for chosen exercise
                for(ExerciseProperties exercise : properties) {
                    if (exercise.getDisp_name().equals(name)) {
                        if (exercise.getWeightbox() == 1) {
                            weightview.setVisibility(View.VISIBLE);
                            weight_sp.setVisibility(View.VISIBLE);
                        } else {
                            weightview.setVisibility(View.INVISIBLE);
                            weight_sp.setVisibility(View.INVISIBLE);
                        }
                        if (exercise.getSetbox() == 1) {
                            setview.setVisibility(View.VISIBLE);
                        } else {
                            setview.setVisibility(View.INVISIBLE);
                        }
                        if (exercise.getRepbox() == 1) {
                            repview.setVisibility(View.VISIBLE);
                        } else {
                            repview.setVisibility(View.INVISIBLE);
                        }
                        if (exercise.getTimebox() == 1) {
                            timeview.setVisibility(View.VISIBLE);
                            time_sp.setVisibility(View.VISIBLE);
                        } else {
                            timeview.setVisibility(View.INVISIBLE);
                            time_sp.setVisibility(View.INVISIBLE);
                        }
                        if (exercise.getSpeedbox() == 1) {
                            speedview.setVisibility(View.VISIBLE);
                            speed_sp.setVisibility(View.VISIBLE);
                        } else {
                            speedview.setVisibility(View.INVISIBLE);
                            speed_sp.setVisibility(View.INVISIBLE);
                        }
                        bt_add.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

    public void add_data2(String name, String box1, String box2) {
        boolean insert_data2 = myDB.add_Data(name, box1, box2);

        if (insert_data2 == true) {
            Toast.makeText(AddActivity.this, "Data inserted successfully.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AddActivity.this,
                    "Error inserting data. Please contact app developer.", Toast.LENGTH_LONG).show();
        }
    }
}

