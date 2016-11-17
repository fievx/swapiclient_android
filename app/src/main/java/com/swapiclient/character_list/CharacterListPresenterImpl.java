package com.swapiclient.character_list;

import com.mvpbase.presenters.BasePresenter;
import com.swapiclient.model.Character;
import com.swapiclient.model.ListApiResponse;
import com.swapiclient.model.api_access.ApiErrorHandler;
import com.swapiclient.model.api_access.ApiManager;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by Jeremy on 17/11/2016.
 */

public class CharacterListPresenterImpl extends BasePresenter <CharacterListView> implements CharacterListPresenter {
    private List<Character> characterList;
    int currentPage;

    @Override
    public void fetchCharacters() {
        ApiManager.getCharacterListAtPage(1)
                .subscribe(new Consumer<ListApiResponse<Character>>() {
                    @Override
                    public void accept(ListApiResponse<Character> characterListApiResponse) throws Exception {
                        characterList = characterListApiResponse.getList();
                    }
                }, new ApiErrorHandler(view));
    }

    @Override
    public void loadNextPage() {

    }
}
