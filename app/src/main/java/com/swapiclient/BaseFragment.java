package com.swapiclient;

import com.mvpbase.presenters.ActivityPresenter;
import com.mvpbase.views.fragments.BaseMvpFragment;
import com.utils.ProgressDialogHelper;

/**
 * Created by Jeremy on 17/11/2016.
 */

public abstract class BaseFragment <T extends ActivityPresenter> extends BaseMvpFragment <T> {

    @Override
    public void showProgressView() {
        ProgressDialogHelper.showProgressDialog(getContext());
    }

    @Override
    public void showProgressView(String message) {
        ProgressDialogHelper.showProgressDialog(getContext(), message);
    }

    @Override
    public void hideProgressView() {
        ProgressDialogHelper.dismissProgressDialog();
    }
}
