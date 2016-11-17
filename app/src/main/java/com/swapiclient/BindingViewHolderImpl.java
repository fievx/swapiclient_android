package com.swapiclient;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Base class implementing the binding view holder interface.
 * You should extend it in your view holders when using data binding mechanisms in recycler view
 * Created by Jeremy on 30/08/2016.
 */
public class BindingViewHolderImpl extends RecyclerView.ViewHolder implements BindingViewHolder{
    ViewDataBinding dataBinding;

    public BindingViewHolderImpl(ViewDataBinding dataBinding) {
        super(dataBinding.getRoot());
        this.dataBinding = dataBinding;
    }

    @Override
    public ViewDataBinding getBinding() {
        return dataBinding;
    }
}
