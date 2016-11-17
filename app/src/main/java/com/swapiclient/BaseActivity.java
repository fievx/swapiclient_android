package com.swapiclient;

import com.mvpbase.presenters.ActivityPresenter;
import com.mvpbase.views.activities.MvpBaseActivity;
import com.utils.ProgressDialogHelper;

/**
 * Created by Jeremy on 17/11/2016.
 */

public abstract class BaseActivity <T extends ActivityPresenter> extends MvpBaseActivity<T> {

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
