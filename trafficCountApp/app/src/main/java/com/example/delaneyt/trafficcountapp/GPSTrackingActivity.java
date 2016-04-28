package com.example.delaneyt.trafficcountapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 *  Finds the GPS coordinates of the user
 *
 *  <p> This class uses the GPSTracker class and will set and show the users GPS location
 *  which are used and saved to a database within the AddJntTableActivity</p>
 *
 *  <p><b>References: </b>The origins of the code used in this class is accredited to Ravi Tamada
 *  ref: Android GPS, Location Manager Tutorial </p>
 *
 *  @author Tim Delaney
 *  @version 1.0
 *  @since 2016-04-20
 *  @see "Android GPS, Location Manager Tutorial" by Ravi Tamada at:
 *  @see <a href="http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/"</a>
 */
public class GPSTrackingActivity extends AppCompatActivity {
    Button btnShowLocation;

    // GPSTracker class
    GPSTracker gps;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(GPSTrackingActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{

                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
            }
        });
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