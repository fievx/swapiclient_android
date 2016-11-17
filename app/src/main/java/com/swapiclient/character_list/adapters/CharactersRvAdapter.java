package com.swapiclient.character_list.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.swapiclient.BR;
import com.swapiclient.BindingViewHolderImpl;
import com.swapiclient.R;

import java.util.List;

/**
 * Created by Jeremy on 16/09/2016.
 */
public class CharactersRvAdapter extends RecyclerView.Adapter<BindingViewHolderImpl> {
    List<Character> list;

    public CharactersRvAdapter(List<Character> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list != null ? this.list.size() : 0;
    }

    @Override
    public BindingViewHolderImpl onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_character, parent, false);
        return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolderImpl holder, int position) {
        final Character object = list.get(position);
        holder.getBinding().setVariable(BR.character, object);
        holder.getBinding().executePendingBindings();
    }

    public class CharacterViewHolder extends BindingViewHolderImpl {
        public CharacterViewHolder(ViewDataBinding dataBinding) {
            super(dataBinding);
        }
    }
}