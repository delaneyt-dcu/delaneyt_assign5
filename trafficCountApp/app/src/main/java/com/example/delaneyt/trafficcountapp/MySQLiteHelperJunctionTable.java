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
 *  <p> This class creates the Junction_Table within the Traffic_Surveys database. It
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
public class MySQLiteHelperJunctionTable extends SQLiteOpenHelper {

    // Variables declared and initialised
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
    private static final int DATABASE_VERSION_JUNCTION = 28;

    /**
     * Method which set the context for this class
     * @param context containing the Db name and its revision version
     */
    public MySQLiteHelperJunctionTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_JUNCTION);
    }

    /**
     * Called when the activity is first created.
     * Creates a myJntTable within SQLiteDatabase
     * @param myJntTable can be passed back to onCreate if the activity needs to be created
     *                           (e.g., orientation change) so that you don't lose this prior
     *                           information. If no data was supplied, savedInstanceState is null.
     */
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

    /**
     * Method which upgrades a table in a database
     * @param myJntTable is the name of the SQLiteDatabase table
     * @param oldVersion is the int value of the previous version created
     * @param newVersion is the int value of the new version to created
     * No return
     */
    @Override
    public void onUpgrade(SQLiteDatabase myJntTable, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelperCountTable.class.getName(), "Upgrading database from version " + oldVersion +
                " to " + newVersion + ", which will destroy all old data");
        myJntTable.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(myJntTable);
    }

    /**
     * Method which populates the Db table
     * @param city_town String variable value
     * @param county String variable value
     * @param armA_Name String variable value
     * @param armB_Name String variable value
     * @param armC_Name String variable value
     * @param armD_Name String variable value
     * @param gpsLat String variable value
     * @param gpsLong String variable value
     * @param photo String variable value
     * @return  boolean value used in confirmation showMessage
     */
    public boolean insertJntData(String city_town, String county, String armA_Name, String armB_Name,
                              String armC_Name, String armD_Name, String gpsLat, String gpsLong,
                              String photo) {
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
        return result != -1;
    }

    /**
     * Method to get the data last added within the Db table
     * @return the statement query used to select last added  data from a Db table
     */
    public Cursor getAddedData() {
        SQLiteDatabase myJntTable = this.getWritableDatabase();
        return myJntTable.rawQuery
                ("select * from " + TABLE_NAME + " where " + COL_1 + " = (select max(" + COL_1 + ") from " + TABLE_NAME + ")", null);
    }
}
