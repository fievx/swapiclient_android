package com.utils.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import au.com.beercoin.model.Constants;

/**
 *
 */
public class SharedPrefsHelper {

    private static final String SHARED_PREFS_DEVICE_TOKEN = Constants.SHARED_PREFS_DEVICE_TOKEN;
    private static final String SHARED_PREFS_IS_FIRST_LAUNCH = Constants.SHARED_PREFS_IS_FIRST_LAUNCH;
    private static final String SHARED_PREFS_AUTH_TOKEN = Constants.SHARED_PREFS_AUTH_TOKEN;
    private static final String SHARED_PREFS_PUSH_NOTIFICATIONS = Constants.SHARED_PREFS_PUSH_NOTIFICATIONS;
    private static final String SHARED_PREFS_FACEBOOK_TOKEN = Constants.SHARED_PREFS_FACEBOOK_TOKEN;

    private static SharedPrefsHelper INSTANCE = new SharedPrefsHelper();
    private SharedPreferences sharedPreferences;

    private SharedPrefsHelper() {
    }

    public static SharedPrefsHelper getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isAppFirstLaunch() {
        return sharedPreferences.getBoolean(SHARED_PREFS_IS_FIRST_LAUNCH, true);
    }

    public void setNotFirstLaunch() {
        sharedPreferences.edit().putBoolean(SharedPrefsHelper.SHARED_PREFS_IS_FIRST_LAUNCH, false).apply();
    }

    public boolean haveAuthToken() {
        return !sharedPreferences.getString(SHARED_PREFS_AUTH_TOKEN, "").isEmpty();
    }

    public String getAuthToken() {
        return sharedPreferences.getString(SHARED_PREFS_AUTH_TOKEN, "");
    }

    public void setAuthToken(String authToken) {
        sharedPreferences.edit().putString(SHARED_PREFS_AUTH_TOKEN, authToken).apply();
    }

    public void removeAuthToken() {
        sharedPreferences.edit().remove(SHARED_PREFS_AUTH_TOKEN).apply();
    }

    public void setDeviceToken(String token) {
        sharedPreferences.edit().putString(SharedPrefsHelper.SHARED_PREFS_DEVICE_TOKEN, token).apply();
    }

    public String getDeviceToken() {
        return sharedPreferences.getString(SharedPrefsHelper.SHARED_PREFS_DEVICE_TOKEN, "");
    }

    public void removeDeviceToken() {
        sharedPreferences.edit().putString(SharedPrefsHelper.SHARED_PREFS_DEVICE_TOKEN, "").apply();
    }

    public void setPushNotification(boolean state) {
        sharedPreferences.edit().putBoolean(SharedPrefsHelper.SHARED_PREFS_PUSH_NOTIFICATIONS, state).apply();
    }

    public boolean isPushNotificationsEnabled() {
        return sharedPreferences.getBoolean(SharedPrefsHelper.SHARED_PREFS_PUSH_NOTIFICATIONS, true);
    }

    public void setFacebookToken (String token){
        sharedPreferences.edit().putString(SHARED_PREFS_FACEBOOK_TOKEN, token).apply();
    }

    public String getFacebookToken(){
        return sharedPreferences.getString(SHARED_PREFS_FACEBOOK_TOKEN, "");
    }

    public boolean hasFacebookToken(){
        return !TextUtils.isEmpty(getFacebookToken());
    }
}

