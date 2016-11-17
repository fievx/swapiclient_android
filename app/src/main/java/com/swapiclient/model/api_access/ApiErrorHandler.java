package com.swapiclient.model.api_access;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.mvpbase.views.ActivityView;
import com.swapiclient.R;
import com.utils.AlertDialogHelper;

import io.reactivex.functions.Consumer;


/**
 * Created by Jeremy on 17/11/2016.
 */

public class ApiErrorHandler implements Consumer <Throwable> {
    ActivityView view;

    public ApiErrorHandler(ActivityView view) {
        this.view = view;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
        view.hideProgressView();
        if (throwable instanceof HttpException) {
            AlertDialogHelper.showOk(view.getActivityContext(), view.getAppContext().getString(R.string.error_title),
                    view.getAppContext().getString(R.string.error_text));
        }
    }
}
