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

    public static final String DATABASE_NAME = "activity_storage_db";

    // table names
    public static final String TABLE_ACTIVITIES = "activity_lst";
    public static final String TABLE_WORKOUT = "daily_workout";

    public static final int DATABASE_VERSION = 1;

    // activity_lst column names
    public static final String act_id = "ID";
    public static final String act_name = "NAME";
    public static final String act_weight = "WEIGHT";
    public static final String act_set = "SETS";
    public static final String act_reps = "REPS";
    public static final String act_time = "TIME";
    public static final String act_speed = "SPEED";
    public static final String act_rest = "REST";

    // daily_workout column names
    public static final String wkt_id = "ID";
    public static final String wkt_name = "NAME";
    public static final String wkt_box1 = "BOX1";
    public static final String wkt_box2 = "BOX2";
    public static final String wkt_date = "DATE";

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

    private static final String create_dy_workout_table = "CREATE TABLE " +
            TABLE_WORKOUT + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " NAME TEXT," + " BOX1 TEXT," + " BOX2 TEXT," + " DATE TEXT);";

    // creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_activity_table);
        db.execSQL(create_dy_workout_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + create_activity_table);
        db.execSQL("DROP IF TABLE EXISTS " + create_dy_workout_table);
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


    // adding workout activities for the day in TABLE_WORKOUT
    public boolean add_Data(String name, String box1, String box2, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(wkt_name, name);
        content_values.put(wkt_box1, box1);
        content_values.put(wkt_box2, box2);
        content_values.put(wkt_date, date);

        long mid = 0;

        try {
            mid = db.insertOrThrow(TABLE_WORKOUT, null, content_values);
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
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_WORKOUT, null);
        return data;
    }

}
