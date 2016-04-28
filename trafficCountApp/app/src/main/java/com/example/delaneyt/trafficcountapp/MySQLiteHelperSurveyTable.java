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
 *  <p> This class creates the Survey_Table within the Traffic_Surveys database. It
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
public class MySQLiteHelperSurveyTable extends SQLiteOpenHelper {

    // Variables declared and initialised
    private static final String DATABASE_NAME = "Traffic_Surveys.db";
    public static final String TABLE_NAME = "Survey_Table";
    public static final String COL_1 = "Survey_ID";
    public static final String COL_2 = "Survey_Date";
    public static final String COL_3 = "Start_Time";
    public static final String COL_4 = "End_Time";
    public static final String COL_5 = "Weather";
    public static final String COL_6 = "Surface";
    private static final int DATABASE_VERSION_SURVEY = 28;

    /**
     * Method which set the context for this class
     * @param context containing the Db name and its revision version
     */
    public MySQLiteHelperSurveyTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_SURVEY);
    }

    /**
     * Called when the activity is first created.
     * Creates a mySurveyTable within SQLiteDatabase
     * @param mySurveyTable can be passed back to onCreate if the activity needs to be created
     *                           (e.g., orientation change) so that you don't lose this prior
     *                           information. If no data was supplied, savedInstanceState is null.
     */
    @Override
    public void onCreate(SQLiteDatabase mySurveyTable) {
        mySurveyTable.execSQL("create table "
                + TABLE_NAME + " (" + COL_1 + " integer primary key autoincrement," +
                COL_2 + " text," +
                COL_3 + " text," +
                COL_4 + " text," +
                COL_5 + " text," +
                COL_6 + " text" + ")");
    }

    /**
     * Method which upgrades a table in a database
     * @param mySurveyTable is the name of the SQLiteDatabase table
     * @param oldVersion is the int value of the previous version created
     * @param newVersion is the int value of the new version to created
     * No return
     */
    @Override
    public void onUpgrade(SQLiteDatabase mySurveyTable, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelperSurveyTable.class.getName(), "Upgrading database from version " + oldVersion +
                " to " + newVersion + ", which will destroy all old data");
        mySurveyTable.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(mySurveyTable);
    }

    /**
     * Method which populates the Db table
     * @param date String variable value
     * @param start String variable value
     * @param end String variable value
     * @param weather String variable value
     * @param surface String variable value
     * @return boolean value used in confirmation showMessage
     */
    public boolean insertSurveyData(String date, String start,
                                  String end, String weather, String surface)
    {
        SQLiteDatabase mySurveyTable = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, start);
        contentValues.put(COL_4, end);
        contentValues.put(COL_5, weather);
        contentValues.put(COL_6, surface);
        long result = mySurveyTable.insert(TABLE_NAME, null, contentValues);
        return result != -1;
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

