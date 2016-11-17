package com.swapiclient.character_list;

import android.support.v7.widget.RecyclerView;

import com.mvpbase.views.ActivityView;
import com.swapiclient.model.Character;

/**
 * Created by Jeremy on 17/11/2016.
 */

public interface CharacterListView extends ActivityView {

    void onRvAdapterReady(RecyclerView.Adapter adapter);

    void openCharacterDetail (Character character);
}
