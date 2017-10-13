package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    Button edit, delete;
    TextView name, weight, set, rep, time, speed, rest;
    String chosen_name, chosen_date;
    int id;
    SQLiteDbHelper historyDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        edit = (Button) findViewById(R.id.button_edit);
        delete = (Button) findViewById(R.id.button_add);
        name = (TextView) findViewById(R.id.review_name);
        weight = (TextView) findViewById(R.id.review_weight);
        set = (TextView) findViewById(R.id.review_set);
        rep = (TextView) findViewById(R.id.review_reps);
        time = (TextView) findViewById(R.id.review_time);
        speed = (TextView) findViewById(R.id.review_speed);
        rest = (TextView) findViewById(R.id.review_rest);
        historyDB = new SQLiteDbHelper(this);
        Intent workoutIntent = getIntent();
        id = workoutIntent.getIntExtra("id", -1);
        chosen_name = workoutIntent.getStringExtra("name");
        chosen_date = Integer.toString(workoutIntent.getIntExtra("date", -1));

        name.setText(chosen_name);
        setTitle(chosen_name);

        final Cursor stored_data = historyDB.getHISTListContents();
        if ((stored_data.getCount()) == 0) {
            Toast.makeText(ReviewActivity.this,
                    "No history found",
                    Toast.LENGTH_LONG).show();
        } else {
            while (stored_data.moveToNext()) {
                if ((chosen_date.equals(stored_data.getString(8))) &&
                        chosen_name.equals(stored_data.getString(1))) {
                    weight.setText("Weight: " + stored_data.getString(2));
                    set.setText("Set: " + stored_data.getString(3));
                    rep.setText("Rep: " + stored_data.getString(4));
                    time.setText("Time: " + stored_data.getString(5));
                    speed.setText("Speed: " + stored_data.getString(6));
                    rest.setText("Rest: " + stored_data.getString(7));
                    break;
                }
            }
            Toast.makeText(ReviewActivity.this,
                    "Activities done on " + chosen_date + " displayed",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        Intent return_hist_display = new Intent(ReviewActivity.this, HistoryDisplay.class);
        return_hist_display.putExtra("chosen_date", chosen_date);
        startActivity(return_hist_display);
    }
}
