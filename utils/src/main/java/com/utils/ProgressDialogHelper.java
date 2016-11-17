package com.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;



public class ProgressDialogHelper {
    private static ProgressDialog dialog;

    public static void showProgressDialog(Context context, String message) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(context);
            }
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.setMessage(message);
            dialog.show();

        } catch (Exception e) {
            Log.e("showProgressDialog", e.getMessage());
        }
    }

    public static void showProgressDialog(Context context) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(context);
            }
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();

        } catch (Exception e) {
            Log.e("showProgressDialog", e.getMessage());
        }
    }

    public static void dismissProgressDialog() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            Log.e("dismissProgressDialog", e.getMessage()!=null ? e.getMessage() : "");
        } finally {
            dialog = null;
        }
    }
}
