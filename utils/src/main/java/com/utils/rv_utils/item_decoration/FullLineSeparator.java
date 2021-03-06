package com.utils.rv_utils.item_decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.utils.R;

/**
 * Created by williamma on 29/06/2016.
 */
public class FullLineSeparator extends RecyclerView.ItemDecoration {

    private Drawable divider;

    public FullLineSeparator(Context context) {
        this(context, 0);
    }

    public FullLineSeparator(Context context, int colorRes) {
        if (context != null) {
            divider = ContextCompat.getDrawable(context, R.drawable.divider);
            if (colorRes != 0) {
                divider.setColorFilter(ContextCompat.getColor(context, colorRes), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();
            divider.setBounds(parent.getLeft(), top, parent.getRight(), bottom);
            divider.draw(c);
        }
    }

}
