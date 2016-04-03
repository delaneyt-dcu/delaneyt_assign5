package com.example.delaneyt.trafficcountapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by delaneyt on 18/03/2016.
 */
public class LocationSettingsActivity extends PreferenceActivity {
    //AppCompatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Get the Intent used to start this Activity
        //Intent intent = getIntent();

        /**
            addPreferencesFromResource(R.xml.locationpreferences);

            Preference button = (Preference)getPreferenceManager().findPreference("next_button");
            if (button != null) {
                button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        //finish();
                        //setContentView(R.layout.activity_main);
                        getFragmentManager().beginTransaction()
                                .replace(android.R.id.content,
                                        new LocationSettingsFrag()).commit();
                        return true;
                    }
                });
            }
         */


 //       Preference button = (Preference)findPreference("next_button");
//        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
 //           @Override
 //           public boolean onPreferenceClick(Preference preference) {
                //code for what you want it to do
                //setContentView(R.layout.activity_main);
//                getFragmentManager().beginTransaction()
//                        .replace(android.R.id.content,
//                                new LocationSettingsFrag()).commit();
//                return true;
//            }
//        });













 //setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content,
                        new LocationSettingsFrag()).commit();

    }

    public static class  LocationSettingsFrag extends PreferenceFragment {
        @Override
        public void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.locationpreferences);
        }
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