package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartWorkout extends AppCompatActivity {

    Button bt_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);

        bt_start = (Button) findViewById(R.id.button_start);

        // start button creates a new workout item
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivityIntent = new Intent(StartWorkout.this, AddActivity.class);
                startActivity(newActivityIntent);
            }
        });
    }
}
