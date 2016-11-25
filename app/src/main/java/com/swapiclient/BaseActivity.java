package com.swapiclient;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;

import com.mvpbase.presenters.ActivityPresenter;
import com.mvpbase.views.activities.MvpBaseActivity;
import com.utils.ProgressDialogHelper;

/**
 * Created by Jeremy on 17/11/2016.
 */

public abstract class BaseActivity <T extends ActivityPresenter> extends MvpBaseActivity<T> {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device. The value is determined using the bool xml value.
     */
    protected boolean isTablet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        isTablet = getResources().getBoolean(R.bool.isTablet);

        //we define the theme to be used
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constants.PREFERENCE_THEME_SW, false))
            setTheme(R.style.AppTheme_Sw);

        super.onCreate(savedInstanceState);

    }

    @Override
    public void showProgressView() {
        ProgressDialogHelper.showProgressDialog(this);
    }

    @Override
    public void showProgressView(String message) {
        ProgressDialogHelper.showProgressDialog(this, message);
    }

    @Override
    public void hideProgressView() {
        ProgressDialogHelper.dismissProgressDialog();
    }
}
