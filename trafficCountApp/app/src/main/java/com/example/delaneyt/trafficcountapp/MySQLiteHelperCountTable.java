package com.example.delaneyt.trafficcountapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelperCountTable extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Traffic_Surveys.db";
    public static final String TABLE_NAME = "Count_Data_Table";
    public static final String COL_1 = "Count_ID";
    public static final String COL_2 = "AtoB_LGV";
    public static final String COL_3 = "AtoB_HGV";
    public static final String COL_4 = "AtoC_LGV";
    public static final String COL_5 = "AtoC_HGV";
    public static final String COL_6 = "AtoD_LGV";
    public static final String COL_7 = "AtoD_HGV";
    public static final String COL_8 = "BtoA_LGV";
    public static final String COL_9 = "BtoA_HGV";
    public static final String COL_10 = "BtoC_LGV";
    public static final String COL_11 = "BtoC_HGV";
    public static final String COL_12 = "BtoD_LGV";
    public static final String COL_13 = "BtoD_HGV";
    public static final String COL_14 = "CtoA_LGV";
    public static final String COL_15 = "CtoA_HGV";
    public static final String COL_16 = "CtoB_LGV";
    public static final String COL_17 = "CtoB_HGV";
    public static final String COL_18 = "CtoD_LGV";
    public static final String COL_19 = "CtoD_HGV";
    public static final String COL_20 = "DtoA_LGV";
    public static final String COL_21 = "DtoA_HGV";
    public static final String COL_22 = "DtoB_LGV";
    public static final String COL_23 = "DtoB_HGV";
    public static final String COL_24 = "DtoC_LGV";
    public static final String COL_25 = "DtoC_HGV";
    private static final int DATABASE_VERSION = 12;

    public MySQLiteHelperCountTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase countTable) {
        countTable.execSQL("create table "
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
                COL_13 + " integer," +
                COL_14 + " integer," +
                COL_15 + " integer," +
                COL_16 + " integer," +
                COL_17 + " integer," +
                COL_18 + " integer," +
                COL_19 + " integer," +
                COL_20 + " integer," +
                COL_21 + " integer," +
                COL_22 + " integer," +
                COL_23 + " integer," +
                COL_24 + " integer," +
                COL_25 + " integer" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase countTable, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelperCountTable.class.getName(), "Upgrading database from version " + oldVersion +
                " to " + newVersion + ", which will destroy all old data");
        countTable.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(countTable);
    }

    public boolean insertData(String AtoB_LGV, String AtoB_HGV, String AtoC_LGV, String AtoC_HGV,
                              String AtoD_LGV, String AtoD_HGV, String BtoA_LGV, String BtoA_HGV,
                              String BtoC_LGV, String BtoC_HGV, String BtoD_LGV, String BtoD_HGV,
                              String CtoA_LGV, String CtoA_HGV, String CtoB_LGV, String CtoB_HGV,
                              String CtoD_LGV, String CtoD_HGV, String DtoA_LGV, String DtoA_HGV,
                              String DtoB_LGV, String DtoB_HGV, String DtoC_LGV, String DtoC_HGV)
    {
        SQLiteDatabase countTable = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, AtoB_LGV);
        contentValues.put(COL_3, AtoB_HGV);
        contentValues.put(COL_4, AtoC_LGV);
        contentValues.put(COL_5, AtoC_HGV);
        contentValues.put(COL_6, AtoD_LGV);
        contentValues.put(COL_7, AtoD_HGV);
        contentValues.put(COL_8, BtoA_LGV);
        contentValues.put(COL_9, BtoA_HGV);
        contentValues.put(COL_10, BtoC_LGV);
        contentValues.put(COL_11, BtoC_HGV);
        contentValues.put(COL_12, BtoD_LGV);
        contentValues.put(COL_13, BtoD_HGV);
        contentValues.put(COL_14, CtoA_LGV);
        contentValues.put(COL_15, CtoA_HGV);
        contentValues.put(COL_16, CtoB_LGV);
        contentValues.put(COL_17, CtoB_HGV);
        contentValues.put(COL_18, CtoD_LGV);
        contentValues.put(COL_19, CtoD_HGV);
        contentValues.put(COL_20, DtoA_LGV);
        contentValues.put(COL_21, DtoA_HGV);
        contentValues.put(COL_22, DtoB_LGV);
        contentValues.put(COL_23, DtoB_HGV);
        contentValues.put(COL_24, DtoC_LGV);
        contentValues.put(COL_25, DtoC_HGV);
        long result = countTable.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase countTable = this.getWritableDatabase();
        Cursor res = countTable.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}