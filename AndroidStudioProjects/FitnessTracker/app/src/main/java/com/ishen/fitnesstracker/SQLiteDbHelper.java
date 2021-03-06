package com.ishen.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by WingsOfRetribution on 2017-10-06.
 */

public class SQLiteDbHelper extends SQLiteOpenHelper {
    
    private static final String TAG = "SQLiteDbHelper";

    public static final String DATABASE_NAME = "activity_storage_db";

    // table names
    public static final String TABLE_ACTIVITIES = "activity_lst";
    public static final String TABLE_DISPLAY = "display";
    public static final String TABLE_HISTORY = "history";

    public static final int DATABASE_VERSION = 1;

    // activity column names
    public static final String act_id = "ID";
    public static final String act_name = "NAME";
    public static final String act_weight = "WEIGHT";
    public static final String act_set = "SETS";
    public static final String act_reps = "REPS";
    public static final String act_time = "TIME";
    public static final String act_speed = "SPEED";
    public static final String act_rest = "REST";

    // display column names
    public static final String wkt_id = "ID";
    public static final String wkt_name = "NAME";
    public static final String wkt_box1 = "BOX1";
    public static final String wkt_box2 = "BOX2";
    public static final String wkt_date = "DATE";
    public static final String wkt_sdf = "DATE2";

    // history column names
    public static final String hist_id = "ID";
    public static final String hist_name = "NAME";
    public static final String hist_weight = "WEIGHT";
    public static final String hist_set = "SETS";
    public static final String hist_reps = "REPS";
    public static final String hist_time = "TIME";
    public static final String hist_speed = "SPEED";
    public static final String hist_rest = "REST";
    public static final String hist_date = "DATE";
    public static final String hist_sdf = "DATE2";


    public SQLiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // create table statements
    private static final String create_activity_table = "CREATE TABLE " +
            TABLE_ACTIVITIES +
            " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " NAME INTEGER," + " WEIGHT INTEGER," +
            " SETS INTEGER," + " REPS INTEGER," +
            " TIME INTEGER," + " SPEED INTEGER," + " REST INTEGER);";

    private static final String create_display_table = "CREATE TABLE " +
            TABLE_DISPLAY + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " NAME TEXT," + " BOX1 TEXT," + " BOX2 TEXT," + " DATE TEXT," + " DATE2 TEXT);";

    private static final String create_history_table = "CREATE TABLE " +
            TABLE_HISTORY +
            " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " NAME TEXT," + " WEIGHT TEXT," +
            " SETS TEXT," + " REPS TEXT," +
            " TIME TEXT," + " SPEED TEXT," + " REST TEXT," + " DATE TEXT," + " DATE2 TEXT);";


    // creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_activity_table);
        db.execSQL(create_display_table);
        db.execSQL(create_history_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS create_activity_table");
        db.execSQL("DROP TABLE IF EXISTS create_display_table");
        db.execSQL("DROP TABLE IF EXISTS create_history_table");
        onCreate(db);
    }

    // storing values in TABLE_ACTIVITIES
    public boolean add_new_activity(String name, int weight, int sets, int reps, int time, int speed, int rest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues entry_values = new ContentValues();
        entry_values.put(act_name, name);
        entry_values.put(act_weight, weight);
        entry_values.put(act_set, sets);
        entry_values.put(act_reps, reps);
        entry_values.put(act_time, time);
        entry_values.put(act_speed, speed);
        entry_values.put(act_rest, rest);

        long mid = 0;

        try {
            mid = db.insertOrThrow(TABLE_ACTIVITIES, null, entry_values);
        }
        catch(SQLException e) {
            Log.e("Exception", "SQLException" + String.valueOf(e.getMessage()));
            e.printStackTrace();
        }

        if (mid == -1) {
            return false;
        } else {
            return true;
        }
    }

    // obtaining contents of TABLE_ACTIVITIES
    public Cursor getACTListContents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_ACTIVITIES, null);
        return data;
    }


    // adding workout activities for the day in TABLE_DISPLAY
    public boolean add_data(String name, String box1, String box2, String date, String date2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(wkt_name, name);
        content_values.put(wkt_box1, box1);
        content_values.put(wkt_box2, box2);
        content_values.put(wkt_date, date);
        content_values.put(wkt_sdf, date2);


        long mid = 0;

        try {
            mid = db.insertOrThrow(TABLE_DISPLAY, null, content_values);
        }
        catch(SQLException e) {
            Log.e("Exception", "SQLException" + String.valueOf(e.getMessage()));
            e.printStackTrace();
        }

        if (mid == -1) {
            return false;
        } else {
            return true;
        }
    }

    // obtaining contents of db
    public Cursor getWKTListContents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_DISPLAY, null);
        return data;
    }

    public Cursor getItemId(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + wkt_id + " FROM " +
                TABLE_DISPLAY + " WHERE " + wkt_name + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    // storing values in TABLE_HISTORY
    public boolean add_history(String name, String weight, String sets, String reps,
                                    String time, String speed, String rest, String date, String date2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues entry_values = new ContentValues();
        entry_values.put(hist_name, name);
        entry_values.put(hist_weight, weight);
        entry_values.put(hist_set, sets);
        entry_values.put(hist_reps, reps);
        entry_values.put(hist_time, time);
        entry_values.put(hist_speed, speed);
        entry_values.put(hist_rest, rest);
        entry_values.put(hist_date, date);
        entry_values.put(hist_sdf, date2);

        long mid = 0;

        try {
            mid = db.insertOrThrow(TABLE_HISTORY, null, entry_values);
        }
        catch(SQLException e) {
            Log.e("Exception", "SQLException" + String.valueOf(e.getMessage()));
            e.printStackTrace();
        }

        if (mid == -1) {
            return false;
        } else {
            return true;
        }
    }

    // obtaining contents of TABLE_ACTIVITIES
    public Cursor getHISTListContents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_HISTORY, null);
        return data;
    }

    // returns an ID matching a date and name;
    public Cursor getHistoryItemID(String name, String date, String date2) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + hist_id + " FROM " + TABLE_HISTORY + " WHERE " + hist_name
                + " = '" + name + "'" + " AND " + hist_date + " = '" + date + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateHistory(String old_weight, String new_weight, String old_sets, String new_sets,
                              String old_reps, String new_reps, String old_time, String new_time,
                              String old_speed, String new_speed, String old_rest, String new_rest,
                              int id, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_HISTORY + " SET " + hist_weight + " = '" + new_weight + "', "
                + hist_set + " = '" + new_sets + "', " + hist_reps + " = '" + new_reps + "', "
                + hist_time + " = '" + new_time + "', " + hist_speed + " = '" + new_speed + "', "
                + hist_rest + " = '" + new_rest + "' WHERE " + hist_id + " = '" + id + "'";

        Log.d(TAG, "updateHistory: query: " + query);

        Log.d(TAG, "updateHistory: name: " + name);
        Log.d(TAG, "updateHistory: old_weights: " + old_weight);
        Log.d(TAG, "updateHistory: weight: " + new_weight);
        Log.d(TAG, "updateHistory: old_reps: " + old_reps);
        Log.d(TAG, "updateHistory: reps: " + new_reps);
        Log.d(TAG, "updateHistory: old_sets: " + old_sets);
        Log.d(TAG, "updateHistory: sets: " + new_sets);
        Log.d(TAG, "updateHistory: old_speed: " + old_speed);
        Log.d(TAG, "updateHistory: speed: " + new_speed);
        Log.d(TAG, "updateHistory: old_time: " + old_time);
        Log.d(TAG, "updateHistory: time: " + new_time);
        Log.d(TAG, "updateHistory: old_rest: " + old_rest);
        Log.d(TAG, "updateHistory: rest: " + new_rest);

        db.execSQL(query);
    }

    public Cursor getDisplayID(String name, String date, String date2) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + wkt_id + " FROM " + TABLE_DISPLAY + " WHERE " + wkt_name + " = '"
                + name + "' AND " + wkt_date + " = '" + date + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }

    public void updateDisplay(int disp_id, String date, String date2, String box1, String box2) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_DISPLAY + " SET " + wkt_box1 + " = '" + box1 + "', " +
                wkt_box2 + " = '" + box2 + "'" + " WHERE " + wkt_id + " = '" + disp_id + "' AND " +
                wkt_date + " = '" + date + "'";

        Log.d(TAG, "updateDisplay: display query: " + query);

        db.execSQL(query);
    }
}
