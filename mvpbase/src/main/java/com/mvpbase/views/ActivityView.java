package com.mvpbase.views;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Jeremy on 25/08/2016.
 */
public interface ActivityView extends View {
    void showProgressView();
    void showProgressView(String message);
    void hideProgressView();
    void finishActivity();
    void startActivity(Intent intent);
    Activity getActivityContext();
}
