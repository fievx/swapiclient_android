package com.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Jeremy on 16/11/2016.
 */

public class Helper {

    public static float convertDpToPixels(Context context, int dp){
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    public static int convertPixelToDp(Context context, float pixels){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)Math.ceil(pixels * scale);
    }
}
