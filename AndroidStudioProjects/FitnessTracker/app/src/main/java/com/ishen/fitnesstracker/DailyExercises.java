package com.ishen.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by WingsOfRetribution on 2017-09-17.
 */

public class DailyExercises extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "daily_activity.db";
    public static final String TABLE_NAME = "activity_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "BOX1";
    public static final String COL4 = "BOX2";

    public DailyExercises(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " +
                TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT," + " BOX1 TEXT," + " BOX2 TEXT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // storing values in db
    public boolean add_Data(String name, String box1, String box2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(COL2, name);
        content_values.put(COL3, box1);
        content_values.put(COL4, box2);

        //long result = db.insert(TABLE_NAME, null, content_values);

        long mid = 0;

        try {
            mid = db.insertOrThrow(TABLE_NAME, null, content_values);
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
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
