package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
                    if (stored_data.getString(2).length() > 0) {
                        String str = "Weight: " + stored_data.getString(2);
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
            Toast.makeText(ReviewActivity.this,
                    "Activities done on " + chosen_date + " displayed",
                    Toast.LENGTH_LONG).show();
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(ReviewActivity.this, EditActivity.class);
                editIntent.putExtra("ex_name", name.toString());
                editIntent.putExtra("ex_weight", weight.toString());
                editIntent.putExtra("ex_set", set.toString());
                editIntent.putExtra("ex_rep", rep.toString());
                editIntent.putExtra("ex_time", time.toString());
                editIntent.putExtra("ex_speed", speed.toString());
                editIntent.putExtra("ex_rest", rest.toString());

                // pass along data from exercise and put as hint
                startActivity(editIntent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent return_hist_display = new Intent(ReviewActivity.this, HistoryDisplay.class);
        return_hist_display.putExtra("chosen_date", chosen_date);
        startActivity(return_hist_display);
    }
}
