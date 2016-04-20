package com.example.delaneyt.trafficcountapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by delaneyt on 13/03/2016.
 */
public class UserSettingsFrag extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    /**
     * Sets the screen's view including tabs and fragment. No return.
     *
     * @param savedInstanceState is a reference to a Bundle object that is passed into the onCreate
     * method of every Android Activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.userpreferences);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);


//        Preference button = (Preference)getPreferenceManager().findPreference("next_button");
 //       if (button != null) {
 //           button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
 //               @Override
 //               public boolean onPreferenceClick(Preference arg0) {
 //                   //finish();
//                    Intent intent = new Intent(getActivity(), LocationSettingsActivity.class);
//                    startActivity(intent);
//                    return true;
//                }
//            });
//        }

//        Preference button = (Preference)findPreference("next_button");
//        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
 //           @Override
 //           public boolean onPreferenceClick(Preference preference) {
//                //code for what you want it to do
//                Intent intent = new Intent(getActivity(), LocationSettingsActivity.class);
//                    startActivity(intent);
//                return true;
//            }
//        });


    }

    /**
     * Calls method to update preferences when a shared preference is changed, added, or removed.
     *
     * @param sharedPreferences that receives the change.
     * @param key represents the name of preference that was changed, added, or removed.
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePreference(findPreference(key));
    }
    public void onResume()
    {
        super.onResume();

        // Set up a listener whenever a key changes
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); ++i)
        {
            Preference preference = getPreferenceScreen().getPreference(i);
            if (preference instanceof PreferenceGroup)
            {
                PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
                for (int j = 0; j < preferenceGroup.getPreferenceCount(); ++j)
                {
                    updatePreference(preferenceGroup.getPreference(j));
                }
            } else
            {
                updatePreference(preference);
            }
        }
    }

    /**
     * Method used to saves the preference changes
     *
     * @param preference represents the new preference to be saved
     */
    private void updatePreference(Preference preference)
    {
        if (preference instanceof EditTextPreference)
        {
            EditTextPreference editTextPref = (EditTextPreference) preference;
            if (!preference.getKey().equalsIgnoreCase("password_preference"))
            {
                preference.setSummary(editTextPref.getText());
            }
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.preference_next_button, container, false);

//        Button button = (Button)view.findViewById(R.id.nextButton);
//        button.setOnClickListener(new View.OnClickListener()
 //       {
//            @Override
//            public void onClick(View v){
//                switch(v.getId()){

//                    case R.id.nextButton:
//                        Intent intent = new Intent(getActivity(), LocationSettingsActivity.class);
//                        startActivity(intent);
//                        break;
//                }
//            }
//        });

 //       return view;
//    }














}
