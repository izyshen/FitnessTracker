package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button bt1, bt2, bt3, bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);

        // takes user to workout activity
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent workoutIntent = new Intent(Home.this, WorkoutActivity.class);
                startActivity(workoutIntent);
            }

        });
    }
}
