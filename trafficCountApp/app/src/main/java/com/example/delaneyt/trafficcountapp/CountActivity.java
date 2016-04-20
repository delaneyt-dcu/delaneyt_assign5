package com.example.delaneyt.trafficcountapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 *  Displays an enlarged view of a thumbprint
 *
 *  <p> This class is launched by explicit intent when an image thumbprint is clicked within the
 *  JntTypeSelectFragment class and includes an actionbar containing a clickable back action arrow which
 *  will return the user to the JntTypeSelectFragment class.</p>
 *
 *  <p><b>References: </b>The origins of the code used in this class is accredited to Dr Adam Porter
 * ref project: UIGrdLayout.java)</p>
 *
 *  @author Tim Delaney
 *  @version 2.0
 *  @since 2016-01-20
 *  @see "UIGridLayout" demo by Adam Porter available at:
 *  @see <a href="http://developer.android.com/guide/topics/ui/layout/gridview.html"</a>
 */
public class CountActivity extends AppCompatActivity {
    int m_Position;

    /**
     * Saves the state of the application in a bundle based on the value of the savedInstance State
     * and carries out button intent actions.
     *
     * @param savedInstanceState can be passed back to onCreate if the activity needs to be created
     *                           (e.g., orientation change) so that you don't lose this prior
     *                           information. If no data was supplied, savedInstanceState is null.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        //Debug Tag for use logging debug output to LogCat
        String TAG = "CountActivity";

        // Calls the onCreate constructor of the AppCompatActivity superclass
        super.onCreate(savedInstanceState);




        // Get the Intent used to start this Activity
        Intent intent = getIntent();

        // Make a new ImageView
        ImageView imageView = new ImageView(getApplicationContext());

        // Get the ID of the image to display and set it as the image for this ImageView
        imageView.setImageResource(intent.getIntExtra(JntTypeSelectFragment.EXTRA_RES_ID, 0));

        // Passes the imageView as an argument into the setContentView method
        setContentView(R.layout.activity_count);

        // Tag marker for this activity
        Log.i(TAG, "The activity is visible and has been created.");































        // Get the Intent used to start this Activity
//        Intent intent = getIntent();

        // Make a new ImageView
//        ImageView imageView = new ImageView(getApplicationContext());

        // Get the ID of the image to display and set it as the image for this ImageView
//        imageView.setImageResource(intent.getIntExtra(ThreeArmJntActivity.EXTRA_RES_ID, 0));

///////////////////////////////////////////////////////////////////////////////////
        // Get the Intent used to start this Activity
//        Bundle extras = getIntent().getExtras();
        // Get the position of the image selected ans use it to setContentView
        // to the corresponding fragment layout
//        m_Position = extras.getInt("positionOfImageSelected");
//        if(m_Position == 0) {
 //           setContentView(R.layout.activity_three_arm_jnt);

 //           FragmentTransaction ft = getFragmentManager().beginTransaction();
 //           ft.add(ThreeArmJntActivity.newInstance(), null);
 //           ft.commit();

//            FragmentManager fm = getFragmentManager();
//            ThreeArmJntActivity imageDialog = new ThreeArmJntActivity();
//            ThreeArmJntActivity.show(fm, "image_title");

//            Intent m_Intent = new Intent(this, ThreeArmJntActivity.class);
//            startActivity(m_Intent);
//        }
//        else if(m_Position ==1) {
//            setContentView(R.layout.activity_four_arm_jnt);
//        }
//        else if(m_Position ==2) {
//            setContentView(R.layout.activity_four_arm_jnt);
//        }
//        else {
//            setContentView(R.layout.frag_jnt_type_select);
//        }
};









 //   public void sendMessage(View view) {
 //       Intent intent = new Intent(this, DisplayMessageActivity.class);
 //       EditText editText = (EditText) findViewById(R.id.edit_message);
 //       String message = editText.getText().toString();
 //       intent.putExtra(EXTRA_MESSAGE, message);
 //       startActivity(intent);
 //   }













    /**
     * Returns user to previous screen with up arrow set to act like a back arrow in this case
     * ie could not set JntTypeSelectFragment as parent in Manifest file
     *
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