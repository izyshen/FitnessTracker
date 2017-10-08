package com.ishen.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditActivity extends AppCompatActivity {

    Button edit, delete;
    TextView name, weight, set, rep, time, speed, rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edit = (Button) findViewById(R.id.button_edit);
        delete = (Button) findViewById(R.id.button_add);
        name = (TextView) findViewById(R.id.review_name);
        weight = (TextView) findViewById(R.id.review_weight);
        set = (TextView) findViewById(R.id.review_set);
        rep = (TextView) findViewById(R.id.review_reps);
        time = (TextView) findViewById(R.id.review_time);
        speed = (TextView) findViewById(R.id.review_speed);
        rest = (TextView) findViewById(R.id.review_rest);


    }
}
