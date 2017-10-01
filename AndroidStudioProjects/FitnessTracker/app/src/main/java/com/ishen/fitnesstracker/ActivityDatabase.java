package com.ishen.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Spinner;

/**
 * Created by WingsOfRetribution on 2017-09-17.
 */

public class ActivityDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "activity_lst.db";
    public static final String TABLE_NAME = "activity_lst";
    private static final int DATABASE_VERSION = 1;
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "WEIGHT";
    public static final String COL4 = "SETS";
    public static final String COL5 = "REPS";
    public static final String COL6 = "TIME";
    public static final String COL7 = "SPEED";
    public static final String COL8 = "REST";

    public ActivityDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " +
                TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME INTEGER," + " WEIGHT INTEGER," +
                " SETS INTEGER," + " REPS INTEGER," +
                " TIME INTEGER," + " SPEED INTEGER," + " REST INTEGER);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // storing values in db
    public boolean add_new_activity(String name, int weight, int sets, int reps, int time, int speed, int rest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues entry_values = new ContentValues();
        entry_values.put(COL2, name);
        entry_values.put(COL3, weight);
        entry_values.put(COL4, sets);
        entry_values.put(COL5, reps);
        entry_values.put(COL6, time);
        entry_values.put(COL7, speed);
        entry_values.put(COL8, rest);

        long mid = 0;

        try {
            mid = db.insertOrThrow(TABLE_NAME, null, entry_values);
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
    public Cursor getListContents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
