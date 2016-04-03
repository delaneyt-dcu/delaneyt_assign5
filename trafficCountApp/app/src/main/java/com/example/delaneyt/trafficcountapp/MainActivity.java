package com.example.delaneyt.trafficcountapp;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Calendar;
import com.example.delaneyt.trafficcountapp.view.*;

/**
 *
 * <h1>TDAssignFour is a School App</h1> used for sharing info such as Events, Pics, a Rugby Match
 * tool and saves user settings
 *
 * <p>
 * The App contains 2 activities. This MainActivity class contains the launch activity and inflates
 * the Match Fragment upon launchThe launch activity has 4 tabs each displaying a fragment. An Art
 * activity is used to enlarge a view of a thumbprint using a ImageAdapter.</p>
 *
 * @author Tim Delaney
 * @version 2.0
 * @since 2016-01-20
 * @see "TabFragPager" demo by Colette Kirwan availble on DCU's SDA github
 * @see "UIGridLayout" demo by Adam Porter avauilable at:
 * @see <a href="http://developer.android.com/guide/topics/ui/layout/gridview.html"</a>
 */
public class MainActivity extends android.support.v7.app.AppCompatActivity {

    //Debug Tag for use logging debug output to LogCat
    private final String TAG = "MainActivity";

    /**
     * Return an intent to use the devices calendar containing specific events details.
     * This method does not startActivity(mCalendarIntent), done by the various calling methods
     * Note: Event Title and Location may be changed within r.values.strings
     *
     * @return mCalendarIntent set with parameters
     */
    public Intent addCalendarEvent() {
        Context context = getApplicationContext();

        // Set event's date and time here, (YYYY, M, D, H, Min)
        // Note: Jan = 0, Feb = 1, etc
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2016, 1, 19, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2016, 1, 19, 8, 30);

        //Implicit intent to open system's calendar
        Intent mCalendarIntent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, context.getString(R.string.events_title))
                .putExtra(CalendarContract.Events.EVENT_LOCATION, context.getString(R.string.event_location));
        mCalendarIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //Tag marker for this activity
        Log.i(TAG, "addCalendarEvent called and mCalendarIntent about to be returned");
        return mCalendarIntent;
    }

    /**
     * Notification method to build the notification message and format
     *
     * @param pendingIntent passed from a PendingIntend method
     * @return NotificationCompat.Builder needed to build the notification event message
     */
    public NotificationCompat.Builder addNotifcationEvent(PendingIntent pendingIntent) {
        Context context = getApplicationContext();

        //Tag marker for this activity
        Log.i(TAG, "addNotifcationEvent called and builder about to be returned");

        // Set the notification parameters and return to caller
        return new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_event_white_24dp)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentIntent(pendingIntent)
                .setContentText(context.getString(R.string.notification_text))
                .setAutoCancel(true)

                        // giving it the bells and whistles
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
    }

    // declaring all of the necessary variables
    protected SlidingTabLayout slidingTabLayout;
    protected ViewPager viewPager;
    protected ArrayList<Fragment> fragments;
    protected ActionTabsViewPagerAdapter myViewPageAdapter;
    protected android.support.v4.app.Fragment counterA;
    protected android.support.v4.app.Fragment counterB;

    /**
     * Method override called when the activity is first created.
     * Sets the screen view including tabs and fragment. No return.
     *
     * @param savedInstanceState is a reference to a Bundle object that is passed into the onCreate
     * method of every Android Activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            counterA = getSupportFragmentManager().getFragment(
                    savedInstanceState, "Team_A");
            counterB = getSupportFragmentManager().getFragment(
                    savedInstanceState, "Team_B");
        }
        setContentView(R.layout.activity_main);

        // Define SlidingTabLayout (shown at top) and ViewPager (shown at bottom) in the layout.
        // Getting their instances.
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // creates a fragment list in order.
        fragments = new ArrayList<>();
        fragments.add(new UserSettingsFrag());
        fragments.add(new LocationSettingsFrag());
        fragments.add(new SurveySettingsFrag());
        fragments.add(new JntTypeSelectFragment());



        // use FragmentPagerAdapter to bind the slidingTabLayout (tabs with different titles)
        // and ViewPager (different pages of fragment) together.
        myViewPageAdapter =new ActionTabsViewPagerAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(myViewPageAdapter);

        // make sure the tabs are equally spaced.
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
    }

    /**
     * Method override called when the activity is first created.
     * Inflates the menu tab view
     *
     * @param menu options are passed into the method
     * @return boolean true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Method called upon a menu item clicked
     * which starts an activity or action
     *
     * @param item refers to either the notify or event items clicked by user
     * @return boolean true if item clicked or false otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuitem_notify:

                // NOTIF_ID can be any 'unique' integer
                final int NOTIF_ID = 9876;
                Context context = getApplicationContext();

                // Intend to call myEventCalendarIntent method which need (pendingIntent)
                Intent myNotificationCalendarIntent= addCalendarEvent();

                // PendingIntent to hold myCalendarIntent parameters for notification;
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, myNotificationCalendarIntent, 0);
                NotificationCompat.Builder mBuilder= addNotifcationEvent(pendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                // NOTIF_ID allows you to update the notification later on.
                mNotificationManager.notify(NOTIF_ID, mBuilder.build());

                // Remove '//' below to cancel notification whilst keeping code for future use
                // mNotificationManager.cancel(NOTIF_ID);

                // Tag marker for this activity
                Log.i(TAG, "addCalendarEvent and addNotifcationEvent methods and mNotificationManager worked following Notify menu tab clicked.");
                return true;

            case R.id.menuitem_event:

                // Call myEventCalendarIntent method and then start activity on click
                Intent myEventCalendarIntent= addCalendarEvent();
                startActivity(myEventCalendarIntent);

                // Tag marker for this activity
                Log.i(TAG, "addCalendarEvent method and myEventCalendarIntent worked following Event menu tab clicked.");
                return true;
        }
        return false;
    }
}
