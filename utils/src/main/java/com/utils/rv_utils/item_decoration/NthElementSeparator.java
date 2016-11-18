package com.utils.rv_utils.item_decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.utils.R;

/**
 * Creates a line that will stop a the left most point of the specified child in the view item.
 * For it to work, the view item must be a single Horizontal LinearLayout
 * Created by williamma on 29/06/2016.
 */
public class    NthElementSeparator extends RecyclerView.ItemDecoration {

    private Drawable divider;
    private int index = 0;

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);

            // Get the left position of the 2nd element in the linear layout
            // We don't want to underline the icon.
            ViewGroup group = (ViewGroup) child;
            int left = 0;
            try {
                left = group.getChildAt(index).getLeft();
            } catch (Exception e) {
                e.printStackTrace();
            }

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();
            divider.setBounds(left, top, parent.getRight(), bottom);
            divider.draw(c);
        }
    }

    public NthElementSeparator(Context context, int index) {
        divider = ContextCompat.getDrawable(context, R.drawable.divider);
        this.index = index;
    }

    public NthElementSeparator(Context context, int index, int colorRes) {
        divider = ContextCompat.getDrawable(context, R.drawable.divider);
        if (colorRes != 0) {
            divider.setColorFilter(ContextCompat.getColor(context, colorRes), PorterDuff.Mode.OVERLAY);
        }
        this.index = index;
    }



}
