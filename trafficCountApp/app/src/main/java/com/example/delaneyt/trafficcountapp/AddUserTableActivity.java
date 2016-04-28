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
 * Class which adds data of a user to a database
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
public class AddUserTableActivity extends AppCompatActivity {
    MySQLiteHelperUserTable userTable;
    Button btnAddData, m_BtnViewData;
    String mEmployeeNo, mName, mGrade, mEmpGradeArray, email;
    Boolean mDirectEmp;
    SharedPreferences mUserPref;

    /**
     * Called when the activity is first created.
     * Saves the state of the application in a bundle based on the value of the savedInstance State
     * and carries out button intent actions.
     *
     * @param savedInstanceState can be passed back to onCreate if the activity needs to be created
     *                           (e.g., orientation change) so that you don't lose this prior
     *                           information. If no data was supplied, savedInstanceState is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_add);
        btnAddData = (Button) findViewById(R.id.button_AddToDbTable);
        m_BtnViewData = (Button) findViewById(R.id.button_ViewData);
        mUserPref = PreferenceManager.getDefaultSharedPreferences(AddUserTableActivity.this);
        email = mUserPref.getString("email_preference", null);
        mDirectEmp = mUserPref.getBoolean("employee_member_preference", true);
        userTable = new MySQLiteHelperUserTable(this);
        mEmployeeNo = mUserPref.getString("employee_number_preference", null);
        mName = mUserPref.getString("username_preference", null);
        mGrade = mUserPref.getString("employment_grade_preference", null);
        assert mGrade != null;

        // Assign certain string values to mEmpGradeArray based on array value stored after user section
        switch(mGrade){
            case "1": mEmpGradeArray = "Director"; break;
            case "2": mEmpGradeArray = "Associate Director"; break;
            case "3": mEmpGradeArray = "Associate"; break;
            case "4": mEmpGradeArray = "Team Leader"; break;
            case "5": mEmpGradeArray = "Project Manager"; break;
            case "6": mEmpGradeArray = "Senior Engineer"; break;
            case "7": mEmpGradeArray = "Design Engineer"; break;
            case "8": mEmpGradeArray = "Graduate Engineer"; break;
            case "9": mEmpGradeArray = "Engineering Tech"; break;
            case "10": mEmpGradeArray = "CAD Tech"; break;
            case "11": mEmpGradeArray = "Other"; break;
            default: mEmpGradeArray = "Unknown Grade"; break;
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
                        boolean isInserted = userTable.insertUserData(
                                mEmployeeNo,
                                mName,
                                mEmpGradeArray,
                                email,
                                String.valueOf(mDirectEmp));
                        if (isInserted)
                            Toast.makeText(AddUserTableActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else {
                            if (mEmployeeNo==null){
                                Toast.makeText(AddUserTableActivity.this, "Data not Inserted, \nEmployee No. must be entered", Toast.LENGTH_LONG).show();
                            }
                            else
                            Toast.makeText(AddUserTableActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
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
        m_BtnViewData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MySQLiteHelperUserTable myTrafficSurveysDb =
                                new MySQLiteHelperUserTable(AddUserTableActivity.this);
                        Cursor res = myTrafficSurveysDb.getAllData();
                        if (res.getCount() == 0) {

                            // show message
                            showMessage("Error", "No data found");
                            return;
                        }
                        StringBuilder buffer = new StringBuilder();
                        while (res.moveToNext()) {
                            buffer.append("Employee No.: ").append(res.getString(0)).append("\n");
                            buffer.append("Name: ").append(res.getString(1)).append("\n");
                            buffer.append("Grade: ").append(res.getString(2)).append("\n");
                            buffer.append("Email:: ").append(res.getString(3)).append("\n");
                            buffer.append("Direct Employee: ").append(res.getString(4)).append("\n\n");
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AddUserTableActivity.this);
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
