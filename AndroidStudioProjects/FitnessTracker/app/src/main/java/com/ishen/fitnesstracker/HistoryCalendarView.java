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
                int date = (year*10000) +
                        ((month+1)*100) +
                        day;
                Log.d(TAG, "onSelectedDayChange: yyyymmdd " + date);

                Intent chosen_date_intent = new Intent(HistoryCalendarView.this, HistoryDisplay.class);
                chosen_date_intent.putExtra("chosen_date", Integer.toString(date));
                startActivity(chosen_date_intent);
            }
        });
    }
}
