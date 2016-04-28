package com.example.delaneyt.trafficcountapp;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.widget.DatePicker;
import java.util.Calendar;

/**
 *  Class which stores a series of Shared Survey Preferences
 *
 *  <p> This class holds values that can be used within the AddSurveyTableActivity</p>
 *
 *  Created by delaneyt on 18/04/2016.
 *  @see "Creating a Date Picker" by developer.android.com at:
 *  @see <a href="http://developer.android.com/guide/topics/ui/controls/pickers.html"</a>
 */
public class SurveySettingsFrag extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {

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

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.surveypreferences);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        Preference datePreference = findPreference("date_preference");
        datePreference.setOnPreferenceClickListener(this);
}

    @Override
    public boolean onPreferenceClick(Preference datePreference) {
        String key = datePreference.getKey();
        if(key.equalsIgnoreCase("date_preference")){
            showDatePicker();
        }
        return false;
    }

    protected void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();

        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);

        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");

    }
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String dob = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear)+ "/" + String.valueOf(year);

        }
    };

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
}
