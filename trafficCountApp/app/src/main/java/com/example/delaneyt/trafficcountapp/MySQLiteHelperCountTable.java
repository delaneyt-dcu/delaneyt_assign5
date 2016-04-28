package com.example.delaneyt.trafficcountapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *  Class to handle all of the functions to do with a SQLiteDatabase table
 *
 *  <p> This class creates the Count_Data_Table within the Traffic_Surveys database. It
 *  upgrades the table if necessary. It adds traffic count data to a SQLite Database table. It
 *  also allows the user to get the data for viewing</p>
 *
 *  <p><b>References: </b>The origins of the code used in this class is accredited to ProgrammingKnowledge
 *  ref: Android SQLite Database Tutorial </p>
 *
 *  @author Tim Delaney
 *  @version 1.0
 *  @since 2016-04-20
 *  @see "Android SQLite Database Tutorial" by ProgrammingKnowledge at:
 *  @see <a href="https://www.youtube.com/watch?v=cp2rL3sAFmI"</a>
 */
public class MySQLiteHelperCountTable extends SQLiteOpenHelper {

    // Variables declared and initialised
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
    private static final int DATABASE_VERSION_COUNT= 28;

    /**
     * Method which set the context for this class
     * @param context containing the Db name and its revision version
     */
    public MySQLiteHelperCountTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_COUNT);
    }

    /**
     * Method called when the activity is first created.
     * Creates a countTable within SQLiteDatabase
     * @param countTable can be passed back to onCreate if the activity needs to be created
     *                           (e.g., orientation change) so that you don't lose this prior
     *                           information. If no data was supplied, savedInstanceState is null.
     */
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

    /**
     * Method which upgrades a table in a database
     * @param countTable is the name of the SQLiteDatabase table
     * @param oldVersion is the int value of the previous version created
     * @param newVersion is the int value of the new version to created
     * No return
     */
    @Override
    public void onUpgrade(SQLiteDatabase countTable, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelperCountTable.class.getName(), "Upgrading database from version " + oldVersion +
                " to " + newVersion + ", which will destroy all old data");
        countTable.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(countTable);
    }

    /**
     * Method which populates the Db table
     * @param AtoB_LGV String variable value
     * @param AtoB_HGV String variable value
     * @param AtoC_LGV String variable value
     * @param AtoC_HGV String variable value
     * @param AtoD_LGV String variable value
     * @param AtoD_HGV String variable value
     * @param BtoA_LGV String variable value
     * @param BtoA_HGV String variable value
     * @param BtoC_LGV String variable value
     * @param BtoC_HGV String variable value
     * @param BtoD_LGV String variable value
     * @param BtoD_HGV String variable value
     * @param CtoA_LGV String variable value
     * @param CtoA_HGV String variable value
     * @param CtoB_LGV String variable value
     * @param CtoB_HGV String variable value
     * @param CtoD_LGV String variable value
     * @param CtoD_HGV String variable value
     * @param DtoA_LGV String variable value
     * @param DtoA_HGV String variable value
     * @param DtoB_LGV String variable value
     * @param DtoB_HGV String variable value
     * @param DtoC_LGV String variable value
     * @param DtoC_HGV String variable value
     * @return boolean value used in confirmation showMessage
     */
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
        return result != -1;
    }

    /**
     * Method to get all the data within the Db table
     * @return the statement query used to select all(ie*) of the data from a Db table
     */
    public Cursor getAllData() {
        SQLiteDatabase countTable = this.getWritableDatabase();
        return countTable.rawQuery("select * from " + TABLE_NAME, null);
    }

    /**
     * Method to get the data last added within the Db table
     * @return the statement query used to select last added  data from a Db table
     */
    public Cursor getAddedData() {
        SQLiteDatabase mySurveyTable = this.getWritableDatabase();
        return mySurveyTable.rawQuery
                ("select * from " + TABLE_NAME + " where " + COL_1 + " = (select max(" + COL_1 + ") from " + TABLE_NAME + ")", null);
    }
}