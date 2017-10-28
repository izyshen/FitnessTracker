package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {

    private static final String TAG = "ReviewActivity";

    Button edit, delete;
    TextView name, weight, set, rep, time, speed, rest;
    String chosen_name, chosen_date, date_str, weight_unit, time_unit, speed_unit;
    SQLiteDbHelper historyDB;
    private int chosen_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        edit = (Button) findViewById(R.id.button_edit);
        delete = (Button) findViewById(R.id.button_add);
        weight = (TextView) findViewById(R.id.review_weight);
        set = (TextView) findViewById(R.id.review_set);
        rep = (TextView) findViewById(R.id.review_reps);
        time = (TextView) findViewById(R.id.review_time);
        speed = (TextView) findViewById(R.id.review_speed);
        rest = (TextView) findViewById(R.id.review_rest);
        historyDB = new SQLiteDbHelper(this);

        // extra from previous intent
        Intent workoutIntent = getIntent();
        chosen_name = workoutIntent.getStringExtra("name");
        chosen_date = Integer.toString(workoutIntent.getIntExtra("date", -1));
        date_str = workoutIntent.getStringExtra("date_str");
        chosen_id = workoutIntent.getIntExtra("id", -1);

        setTitle(chosen_name);

        final Cursor stored_data = historyDB.getHISTListContents();
        if ((stored_data.getCount()) == 0) {
            Toast.makeText(ReviewActivity.this,
                    "No history found",
                    Toast.LENGTH_LONG).show();
        } else {
            while (stored_data.moveToNext()) {
                if (stored_data.getInt(0) == chosen_id) {
                    Log.d(TAG, "onCreate: chosen_id: " + stored_data.getInt(0));
                    Log.d(TAG, "onCreate: chosen_name: " + stored_data.getString(1));
                    Log.d(TAG, "onCreate: chosen weight: " + stored_data.getString(2));
                    Log.d(TAG, "onCreate: chosen_set: " + stored_data.getString(3));
                    Log.d(TAG, "onCreate: chosen_rep: " + stored_data.getString(4));
                    Log.d(TAG, "onCreate: chosen_time: " + stored_data.getString(5));
                    Log.d(TAG, "onCreate: chosen_speed: " + stored_data.getString(6));
                    Log.d(TAG, "onCreate: chosen_rest: " + stored_data.getString(7));
                    if (stored_data.getString(2).length() > 0) {
                        String str = "Weight: " + stored_data.getString(2);  // contains 123lbs
                        weight.setText(str);
                    } else {
                        weight.setVisibility(View.INVISIBLE);
                    }
                    if (stored_data.getString(3).length() > 0) {
                        String str = "Set: " + stored_data.getString(3);
                        set.setText(str);
                    } else {
                        set.setVisibility(View.INVISIBLE);
                    }
                    if (stored_data.getString(4).length() > 0) {
                        String str = "Repetitions: " + stored_data.getString(4);
                        rep.setText(str);
                    } else {
                        rep.setVisibility(View.INVISIBLE);
                    }
                    if (stored_data.getString(5).length() > 0) {
                        String str = "Time: " + stored_data.getString(5);
                        time.setText(str);
                    } else {
                        time.setVisibility(View.INVISIBLE);
                    }
                    if (stored_data.getString(6).length() > 0) {
                        String str = "Speed: " + stored_data.getString(6);
                        speed.setText(str);
                    } else {
                        speed.setVisibility(View.INVISIBLE);
                    }
                    if (stored_data.getString(7).length() > 0) {
                        String str = "Rest: " + stored_data.getString(7);
                        rest.setText(str);
                    } else {
                        rest.setVisibility(View.INVISIBLE);
                    }
                    break;
                }
            }
        }

        // checks if there is a given weight for the corresponding exercise
        String stored_weight = stored_data.getString(2);
        if (stored_weight.length() > 2) {
            weight_unit = stored_weight.substring(stored_weight.length() - 3);
            weight_unit = weight_unit.replaceAll("[0-9.]", "");
        } else {
            weight_unit = "";
        }
        // checks if there is a given time
        String stored_time = stored_data.getString(5);
        if (stored_time.length() > 4) {
            time_unit = stored_time.substring(stored_time.length() - 5);
            time_unit = time_unit.replaceAll("[0-9.]", "");
        } else {
            time_unit = "";
        }
        // checks if there is a given speed
        String stored_speed = stored_data.getString(6);
        if (stored_speed.length() > 3) {
            speed_unit = stored_speed.substring(stored_speed.length() - 4);
            speed_unit = speed_unit.replaceAll("[0-9.]", "");
        } else {
            speed_unit = "";
        }

        Log.d(TAG, "onCreate: regex weight unit: " + weight_unit );
        Log.d(TAG, "onCreate: regex time unit: " + time_unit);
        Log.d(TAG, "onCreate: regex speed unit: " + speed_unit );

        // pass along data from exercise and put as hint
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(ReviewActivity.this, EditActivity.class);
                editIntent.putExtra("ex_name", stored_data.getString(1));
                editIntent.putExtra("ex_weight", stored_data.getString(2));
                editIntent.putExtra("weight_unit", weight_unit);
                editIntent.putExtra("ex_set", stored_data.getString(3));
                editIntent.putExtra("ex_rep", stored_data.getString(4));
                editIntent.putExtra("ex_time", stored_data.getString(5));
                editIntent.putExtra("time_unit", time_unit);
                editIntent.putExtra("ex_speed", stored_data.getString(6));
                editIntent.putExtra("speed_unit", speed_unit);
                editIntent.putExtra("ex_rest", stored_data.getString(7));
                editIntent.putExtra("chosen_date", chosen_date);
                editIntent.putExtra("date_str", date_str);
                editIntent.putExtra("id", chosen_id);
                startActivity(editIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent return_hist_display = new Intent(ReviewActivity.this, HistoryDisplay.class);
        return_hist_display.putExtra("chosen_date", chosen_date);
        return_hist_display.putExtra("date_str", date_str);
        startActivity(return_hist_display);
    }
}
