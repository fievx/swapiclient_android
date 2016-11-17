package com.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateHelper {

    public static final SimpleDateFormat dMMMyyyy = new SimpleDateFormat("dd MMM yyyy", Locale.US);
    public static final SimpleDateFormat apiDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat dateReceivedFromAPI = new SimpleDateFormat("yyyyMMdd");
    private static final GregorianCalendar calendar = new GregorianCalendar();
    private static final String TAG = "DateHelper";

    public static final int PAST = 1;
    public static final int FUTURE = 2;

    public static void showPicker(Context context, final TextView v) {
        showPicker(context, v, null);
    }

    public static void showPicker(Context context, final TextView v, final View nextView) {
        getDatePickerDialog(context, v, nextView, null, 0).show();
    }

    /**
     * Prepares and shows a time picker with default date as yesterday
     * @param context
     * @param v
     */
    public static void showPastPickerFromYesterday(Context context, final TextView v){
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_YEAR, -1);
        getDatePickerDialog(context, v, null, yesterday, PAST).show();
    }

    public static void showPastPicker(Context context, final TextView v){
        Calendar today = Calendar.getInstance();
        getDatePickerDialog(context, v, null, today, PAST).show();
    }

    /**
     *
     * @param context
     * @param v
     * @param nextView
     * @param date must be of the form dd/mm/yyyy
     * @param pastOrFuture must of the form DateHelper.FUTURE or DateHelper.PAST. can be 0 if does not
     *                     past and future allowed
     * @return
     */
    public static DatePickerDialog getDatePickerDialog(Context context, final TextView v,
                                                       final View nextView, final Calendar date,
                                                       int pastOrFuture){
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
                v.setText(dMMMyyyy.format(calendar.getTime()));

                if (nextView != null) nextView.requestFocus();
            }
        };

        // If our textView isn't set, use the passed date or today's date if nothing is passed
        Calendar cal = null;
        if (date!=null){
            cal = date;
        }
        if (cal==null) {
            cal=Calendar.getInstance();
        }
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        if (!v.getText().toString().isEmpty()) {
            try {
                calendar.setTime(dMMMyyyy.parse(v.getText().toString()));
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        DatePickerDialog dialog = new DatePickerDialog(context, listener, year, month, day);
        if(pastOrFuture!=0){
            Calendar today = Calendar.getInstance();
            if (pastOrFuture==PAST){
                if (today.compareTo(cal)<=0) // today is before cal
                    dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
                else dialog.getDatePicker().setMaxDate(today.getTimeInMillis());
            }
            if (pastOrFuture==FUTURE){
                if (today.compareTo(cal)<=0)//today is before cal
                    dialog.getDatePicker().setMaxDate(today.getTimeInMillis());
                else dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
            }
        }

        return dialog;
    }

    public static int compare(String d1, String d2) throws ParseException {
        return dMMMyyyy.parse(d1).compareTo((dMMMyyyy.parse(d2)));
    }

    public static boolean earlier(TextView fromDate, TextView toDate) throws ParseException {
        return dMMMyyyy.parse(fromDate.getText().toString())
                .compareTo((dMMMyyyy.parse(toDate.getText().toString()))) <= 0;
    }

    public static String dateForAPICall (TextView view){
        return dateForAPICall(view.getText().toString());
    }

    /**
     *Takes a text in format d MMM YYYY and return a string formated dd/MM/yyyy
     * @param text must be of the form d MMM YYYY
     * @return
     */
    public static String dateForAPICall (String text){

        if (text.isEmpty()) {
            return "";
        }

        try {
            Date date = dMMMyyyy.parse(text);
            return apiDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Date strToDate(String text) throws ParseException {
        return dMMMyyyy.parse(text);
    }

    /**
     *
     * @param text must be of the form dd/mm/yyyy
     * @return
     */
    public static Calendar getCalendarFromString (String text){
        try {
            Date date = apiDateFormat.parse(text);
            return apiDateFormat.getCalendar();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a date set to yesterday at the same time as now and returns the corresponding String
     * in the format d MMM YYYY
     * @return
     */
    public static String getYesterdayAsText(){
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_YEAR, -1);

        return dMMMyyyy.format(yesterday.getTime());
    }

    /**
     * Creates a date set to yesterday at the same time as now and returns the corresponding String
     * in the format d MMM YYYY
     * @return
     */
    public static String getTodayAsText(){
        Calendar today = Calendar.getInstance();
        return dMMMyyyy.format(today.getTime());
    }

    public static String getLastYearAsText(){
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, -365);
        return dMMMyyyy.format(today.getTime());
    }

    public static String getLastMonthAsText(){
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -1);
        return dMMMyyyy.format(today.getTime());
    }


    /**
     * Takes a date in the Format yyyyMMd (ex 20160115) and returns a string of the format
     * d MMM yyyyy
     * @param date
     * @return a valid date to display or an empty string if a parse exception occurred.
     */
    public static String getDisplayableDateFromStringDate(String date){
        if (date!=null) {
            Date formattedDate = null;
            try {
                formattedDate = dateReceivedFromAPI.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
            return dMMMyyyy.format(formattedDate);
        } else {
            return "";
        }
    }

    /**
     * Takes a date in the Format d/MM/yyy (ex 01/02/2016) and returns a string of the format
     * d MMM yyyyy
     * @param date
     * @return a valid date to display or an empty string if a parse exception occured.
     */
    public static String getDisplayableDateFromAPIDateFormat(String date){
        Date formattedDate;
        try {
            formattedDate = apiDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return dMMMyyyy.format(formattedDate);
    }

}
