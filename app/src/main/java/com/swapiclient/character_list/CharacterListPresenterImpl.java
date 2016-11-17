package com.swapiclient.character_list;

import com.mvpbase.presenters.BasePresenter;
import com.swapiclient.character_list.adapters.CharactersRvAdapter;
import com.swapiclient.model.SwCharacter;
import com.swapiclient.model.ListApiResponse;
import com.swapiclient.model.api_access.ApiErrorHandler;
import com.swapiclient.model.api_access.ApiManager;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by Jeremy on 17/11/2016.
 */

public class CharacterListPresenterImpl extends BasePresenter <CharacterListView> implements CharacterListPresenter {
    private List<SwCharacter> characterList;
    private int currentPage;
    private CharactersRvAdapter rvAdapter;

    @Override
    public void loadNextPage() {
        currentPage++;
        ApiManager.getCharacterListAtPage(currentPage)
                .subscribe(new Consumer<ListApiResponse<SwCharacter>>() {
                    @Override
                    public void accept(ListApiResponse<SwCharacter> characterListApiResponse) throws Exception {
                        characterList = characterListApiResponse.getList();
                        if (rvAdapter == null)
                            rvAdapter = new CharactersRvAdapter(characterList);
                    }
                }, new ApiErrorHandler(view){
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        super.accept(throwable);
                        currentPage--;
                    }
                });
    }

}
