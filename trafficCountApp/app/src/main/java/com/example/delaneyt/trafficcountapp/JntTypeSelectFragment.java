package com.example.delaneyt.trafficcountapp;

import java.util.ArrayList;
import java.util.Arrays;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * Displays a grid view of clickable thumbprint images
 *
 * <p> This is a child activity of the MainActivity class and is launched by explicit intent with
 * the junction action tab.
 *
 * It loads layout resources from the frag_jnt_type_select.xml file which includes a gridView of
 * clickable image thumbprints which launch the JntTypeSelectFragment.</p>
 *
 * <p><b>References: </b>The origins of the code used in this class is accredited to Dr Adam Porter
 * ref project: UIGrdLayout.java)</p>
 *
 *  @author Tim Delaney
 *  @version 1.0
 *  @since 2016-04-20
 *  @see "UIGridLayout" demo by Adam Porter available at:
 *  @see <a href="http://developer.android.com/guide/topics/ui/layout/gridview.html"</a>
 */
public class JntTypeSelectFragment extends Fragment {

    // Debug Tag for use logging debug output to LogCat
    private final String TAG = "JntTypeSelectFragment";

    // Declares a protected string variable EXTRA_RES_ID and initiates its value as POS
    protected static final String EXTRA_RES_ID = "POS";

    // Creates an array of resource image files which is a private instance to this class
    private ArrayList<Integer> mThumbIdsJuntions = new ArrayList<>(
            Arrays.asList(R.drawable.jnt_three_arm, R.drawable.jnt_four_arm,
                    R.drawable.jnt_staggard));

    /**
     * Returns a gridView of images inflated from the juntion_type_fragmentgment.xml layout and starts
     * CountActivity by intent using the ImageAdapter with the necessary Thumbprint Id.
     *
     * @param inflater references what to inflate on the screen
     * @param container references to a special view that can contain other views (ie children)
     * @param savedInstanceState is the argument passed back to onCreate if the activity needs to be
     *                           created (e.g., orientation change) so that you don't lose this prior
     *                           information. If no data was supplied, savedInstanceState is null.
     * @return rootView depending on user selection
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Tag marker for this method
        Log.i(TAG, "The view is visible and about to be created.");

        // references the resource file to inflate view
        View rootView = inflater.inflate(R.layout.frag_jnt_type_select, container, false);
        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getActivity(), mThumbIdsJuntions));

        // Set an setOnItemClickListener on the GridView
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position == 0) {
                    //Create an Intent to start the CountActivity
                    Intent intent = new Intent(getActivity(), AddCountTableThreeArmActivity.class);

                    // Add the positon of the thumbnail selected as an Intent Extra
//                intent.putExtra("positionOfImageSelected", position);

                    // Add the ID of the thumbnail to display as an Intent Extra
                    intent.putExtra(EXTRA_RES_ID, (int) id);

                    // Start the CountActivity
                    startActivity(intent);
                }

                else if(position == 1) {
                    Intent intent = new Intent(getActivity(), AddCountTableFourArmActivity.class);
                    intent.putExtra(EXTRA_RES_ID, (int) id);
                    startActivity(intent);
                }

                // Tag marker for this activity
                Log.i(TAG, "The activity is visible has been started.");
            }
        });
        return rootView;
    }
}