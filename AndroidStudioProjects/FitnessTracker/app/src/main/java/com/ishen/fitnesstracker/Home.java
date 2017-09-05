package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button bt_workout, bt_history, bt_button, bt_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bt_workout = (Button) findViewById(R.id.button);
        bt_history = (Button) findViewById(R.id.button2);
        bt_button = (Button) findViewById(R.id.button3);
        bt_settings = (Button) findViewById(R.id.button4);

        // takes user to workout activity
        bt_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent workoutIntent = new Intent(Home.this, WorkoutActivity.class);
                startActivity(workoutIntent);
            }

        });
    }
}
