package com.ishen.fitnesstracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class HistoryCalendarView extends AppCompatActivity {

    private static final String TAG = "my_calendar_view";

    private CalendarView calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_calendar_view);

        calendar = (CalendarView) findViewById(R.id.history_calendar_view);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                            int year, int month, int day) {
                String date = Integer.toString(day) +
                        Integer.toString(month+1) +
                        Integer.toString(year);
                Log.d(TAG, "onSelectedDayChange: ddmmyyyy " + date);

                Intent chosen_date_intent = new Intent(HistoryCalendarView.this, HistoryDisplay.class);
                chosen_date_intent.putExtra("chosen_date", date);
                startActivity(chosen_date_intent);

            }
        });
    }
}
