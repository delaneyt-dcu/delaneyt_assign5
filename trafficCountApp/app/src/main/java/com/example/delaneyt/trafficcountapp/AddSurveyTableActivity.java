package com.example.delaneyt.trafficcountapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Class which adds data of a survey to a database
 *
 * <p> This class allows the user add data to a SQLiteDatabase by calling a MySQLiteHelperClass
 *
 *  <p><b>References: </b>The origins of some of the code used in this class is accredited to
 *  ProgrammingKnowledge. Ref: Android SQLite Database Tutorial </p>
 *
 *  @author Tim Delaney
 *  @version 1.0
 *  @since 2016-04-20
 *  @see "Android SQLite Database Tutorial" by ProgrammingKnowledge at:
 *  @see <a href="https://www.youtube.com/watch?v=cp2rL3sAFmI"</a>
 */
public class AddSurveyTableActivity extends AppCompatActivity {
    MySQLiteHelperSurveyTable mySurveyTable;
    Button btnAddData, m_BtnViewAllData;
    String mSurveyDate, mStartTime, mEndTime, mWeather, mRoadCondition, mWeatherArray, mRoadArray;
    SharedPreferences mSurveyPref;

    /**
     * Public View method that inflates the layout with a variable values.
     * @param savedInstanceState which is a Bundle of instance states
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_add);
        btnAddData = (Button) findViewById(R.id.button_AddToDbTable);
        m_BtnViewAllData = (Button) findViewById(R.id.button_ViewData);
        mySurveyTable = new MySQLiteHelperSurveyTable(this);
        mSurveyPref = PreferenceManager.getDefaultSharedPreferences(AddSurveyTableActivity.this);
        mSurveyDate = mSurveyPref.getString("date_preference", "29/04/16");
        mStartTime = mSurveyPref.getString("start_time_preference", null);
        mEndTime = mSurveyPref.getString("end_time_preference", null);
        mWeather = mSurveyPref.getString("weather_preference", null);
        assert mWeather != null;

        // Assign certain string values to mWeatherArray based on array value stored after user section
        switch(mWeather){
            case "1": mWeatherArray = "Dry and sunny"; break;
            case "2": mWeatherArray = "Dry and overcast"; break;
            case "3": mWeatherArray = "Raining"; break;
            case "4": mWeatherArray = "Snowing"; break;
            case "5": mWeatherArray = "Frost"; break;
            case "6": mWeatherArray = "Other"; break;
            default: mWeatherArray = "Weather Unknown "; break;
        }
        mRoadCondition = mSurveyPref.getString("surface_preference", null);
        assert mRoadCondition != null;

        // Assign certain string values to mRoadArray based on array value stored after user section
        switch(mRoadCondition) {
            case "1": mRoadArray = "Wet"; break;
            case "2": mRoadArray = "Dry"; break;
            case "3": mRoadArray = "Snow"; break;
            case "4": mRoadArray = "Ice"; break;
            case "5": mRoadArray = "Leafs"; break;
            case "6": mRoadArray = "InNeedOfRepair"; break;
            case "7": mRoadArray = "Other"; break;
            default: mRoadArray = "Surface Unknown "; break;
        }

        // Call methods during onCreate
        addData();
        viewAll();
    }

    /**
     * Method to add the member variable values to the Database
     */
    public void addData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mySurveyTable.insertSurveyData(
                                mSurveyDate,
                                mStartTime,
                                mEndTime,
                                mWeatherArray,
                                mRoadArray);
                        if (isInserted)
                            Toast.makeText(AddSurveyTableActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else {
                                Toast.makeText(AddSurveyTableActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    /**
     * Method to create a message containing all of the data added to database when button clicked
     * Message returned.
     */
    public void viewAll() {
        m_BtnViewAllData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MySQLiteHelperSurveyTable myTrafficSurveysDb =
                                new MySQLiteHelperSurveyTable(AddSurveyTableActivity.this);
                        Cursor res = myTrafficSurveysDb.getAddedData();
                        if (res.getCount() == 0) {

                            // show message
                            showMessage("Error", "No data found");
                            return;
                        }
                        StringBuilder buffer = new StringBuilder();
                        while (res.moveToNext()) {
                            buffer.append("Survey ID: ").append(res.getString(0)).append("\n");
                            buffer.append("Survey Date: ").append(res.getString(1)).append("\n");
                            buffer.append("Start Time: ").append(res.getString(2)).append("\n");
                            buffer.append("End Time: ").append(res.getString(3)).append("\n");
                            buffer.append("Weather: ").append(res.getString(4)).append("\n");
                            buffer.append("Road Conditions: ").append(res.getString(5)).append("\n\n");
                        }

                        /**
                         * method which call the show message and passes the buffered data as a string
                         * return the string data
                         */
                        showMessage("Data", buffer.toString());
                    }
                });
    }

    /**
     * Method to call various builder methods used to show a message of the data added to the database
     * @param title is the String "Data"
     * @param Message is the String from a buffer
     * No return
     */
    public void showMessage (String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddSurveyTableActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Returns user to previous screen with up arrow set to act like a back arrow
     * @param item represent the menu item clicked, the up arrow in this case
     * @return boolean true if clicked to return user to previous screen
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

