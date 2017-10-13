package com.ishen.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormatSymbols;

public class HistoryDisplay extends AppCompatActivity {

    //DailyExercises prev_exercise_DB;
    SQLiteDbHelper prev_exercise_DB;
    ArrayList<Exercise> exercise_list;
    ListView listview;
    Exercise exercise;
    int chosen_date, today_date;
    TextView no_ex;
    StringBuilder title_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_display);

        listview = (ListView) findViewById(R.id.history_listview);
        no_ex = (TextView) findViewById(R.id.no_exercise);
        prev_exercise_DB = new SQLiteDbHelper(this);
        Intent incoming_intent = getIntent();
        chosen_date = Integer.parseInt(incoming_intent.getStringExtra("chosen_date"));
        exercise_list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        today_date = (year*10000) + (month*100) + day;

        //12345678
        int chosen_day = chosen_date%100;
        int chosen_month = (chosen_date/100)%100;
        int chosen_year = chosen_date/10000;

        // change actionbar title
        title_date = new StringBuilder();
        title_date.append(new DateFormatSymbols().getMonths()[chosen_month-1]);
        title_date.append(" " + chosen_day + ", " + chosen_year);
        setTitle(title_date);

        Cursor data = prev_exercise_DB.getWKTListContents();

        // populate exercise_list with data from DB for chosen date
        while(data.moveToNext()) {
            if (Integer.parseInt(data.getString(4))== (chosen_date)) {
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
                    Intent reviewIntent = new Intent(HistoryDisplay.this, ReviewActivity.class);
                    reviewIntent.putExtra("name", name);
                    reviewIntent.putExtra("date", chosen_date);
                    startActivity(reviewIntent);
                }
            });
        } else {
            if (chosen_date >= today_date) {
                no_ex.setText("Hey! You didn't workout out this day... YET");
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

