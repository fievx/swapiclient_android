package com.mvpbase.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mvpbase.views.ActivityView;

/**
 * Created by Jeremy on 25/08/2016.
 */
public interface ActivityPresenter <T extends ActivityView> extends Presenter <T>{
    void onCreate (Bundle savedInstanceState);
    void onCreate (Intent intent, Bundle savedInstanceState);
    void onSaveInstanceState(Bundle outState);
    void onDestroy();
    Context getActivityContext();
}
