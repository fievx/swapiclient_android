package com.swapiclient.character_list;

import android.os.Bundle;
import android.os.Parcelable;

import com.mvpbase.presenters.BasePresenter;
import com.swapiclient.character_list.adapters.CharactersRvAdapter;
import com.swapiclient.model.SwCharacter;
import com.swapiclient.model.ListApiResponse;
import com.swapiclient.model.api_access.ApiErrorHandler;
import com.swapiclient.model.api_access.ApiManager;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import io.reactivex.functions.Consumer;

/**
 * Created by Jeremy on 17/11/2016.
 */

class CharacterListPresenterImpl extends BasePresenter <CharacterListView> implements CharacterListPresenter {
    private static final String CHARACTER_LIST = "CHARACTER_LIST";
    private static final String CURRENT_PAGE = "CURRENT_PAGE";
    private static final String LAST_PAGE_LOADED = "LAST_PAGE_LOADED";

    private List<SwCharacter> characterList;
    private int currentPage;
    private CharactersRvAdapter rvAdapter;
    private boolean lastPageLoaded;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState!=null){
            characterList = savedInstanceState.getParcelableArrayList(CHARACTER_LIST);
            currentPage = savedInstanceState.getInt(CURRENT_PAGE);
            lastPageLoaded = savedInstanceState.getBoolean(LAST_PAGE_LOADED);

            rvAdapter = new CharactersRvAdapter(characterList);
            view.onRvAdapterReady(rvAdapter);
        }else{
            loadNextPage();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(CHARACTER_LIST, (ArrayList<? extends Parcelable>) characterList);
        outState.putInt(CURRENT_PAGE,  currentPage);
        outState.putBoolean(LAST_PAGE_LOADED, lastPageLoaded);
    }

    @Override
    public void loadNextPage() {
        if (!lastPageLoaded) { //Load another page only if we are not already on the last one
            currentPage++;
            ApiManager.getCharacterListAtPage(currentPage)
                    .subscribe(new Consumer<ListApiResponse<SwCharacter>>() {
                        @Override
                        public void accept(ListApiResponse<SwCharacter> characterListApiResponse) throws Exception {
                            //check if last page
                            lastPageLoaded = characterListApiResponse.getNext()==null;

                            //Create the list of characters or add them to existing list
                            if (characterList==null)
                                characterList = characterListApiResponse.getResults();
                            else characterList.addAll(characterListApiResponse.getResults());

                            //create adapter or add new elements to list
                            if (rvAdapter == null) {
                                rvAdapter = new CharactersRvAdapter(characterList);
                                view.onRvAdapterReady(rvAdapter);
                            }
                            else {
                                final int additionStart = characterList.size() - characterListApiResponse.getResults().size();
                                rvAdapter.notifyItemRangeInserted(additionStart, characterList.size()-1);
                            }
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

}
