package com.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * Created by jeremy on 21/03/2016.
 */
public class CurrencyHelper {

    private static final DecimalFormat currencyFormat = new DecimalFormat("###,###.##");

    /**
     * Format a value string to make it more readable by adding separators
     * this text "1000000.00" becomes "1,000,000.00"
     * @param value
     * @return
     */
    public static String formatValue (String value){
        if (value == null || TextUtils.isEmpty(value))
            return null;

        double valueDouble = 0;
        try {
            valueDouble = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "";
        }

        return currencyFormat.format(valueDouble) ;
    }

    /**
     * Simply removes all "," from the given text.
     * @param formattedValue
     * @return
     */
    public static String unformatValue(String formattedValue){
        return formattedValue = formattedValue.replaceAll(",", "");
    }
}
