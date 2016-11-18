package com.swapiclient.character_list.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swapiclient.BR;
import com.swapiclient.BindingViewHolderImpl;
import com.swapiclient.R;
import com.swapiclient.model.SwCharacter;

import java.util.List;

/**
 * Created by Jeremy on 16/09/2016.
 */
public class CharactersRvAdapter extends RecyclerView.Adapter<BindingViewHolderImpl> {
    List<SwCharacter> list;
    OnSwCharacterClickListener listener;

    public CharactersRvAdapter(List<SwCharacter> list) {
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
        final SwCharacter object = list.get(position);
        holder.getBinding().setVariable(BR.character, object);
        holder.getBinding().executePendingBindings();
    }

    public class CharacterViewHolder extends BindingViewHolderImpl implements View.OnClickListener {
        public CharacterViewHolder(ViewDataBinding dataBinding) {
            super(dataBinding);
            dataBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener!=null){
                listener.onSwCharacterClicked(list.get(getAdapterPosition()));
            }
        }
    }

    public void setListener(OnSwCharacterClickListener listener) {
        this.listener = listener;
    }

    public interface OnSwCharacterClickListener{
        void onSwCharacterClicked(SwCharacter character);
    }
}