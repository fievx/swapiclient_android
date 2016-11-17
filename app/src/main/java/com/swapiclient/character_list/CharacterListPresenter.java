package com.swapiclient.character_list;

import com.mvpbase.presenters.ActivityPresenter;

/**
 * Created by Jeremy on 17/11/2016.
 */

public interface CharacterListPresenter extends ActivityPresenter <CharacterListView> {

    void fetchCharacters();
    void loadNextPage();
}
