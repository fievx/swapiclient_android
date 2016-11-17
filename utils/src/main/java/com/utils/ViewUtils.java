package com.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.InputFilter;
import android.util.TypedValue;
import android.widget.EditText;

public class ViewUtils {

    public static void limitEditTextLength(EditText v, final int maxCharacters){
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(maxCharacters);
        v.setFilters(filterArray);

    }

    public static float convertDpToPixels(Context context, int dp){
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    public static int convertPixelToDp(Context context, float pixels){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)Math.ceil(pixels * scale);
    }
}
