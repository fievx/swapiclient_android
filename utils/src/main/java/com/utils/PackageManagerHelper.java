package com.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

/**
 * Helper to facilitate actions using the Package Manager
 * Created by jeremy on 29/03/2016.
 */
public class PackageManagerHelper {

    private static PackageManagerHelper helper;
    Context context;
    SharedPreferences prefs;
    private static final String TAG = "PackageManagerHelper";

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private PackageManagerHelper(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PackageManagerHelper getInstance(Context pContext){
        if (helper==null || !helper.getContext().equals(pContext))
            return new PackageManagerHelper(pContext);
        else return helper;
    }

    public PackageManagerHelper() {
    }

    /**
     * Creates an Intent using the package name of the Investor App. Will display an error message if
     *  the app is not found on the device.
     */
    public Intent getIntentFromPackageName(final String packageName, String appName){
        final PackageManager manager = context.getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(packageName);
        if (intent!=null) {
            return intent;
        } else {
            DialogInterface.OnClickListener clickYes = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startPlayStoreAndGoToApp(packageName);
                }
            };

            new AlertDialog.Builder(context)
                    .setCancelable(true)
                    .setMessage("We could not find the app " +appName+" on your device. \n Would you like to go to the play store to download it?")
                    .setPositiveButton("YES", clickYes)
                    .setNegativeButton("NO", null).show();

            return null;
        }
    }

    /**
     * Start an activity to the play store and passes the package name to search on the play store.
     * @param packageName
     */
    public void startPlayStoreAndGoToApp (String packageName){
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
    }
}
