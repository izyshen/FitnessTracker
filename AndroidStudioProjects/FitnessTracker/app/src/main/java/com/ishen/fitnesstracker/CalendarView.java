package com.ishen.fitnesstracker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import static com.ishen.fitnesstracker.R.color.colorPrimaryDark;
import static com.ishen.fitnesstracker.R.id.parent;

/**
 * Created by WingsOfRetribution on 2017-10-24.
 */

public class CalendarView extends LinearLayout {

    private static final String TAG = "com.ishen.fitnesstracker.CalendarView";

    private static final int days_count = 42;
    private static final String days_format = "MMM YYYY";
    private Calendar current_date = Calendar.getInstance();

    private LinearLayout header;
    private ImageView prev_bt, next_bt;
    private TextView date;
    private GridView grid;

    public CaldendarView(Context context) {
        super(context);
        initControl(context);
    }

    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_custom_calendar, this);

        header = (LinearLayout) findViewById(R.id.calendar_weekdays);
        prev_bt = (ImageView) findViewById(R.id.prev_month_bt);
        next_bt = (ImageView) findViewById(R.id.next_month_bt);
        date = (TextView) findViewById(R.id.calendar_month);
        grid = (GridView) findViewById(R.id.calendar_days);
    }

    private void updateCalendar() {
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar)current_date.clone();

        // start cell for current month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int month_beginning_cell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        calendar.add(Calendar.DAY_OF_MONTH, -month_beginning_cell);

        // fill cells
        while (cells.size() < days_count) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // update grid
        ((calendar_adapter)grid.getAdapter()).update_data(cells);

        // update title
        SimpleDateFormat date_format = new SimpleDateFormat("MMM, YYYY");
        date.setText(date_format.format(current_date.getTime()));
    }

    private class calendar_adapter extends ArrayAdapter<Date> {

        private HashSet<Date> event_days;
        private LayoutInflater inflater;

        public calendar_adapter(Context context, ArrayList<Date> days, HashSet<Date> event_days) {
            super(context, R.layout.activity_custom_calendar, days);
            this.event_days = event_days;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int pos, View view, ViewGroup parent) {

            Calendar chosen_cal_date = Calendar.getInstance();
            Date chosen_date = chosen_cal_date.getTime();
            int day = chosen_cal_date.get(Calendar.DAY_OF_MONTH);
            int month = chosen_cal_date.get(Calendar.MONTH);
            int year = chosen_cal_date.get(Calendar.YEAR);

            Calendar today = Calendar.getInstance();

            // inflate item if it doesn't exist
            if (view == null) {
                view = inflater.inflate(R.layout.activity_custom_calendar_day, parent, false);
            }

            view.setBackgroundResource(0);
            if (event_days != null) {
                for (Date event_date : event_days) {
                    if (event_date.getDate() == day &&
                            event_date.getMonth() == month &&
                            event_date.getYear() == year) {
                        view.setBackgroundResource(R.drawable.dot);
                        break;
                    }

                }
            }

            // clear styling
            view.setTypeface(null, Typeface.NORMAL);
            view.setTextColor(Color.BLACK);

            if (month != today.get(Calendar.MONTH) ||
                    year != today.get(Calendar.YEAR)) {
                view.setTextColor(Color.LTGRAY);
            } else if (day == today.getDate()) {
                view.setTypeface(null, Typeface.BOLD);
                view.setTextColor(colorPrimaryDark);
            }

            // set date
            view.setText(String.valueOf(date.getDate()));

            return view;
        }
    }

}