package com.example.delaneyt.trafficcountapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.Arrays;

/**
 * Created by delaneyt on 17/04/2016.
 */
public class DbJntAddActivity extends ActionBarActivity {
    MySQLiteHelperJunctionTable jntTable;
    Button btnAddData, m_BtnViewAllData;
    String townOrCityName, countyName, armAName, armBName, armCName, armDName;
    Double mGPSLat, mGPSLong;
    Blob sitePhoto;
    SharedPreferences pref;
    GPSTracker mGps;
    PhotoActivity mPhoto;
    byte[] image;
    Bitmap mImgBitmap;
    byte[] mPhotoByteArray;
    private byte[] img=null;

    //   public void insertImg(int id , Bitmap mImgBitmap ) {
//    byte[]   mPhotoByteArray = getBitmapAsByteArray(mImgBitmap); // this is a function
//        insertStatement_logo.bindLong(1, id);
//        insertStatement_logo.bindBlob(2, data);
//        insertStatement_logo.executeInsert();
//        insertStatement_logo.clearBindings() ;
//    }

//    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
//        return outputStream.toByteArray();
//    }

//    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
//        return stream.toByteArray();
//    }










//    public static final String PREFS_KEY = "town_preference";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_location_add);

        jntTable = new MySQLiteHelperJunctionTable(this);

        pref = PreferenceManager.getDefaultSharedPreferences(DbJntAddActivity.this);
        townOrCityName = pref.getString("town_preference", null);
        countyName = pref.getString("county_preference", null);
        armAName = pref.getString("arm_a_preference", null);
        armBName = pref.getString("arm_b_preference", null);
        armCName = pref.getString("arm_c_preference", null);
        armDName = pref.getString("arm_d_preference", null);
        mGps = new GPSTracker(DbJntAddActivity.this);
        mGPSLat = mGps.getLatitude();
        mGPSLong = mGps.getLongitude();

        mPhoto = new PhotoActivity();
//        mImgBitmap = mPhoto.mImageBitmap;
        mImgBitmap = mPhoto.getImageForDb();

//        mPhotoByteArray = getBitmapAsByteArray(mImgBitmap); // this is a function

        Bitmap testImageBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
//        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        testImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
//        db=mdb.getWritableDatabase();

//        sitePhoto = pref.getBlob("town_preference", null);

        btnAddData = (Button) findViewById(R.id.button_AddToJntTable);
        m_BtnViewAllData = (Button) findViewById(R.id.button_ViewAll);
        addData();
        viewAll();
    }

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
                                Arrays.toString(img));
                        if (isInserted == true)
                            Toast.makeText(DbJntAddActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DbJntAddActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



    public void viewAll() {
        m_BtnViewAllData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MySQLiteHelperJunctionTable myTrafficSurveysDb = new MySQLiteHelperJunctionTable(DbJntAddActivity.this);
                        Cursor res = myTrafficSurveysDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "No data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Junction_Id: " + res.getString(0) + "\n");
                            buffer.append("Town_City: " + res.getString(1) + "\n");
                            buffer.append("County: " + res.getString(2) + "\nJunction Arm Names:\n");
                            buffer.append("ArmA:: " + res.getString(3) + "\n");
                            buffer.append("ArmB: " + res.getString(4) + "\n");
                            buffer.append("ArmC: " + res.getString(5) + "\n");
                            buffer.append("ArmD: " + res.getString(6) + "\n");
                            buffer.append("GPS_Lat: " + res.getString(7) + "\n");
                            buffer.append("GPS_Long: " + res.getString(8) + "\n");
                            buffer.append("Photo: " + res.getBlob(9) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                        return;
                    }
                });
    }

    public void showMessage (String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DbJntAddActivity.this);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
