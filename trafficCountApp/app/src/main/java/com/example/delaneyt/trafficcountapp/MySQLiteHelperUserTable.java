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
 *  <p> This class creates the User_Table within the Traffic_Surveys database. It
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
public class MySQLiteHelperUserTable extends SQLiteOpenHelper {

    // Variables declared and initialised
    private static final String DATABASE_NAME = "Traffic_Surveys.db";
    public static final String TABLE_NAME = "User_Table";
    public static final String COL_1 = "Employee_No";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Grade";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "Direct_Emp";
    private static final int DATABASE_VERSION_USER = 28;

    /**
     * Method which set the context for this class
     * @param context containing the Db name and its revision version
     */
    public MySQLiteHelperUserTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_USER);
    }

    /**
     * Called when the activity is first created.
     * Creates a myUserTable within SQLiteDatabase
     * @param myUserTable can be passed back to onCreate if the activity needs to be created
     *                           (e.g., orientation change) so that you don't lose this prior
     *                           information. If no data was supplied, savedInstanceState is null.
     */
    @Override
    public void onCreate(SQLiteDatabase myUserTable) {
        myUserTable.execSQL("create table "
                + TABLE_NAME + " (" + COL_1 + " integer primary key," +
                COL_2 + " text," +
                COL_3 + " text," +
                COL_4 + " text," +
                COL_5 + " text" + ")");
    }

    /**
     * Method which upgrades a table in a database
     * @param myUserTable is the name of the SQLiteDatabase table
     * @param oldVersion is the int value of the previous version created
     * @param newVersion is the int value of the new version to created
     * No return
     */
    @Override
    public void onUpgrade(SQLiteDatabase myUserTable, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelperCountTable.class.getName(), "Upgrading database from version " + oldVersion +
                " to " + newVersion + ", which will destroy all old data");
        myUserTable.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(myUserTable);
    }

    /**
     * Method which populates the Db table
     * @param employee_no String variable value
     * @param name String variable value
     * @param grade String variable value
     * @param email String variable value
     * @param dir_emp String variable value
     * @return boolean value used in confirmation showMessage
     */
    public boolean insertUserData(String employee_no, String name, String grade, String email, String dir_emp)
    {
        SQLiteDatabase myUserTable = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, employee_no);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, grade);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, dir_emp);
        long result = myUserTable.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    /**
     * Method to get all the data within the Db table
     * @return the statement query used to select all(ie*) of the data from a Db table
     */
    public Cursor getAllData() {
        SQLiteDatabase myUserTable = this.getWritableDatabase();
        return myUserTable.rawQuery("select * from " + TABLE_NAME, null);
    }
}
