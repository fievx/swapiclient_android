package com.mvpbase.views.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mvpbase.presenters.ActivityPresenter;
import com.mvpbase.views.ActivityView;

/**
 * Created by Jeremy on 4/12/2016.
 */
public abstract class MvpBaseActivity<T extends ActivityPresenter> extends AppCompatActivity implements ActivityView {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    protected boolean enableUpNavigationFromToolbar(){return true;}

    protected void handleNavigateUp(){
        finish();
    }

    protected Context getContext(){
        return this;
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public Activity getActivityContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

}
