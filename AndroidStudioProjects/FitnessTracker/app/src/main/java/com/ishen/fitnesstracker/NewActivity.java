package com.ishen.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }

    Spinner spinner = (Spinner) findViewById(R.id.muscle_spinner);

    // array adapter with string array and spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
            R.array.type_array,
            android.R.layout.simple_spinner_item);

    // layout for list of choices
     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // apply adapter to spinner
    spinner.setAdapter(adapter);
    
}
