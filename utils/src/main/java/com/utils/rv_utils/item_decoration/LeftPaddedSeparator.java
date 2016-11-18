package com.utils.rv_utils.item_decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.utils.Helper;
import com.utils.R;

/**
 * Creates a line that will stop at a specified padding.
 */
public class LeftPaddedSeparator extends RecyclerView.ItemDecoration {

    private Drawable divider;
    private float padding;

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = 0; i<parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);

            // Get the left position of the 2nd element in the linear layout
            // We don't want to underline the icon.
            ViewGroup group = (ViewGroup) child;

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();
            divider.setBounds((int)padding, top, parent.getRight(), bottom);
            divider.draw(c);
        }
    }

    /**
     * Constructor to define the padding in dp
     * @param context
     * @param dp
     */
    public LeftPaddedSeparator(Context context, int dp) {
        divider = ContextCompat.getDrawable(context, R.drawable.divider_dark);
        this.padding = Helper.convertDpToPixels(context, dp);
    }

    /**
     * Constructor to define the padding directly in pixels
     * @param context
     * @param pixels
     */
    public LeftPaddedSeparator(Context context, float pixels) {
        divider = ContextCompat.getDrawable(context, R.drawable.divider_dark);
        this.padding = pixels;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }

        outRect.top = divider.getIntrinsicHeight();
    }
}
