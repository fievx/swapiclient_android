package com.mvpbase.views.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.mvpbase.presenters.Presenter;
import com.mvpbase.views.ActivityView;

/**
 * Created by Jeremy on 4/17/2016.
 */
public abstract class BaseMvpFragment<T extends Presenter> extends Fragment implements ActivityView {

    protected T presenter;

    @Override
    public void finishActivity() {
        getActivity().finish();
    }

    @Override
    public Activity getActivityContext() {
        return getActivity();
    }

    @Override
    public Context getAppContext() {
        return getContext();
    }
}
