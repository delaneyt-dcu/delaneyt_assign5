package com.example.delaneyt.trafficcountapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelperJunctionTable extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Traffic_Surveys.db";
    public static final String TABLE_NAME = "Junction_Table";
    public static final String COL_1 = "Junction_ID";
    public static final String COL_2 = "CityOrTown";
    public static final String COL_3 = "County";
    public static final String COL_4 = "ArmA_Name";
    public static final String COL_5 = "ArmB_Name";
    public static final String COL_6 = "ArmC_Name";
    public static final String COL_7 = "ArmD_Name";
    public static final String COL_8 = "GPS_Latitude";
    public static final String COL_9 = "GPS_Longitude";
    public static final String COL_10 = "Jnt_Photo";
    private static final int DATABASE_VERSION = 12;


    public MySQLiteHelperJunctionTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase myJntTable) {
        myJntTable.execSQL("create table "
                + TABLE_NAME + " (" + COL_1 + " integer primary key autoincrement," +
                COL_2 + " text," +
                COL_3 + " text," +
                COL_4 + " text," +
                COL_5 + " text," +
                COL_6 + " text," +
                COL_7 + " text," +
                COL_8 + " real," +
                COL_9 + " real," +
                COL_10 + " blob" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myJntTable, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelperCountTable.class.getName(), "Upgrading database from version " + oldVersion +
                " to " + newVersion + ", which will destroy all old data");
        myJntTable.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(myJntTable);
    }

    public boolean insertJntData(String city_town, String county, String armA_Name, String armB_Name,
                              String armC_Name, String armD_Name, String gpsLat, String gpsLong,
                              String photo)
    {
        SQLiteDatabase myJntTable = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, city_town);
        contentValues.put(COL_3, county);
        contentValues.put(COL_4, armA_Name);
        contentValues.put(COL_5, armB_Name);
        contentValues.put(COL_6, armC_Name);
        contentValues.put(COL_7, armD_Name);
        contentValues.put(COL_8, gpsLat);
        contentValues.put(COL_9, gpsLong);
        contentValues.put(COL_10, photo);
        long result = myJntTable.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase myJntTable = this.getWritableDatabase();
        Cursor res = myJntTable.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}