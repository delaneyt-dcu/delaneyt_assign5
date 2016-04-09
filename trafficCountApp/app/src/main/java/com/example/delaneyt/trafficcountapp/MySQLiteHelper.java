package com.example.delaneyt.trafficcountapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Traffic_Surveys.db";
    public static final String TABLE_NAME = "Count_Data_Table";
    public static final String COL_1 = "Count_ID";
    public static final String COL_2 = "M1_LGV";
    public static final String COL_3 = "Movement1_HGV";
    public static final String COL_4 = "Movement2_LGV";
    public static final String COL_5 = "Movement2_HGV";
    public static final String COL_6 = "Movement3_LGV";
    public static final String COL_7 = "Movement3_HGV";
    public static final String COL_8 = "Movement4_LGV";
    public static final String COL_9 = "Movement4_HGV";
    public static final String COL_10 = "Movement5_LGV";
    public static final String COL_11 = "Movement5_HGV";
    public static final String COL_12 = "Movement6_LGV";
    public static final String COL_13 = "Movement6_HGV";
    private static final int DATABASE_VERSION = 2;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //    db.execSQL(DATABASE_CREATE);
        db.execSQL("create table "
                + TABLE_NAME + " (" + COL_1 + " integer primary key autoincrement," +
                COL_2 + " integer," +
                COL_3 + " integer," +
                COL_4 + " integer," +
                COL_5 + " integer," +
                COL_6 + " integer," +
                COL_7 + " integer," +
                COL_8 + " integer," +
                COL_9 + " integer," +
                COL_10 + " integer," +
                COL_11 + " integer," +
                COL_12 + " integer," +
                COL_13 + " integer" +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
/**
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + " (" + COL_1 + " integer primary key autoincrement," +
            COL_2 + " integer," +
            COL_3 + " integer," +
            COL_4 + " integer," +
            COL_5 + " integer," +
            COL_6 + " integer," +
            COL_7 + " integer," +
            COL_8 + " integer," +
            COL_9 + " integer," +
            COL_10 + " integer," +
            COL_11 + " integer," +
            COL_12 + " integer," +
            COL_13 + " integer" +")";
    */
    public boolean insertData(String M1_LGV, String Movement1_HGV, String Movement2_LGV,
                              String Movement2_HGV, String Movement3_LGV, String Movement3_HGV,
                              String Movement4_LGV, String Movement4_HGV, String Movement5_LGV,
                              String Movement5_HGV, String Movement6_LGV, String Movement6_HGV) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, M1_LGV);
        contentValues.put(COL_3, Movement1_HGV);
        contentValues.put(COL_4, Movement2_LGV);
        contentValues.put(COL_5, Movement2_HGV);
        contentValues.put(COL_6, Movement3_LGV);
        contentValues.put(COL_7, Movement3_HGV);
        contentValues.put(COL_8, Movement4_LGV);
        contentValues.put(COL_9, Movement4_HGV);
        contentValues.put(COL_10, Movement5_LGV);
        contentValues.put(COL_11, Movement5_HGV);
        contentValues.put(COL_12, Movement6_LGV);
        contentValues.put(COL_13, Movement6_HGV);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

 /**
public boolean insertData(String name, String surname, String marks) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COL_2, name);
    contentValues.put(COL_3, surname);
    contentValues.put(COL_4, marks);
    long result = db.insert(TABLE_NAME, null, contentValues);
    if (result == -1)
        return false;
    else
        return true;
}
    */

}