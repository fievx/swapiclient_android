package com.utils.rv_utils.item_decoration;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.utils.R;


/**
 * Creates a line that will stop at the left of a specified View
 */
public class LeftOfViewSeparator extends BaseHorizontalDivider {

    private int rootId, id;
    private boolean paddingObtained;
    protected float padding;

    /**
     * Constructor to define the view at which left the separator will stop.
     * @param context
     * @param id the id of the view to find and define as left padding
     */
    public LeftOfViewSeparator(Context context, int id) {
        super(context);
        this.context = context;
        this.id = id;
    }

    @Override
    protected Drawable getDivider() {
        return ContextCompat.getDrawable(context, R.drawable.divider_dark);
    }

    @Override
    protected int[] getDecorationBounds(int position, RecyclerView parent) {
        int [] bounds = getDefaultDecorationBounds(position, parent);
        View child = parent.getChildAt(position);

        // Get the left position of the 2nd element in the linear layout
        // We don't want to underline the icon.
        ViewGroup group = (ViewGroup) child;
        if (!paddingObtained) {
            rootId = child.getId();
            exploreHierarchy(group, id);
        }

        bounds[0] = (int)padding;

        return bounds;
    }

    /**
     * recursively explore the hierarchy of a given ViewGroup looking for the Id
     * @param parent
     * @param id
     */
    private void exploreHierarchy(ViewGroup parent, int id){
        for (int i = 0; i<parent.getChildCount(); i++){
            //if we already have a padding, this means we have found the view so we stop everything for all recursions
            if(padding!=0)
                return;

            final View v = parent.getChildAt(i);
            if (v.getId()== id){
                addPaddingsFromBottomToTop(v);
                return;
            } else if (v instanceof ViewGroup){
                exploreHierarchy((ViewGroup) v, id);
            }
        }
    }

    /**
     * is called when the child view with the correct id has been found.
     * It will add all the left padding of this view and its parent.
     */
    private void addPaddingsFromBottomToTop(View view){
        padding=padding+view.getLeft();
        if (view.getId() == rootId) {
            paddingObtained = true;
            return;
        }
        addPaddingsFromBottomToTop((View)view.getParent());
    }
}
