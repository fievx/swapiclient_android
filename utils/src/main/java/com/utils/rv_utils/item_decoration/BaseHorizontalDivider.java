package com.utils.rv_utils.item_decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.utils.Helper;

/**
 * Creates a line that will stop at the left of a specified View
 */
public abstract class BaseHorizontalDivider extends RecyclerView.ItemDecoration {

    protected Drawable divider;
    protected int start, end;
    private int topBottomPadding;
    protected Context context;


    /**
     * Constructor
     * @param context
     */
    public BaseHorizontalDivider(Context context) {
        this.context = context;
        divider = getDivider();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }

        outRect.top = divider.getIntrinsicHeight() + topBottomPadding;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = start; i<parent.getChildCount()-end; i++) {
            final int [] bounds = getDecorationBounds(i, parent);
            divider.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
            divider.draw(c);
        }
    }

    /**
     * Padding that should be applied before and after each separator
     * @param paddingInDP in DP (just like you would in an XML layout)
     */
    public void setTopBottomPadding (int paddingInDP){
        topBottomPadding = (int) Helper.convertDpToPixels(context, paddingInDP);
    }

    protected abstract int [] getDecorationBounds(int position, RecyclerView parent);

    /**
     * Convenience method to get an array of bounds that creates a full line.
     * can be used when implementing getDecorationBounds in subclasses
     * @param position
     * @param parent
     * @return
     */
    protected int[] getDefaultDecorationBounds(int position, RecyclerView parent){
        View child = parent.getChildAt(position);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getBottom() + params.bottomMargin + topBottomPadding;
        int bottom = top + divider.getIntrinsicHeight();

        int [] bounds = {
                parent.getLeft(),
                top,
                parent.getRight(),
                bottom
        };

        return bounds;
    }

    /**
     * The number of view before the end that should not be decorated.
     * pass 0 to have a line below the last item
     * pass 1 to avoid decorating the last item
     * So on...
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * the first view that should get decorated
     * pass 0 to decorate the first view
     * pass 1 to skip the header
     * and so on...
     * @param start
     */
    public void setStart(int start) {
        this.start = start;
    }

    protected abstract Drawable getDivider();
}
