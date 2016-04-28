package com.example.delaneyt.trafficcountapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 *  Class which adds data of a junctions location to a database
 *
 *  <p> This class allows the user add data to a SQLiteDatabase by calling a MySQLiteHelperClass
 *
 *  <p><b>Credit is attributed to Colette Kirwan of DCU for some of the code used in this class</b></p>
 *
 *  <p><b>References: </b>The origins of some of the code used in this class is accredited to
 *  ProgrammingKnowledge. Ref: Android SQLite Database Tutorial </p>
 *
 *  @author Tim Delaney
 *  @version 1.0
 *  @since 2016-04-20
 *  @see "PhotIntentActivity" demo by Colette Kirwan availble on DCU's SDA github
 *  @see "Android SQLite Database Tutorial" by ProgrammingKnowledge at:
 *  @see <a href="https://www.youtube.com/watch?v=cp2rL3sAFmI"</a>
 */
public class AddJntTableActivity extends AppCompatActivity {
    MySQLiteHelperJunctionTable jntTable;
    Button btnAddData, m_BtnViewData;
    String townOrCityName, countyName, armAName, armBName, armCName, armDName;
    Double mGPSLat, mGPSLong;
    SharedPreferences mLocationPref;
    GPSTracker mGps;
    PhotoActivity mPhoto;
//    Bitmap mImgBitmap;
//    private byte[] img=null;
    Bitmap img;
    private Uri fileUri;

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
        jntTable = new MySQLiteHelperJunctionTable(this);
        mLocationPref = PreferenceManager.getDefaultSharedPreferences(AddJntTableActivity.this);
        townOrCityName = mLocationPref.getString("town_preference", null);
        countyName = mLocationPref.getString("county_preference", null);
        armAName = mLocationPref.getString("arm_a_preference", null);
        armBName = mLocationPref.getString("arm_b_preference", null);
        armCName = mLocationPref.getString("arm_c_preference", null);
        armDName = mLocationPref.getString("arm_d_preference", null);
        mGps = new GPSTracker(AddJntTableActivity.this);
        mGPSLat = mGps.getLatitude();
        mGPSLong = mGps.getLongitude();
        mPhoto = new PhotoActivity();
//        mImgBitmap = mPhoto.getImageForDb();
        btnAddData = (Button) findViewById(R.id.button_AddToDbTable);
        m_BtnViewData = (Button) findViewById(R.id.button_ViewData);
        Bitmap testImageBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        testImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

        // Call methods during onCreate
        addData();
        viewAll();
    }

    /**
     * Method to retrieve image from its imageView in xml as a Bitmap variable
     * @param requestCode is a int variable
     * @param resultCode is a int variable
     * @param intent is an Intent variable
     */
    protected void onActivityResult(int requestCode, int resultCode,Intent intent) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                if (intent == null) {

                    // The picture was taken but not returned
                    Toast.makeText(
                            getApplicationContext(),
                            "The picture was taken and is located here: "
                                    + fileUri.toString(), Toast.LENGTH_LONG)
                            .show();
                } else {

                    // The picture was returned
                    Bundle extras = intent.getExtras();
                    img=(Bitmap) extras.get("data");
                    ImageView imageView1 = (ImageView) findViewById(R.id.imageViewOfPhoto);
                    imageView1.setImageBitmap((Bitmap) extras.get("data"));
                }
            }
        }
    }

    /**
     * Method to add the member variable values to the Database
     */
    public void addData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = jntTable.insertJntData(
                                townOrCityName,
                                countyName,
                                armAName,
                                armBName,
                                armCName,
                                armDName,
                                String.valueOf(mGPSLat),
                                String.valueOf(mGPSLong),
                                String.valueOf(img));
                        if (isInserted)
                            Toast.makeText(AddJntTableActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddJntTableActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
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
                        MySQLiteHelperJunctionTable myTrafficSurveysDb = new MySQLiteHelperJunctionTable(AddJntTableActivity.this);
                        Cursor res = myTrafficSurveysDb.getAddedData();
                        if (res.getCount() == 0) {

                            // show message
                            showMessage("Error", "No data found");
                            return;
                        }
                        StringBuilder buffer = new StringBuilder();
                        while (res.moveToNext()) {
                            buffer.append("Junction_Id: ").append(res.getString(0)).append("\n");
                            buffer.append("Town_City: ").append(res.getString(1)).append("\n");
                            buffer.append("County: ").append(res.getString(2)).append("\nJunction Arm Names:\n");
                            buffer.append("ArmA:: ").append(res.getString(3)).append("\n");
                            buffer.append("ArmB: ").append(res.getString(4)).append("\n");
                            buffer.append("ArmC: ").append(res.getString(5)).append("\n");
                            buffer.append("ArmD: ").append(res.getString(6)).append("\n");
                            buffer.append("GPS_Lat: ").append(res.getString(7)).append("\n");
                            buffer.append("GPS_Long: ").append(res.getString(8)).append("\n");
                            buffer.append("Photo: ").append(res.getBlob(9)).append("\n\n");
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AddJntTableActivity.this);
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
