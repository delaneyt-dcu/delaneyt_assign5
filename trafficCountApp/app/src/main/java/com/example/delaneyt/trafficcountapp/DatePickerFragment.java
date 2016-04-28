package com.example.delaneyt.trafficcountapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Class which extends DialogFragment and is used to set a date selected by user.
 * Created by delaneyt on 23/04/2016.
 * @see "Creating a Date Picker" by developer.android.com at:
 * @see <a href="http://developer.android.com/guide/topics/ui/controls/pickers.html"</a>
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
    }
}