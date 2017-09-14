package com.ishen.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ishen on 2017-09-14.
 */

public class ActivityDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "activity_lst.db";
    public static final String TABLE_NAME = "activity_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";

    public ActivityDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE" +
                TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ITEM1 TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
    }

    // storing values in db
    public boolean addData(String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
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
