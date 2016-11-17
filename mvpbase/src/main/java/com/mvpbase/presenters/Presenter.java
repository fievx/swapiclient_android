package com.mvpbase.presenters;

import android.content.Context;

import com.mvpbase.views.View;

/**
 * Created by Jeremy on 7/08/2016.
 */
public interface Presenter <T extends View> {

    void bindView(T view);
    void unbindView();
    Context getAppContext();
}
