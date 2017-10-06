package com.ishen.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by WingsOfRetribution on 2017-10-05.
 */

public class HistoryDatabase extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "history.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "history_lst";
    public static final String COL1 = "ID";
    public static final String COL2 = "DATE";
    public static final String COL3 = "WORKOUT";

    public HistoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " +
                TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " DATE STRING," + " WORKOUT STRING);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldvers, int newvers) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean add_workout(String date, String workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues new_workout = new ContentValues();
        new_workout.put(COL2, date);
        new_workout.put(COL3, workout);

        long mid = 0;

        try {
            mid = db.insertOrThrow(TABLE_NAME, null, new_workout);
        }
        catch(SQLException e) {
            Log.e("Exception", "SQLException" + String.valueOf(e.getMessage()));
            e.printStackTrace();
        }

        if (mid==-1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getWorkoutContents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
