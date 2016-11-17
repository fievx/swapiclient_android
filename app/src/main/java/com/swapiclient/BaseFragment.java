package com.swapiclient;

import com.mvpbase.views.fragments.BaseMvpFragment;
import com.utils.ProgressDialogHelper;

/**
 * Created by Jeremy on 17/11/2016.
 */

public abstract class BaseFragment extends BaseMvpFragment {

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
