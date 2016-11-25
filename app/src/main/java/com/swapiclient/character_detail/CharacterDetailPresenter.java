package com.swapiclient.character_detail;

import com.mvpbase.presenters.ActivityPresenter;

/**
 * Created by Jeremy on 18/11/2016.
 */

public interface CharacterDetailPresenter extends ActivityPresenter <CharacterDetailView> {

    /**
     * to be called when the view is ready to receive orders
     */
    void prepareElements();
}
