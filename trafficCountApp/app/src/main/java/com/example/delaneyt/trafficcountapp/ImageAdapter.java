package com.example.delaneyt.trafficcountapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Contains formatting parameters used by the CountActivity
 *
 * <p>This class is called by explicit intent initiated by a clickable thumbprint image.
 * This class does not launch a screen/activity.</p>
 *
 * <p><b>References: </b>The origins of the code used in this class is accredited to Dr Adam Porter
 * ref project: UIGrdLayout.java)</p>
 *
 *  @author Tim Delaney
 *  @version 2.0
 *  @since 2016-01-20
 *  @see "UIGridLayout" demo by Adam Porter available at:
 *  @see <a href="http://developer.android.com/guide/topics/ui/layout/gridview.html"</a>
 */
public class ImageAdapter extends BaseAdapter {

    // Variables declared and some set as CONSTANTS
    private static final int PADDING = 10;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private Context mContext;
    private List<Integer> mThumbIds;

    /**
     * Stores a list of image IDs
     * Used by the {@link public View onCreateView} method in JntTypeSelectFragment class
     *
     * @param c refers to the context
     * @param ids are passed to this method from the JntTypeSelectFragment class
     */
    public ImageAdapter(Context c, List<Integer> ids) {
        mContext = c;
        this.mThumbIds = ids;
    }

    /**
     * Returns the number of items in the Adapter
     *
     * @return Int of the number of thumbprint
     */
    @Override
    public int getCount() {
        return mThumbIds.size();
    }

    /**
     * Returns the thumbprint position
     *
     * @param position as a int number for the thumbprint
     * @return thumbprint's Id with its associated position
     */
    @Override
    public Object getItem(int position) {
        return mThumbIds.get(position);
    }

    /**
     * Returns the ID of the image represented by the images int position
     *
     * @param position of the thumbprint represented by an int value
     * @return thumbprint's Id with its associated position
     */
    @Override
    public long getItemId(int position) {
        return mThumbIds.get(position);
    }

    /**
     * Returns an ImageView for each item referenced by the Adapter
     *
     * @param position of the thumbprint in the form an integer
     * @param convertView of the View
     * @param parent of the ViewGroup
     * @return an imageView of the image using the parameters set
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = (ImageView) convertView;

        // if convertView's not recycled, initialize some attributes
        if (imageView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT));
            imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        imageView.setImageResource(mThumbIds.get(position));
        return imageView;
    }
}