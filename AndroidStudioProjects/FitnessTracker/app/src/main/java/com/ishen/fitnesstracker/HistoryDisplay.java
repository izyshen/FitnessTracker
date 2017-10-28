package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormatSymbols;
import java.util.Date;

public class HistoryDisplay extends AppCompatActivity {

    private static final String TAG = "HistoryDisplay";

    SQLiteDbHelper prev_exercise_DB;
    ArrayList<Exercise> exercise_list;
    ListView listview;
    Exercise exercise;
    int chosen_date, today_date;
    TextView no_ex;
    StringBuilder title_date;
    String date_str, today_date_str, format;
    Date chosen_d, today_d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_display);

        listview = (ListView) findViewById(R.id.history_listview);
        no_ex = (TextView) findViewById(R.id.no_exercise);
        prev_exercise_DB = new SQLiteDbHelper(this);
        Intent incoming_intent = getIntent();
        chosen_date = Integer.parseInt(incoming_intent.getStringExtra("chosen_date"));
        date_str = incoming_intent.getStringExtra("date_str");
        exercise_list = new ArrayList<>();

        // changes format of incoming date_str to an sdf date
        format = "yyyy-MM-dd";
        SimpleDateFormat incoming_sdf = new SimpleDateFormat(format);
        try {
            chosen_d = incoming_sdf.parse(date_str);
            Log.d(TAG, "onCreate: sdf incoming date: " + chosen_d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // changes format of today_date_str to sdf date
        today_d = new Date();
        SimpleDateFormat today_sdf = new SimpleDateFormat("yyyy-MM-dd");

        Log.d(TAG, "onCreate: today_d date from today_d.getTime(): " + today_d.getTime());
        today_date_str = today_sdf.format(today_d.getTime());
        incoming_sdf = new SimpleDateFormat(format);
        try {
            today_d = incoming_sdf.parse(today_date_str);
            Log.d(TAG, "onCreate: sdf incoming date: " + chosen_d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "onCreate: today_date_str from today_d.getTime(): " + today_date_str);
        Log.d(TAG, "onCreate: today_d date from parsing date_string: " + today_d);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        today_date = (year*10000) + (month*100) + day;

        int chosen_day = chosen_date%100;
        int chosen_month = (chosen_date/100)%100;
        int chosen_year = chosen_date/10000;

        // stores date as a string in sdf
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        today_date_str = sdf.format(d.getTime());
        Log.d(TAG, "onCreate: sdf time - " + today_date_str);
        Log.d(TAG, "onCreate: calendar date: " + today_date + "in YYYYMMDD");
        Log.d(TAG, "onCreate: prev_date: " + chosen_date);
        Log.d(TAG, "onCreate: previous calendar_sdf: " + date_str);

        // change actionbar title to date
        title_date = new StringBuilder();
        title_date.append(new DateFormatSymbols().getMonths()[chosen_month-1]);
        title_date.append(" " + chosen_day + ", " + chosen_year);
        setTitle(title_date);

        // populate exercise_list with data from DB for chosen date
        Cursor data = prev_exercise_DB.getWKTListContents();
        while(data.moveToNext()) {
            if ((Integer.parseInt(data.getString(4))== (chosen_date)) &&
                    (data.getString(5).equals(date_str))) {
                exercise = new Exercise(data.getString(1), data.getString(2), data.getString(3));
                exercise_list.add(exercise);
            }
        }

        // displays different things depending on whether the user tracked workouts that day
        if (exercise_list.size() > 0 ) {
            three_part_list_adapter adapter = new three_part_list_adapter(
                    this,
                    R.layout.activity_workout_layout,
                    exercise_list);
            listview.setAdapter(adapter);
            no_ex.setVisibility(View.INVISIBLE);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                    String name = exercise_list.get(pos).getDisp_name();
                    Log.d(TAG, "onItemClick: You clicked on " + name);

                    // chosen itemID passed on to next activity
                    Cursor data = prev_exercise_DB.getHistoryItemID(name, String.valueOf(chosen_date), date_str);
                    int itemID = -1;
                    while (data.moveToNext()) {
                        itemID = data.getInt(0);
                    }
                    if (itemID > -1) {
                        Log.d(TAG, "onItemClick: The ID is " + itemID);
                        Intent reviewIntent = new Intent(HistoryDisplay.this, ReviewActivity.class);
                        reviewIntent.putExtra("id", itemID);
                        reviewIntent.putExtra("name", name);
                        reviewIntent.putExtra("date", chosen_date);
                        reviewIntent.putExtra("date_str", date_str);
                        startActivity(reviewIntent);
                    } else {
                        Toast.makeText(HistoryDisplay.this,
                                "No ID associated with that name and date", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            if (chosen_date >= today_date) {
                no_ex.setText("Hey! You didn't workout this day... YET");
                if (chosen_d.after(today_d)) {
                    no_ex.setText("Hey! You didn't workout this day! x2");
                }
            } else {
                no_ex.setText("Looks like you took a break this day.");
            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent return_calendar = new Intent(HistoryDisplay.this, HistoryCalendarView.class);
        startActivity(return_calendar);
    }
}

