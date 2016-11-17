package com.mvpbase.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mvpbase.views.ActivityView;

/**
 * Created by Jeremy on 7/08/2016.
 */
public abstract class BasePresenter <T extends ActivityView> implements ActivityPresenter<T> {

    protected T view;

    @Override
    public void bindView(T view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onCreate(Intent intent, Bundle savedInstanceState) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Context getActivityContext() {
        return view.getActivityContext();
    }

    @Override
    public Context getAppContext() {
        return view.getAppContext();
    }

    protected void startActivity(Intent intent){
        view.getActivityContext().startActivity(intent);
    }

    protected void startActivityForResult(Intent intent, int requestCode){
        view.getActivityContext().startActivityForResult(intent, requestCode);
    }
}
