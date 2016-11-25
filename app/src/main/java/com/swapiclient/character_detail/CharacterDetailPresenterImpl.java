package com.swapiclient.character_detail;

import android.os.Bundle;

import com.mvpbase.presenters.BasePresenter;
import com.swapiclient.model.SwCharacter;
import com.swapiclient.model.SwGenericElement;
import com.swapiclient.model.api_access.ApiErrorHandler;
import com.swapiclient.model.api_access.SwapiClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Jeremy on 20/11/2016.
 */

public class CharacterDetailPresenterImpl extends BasePresenter <CharacterDetailView> implements CharacterDetailPresenter {

    private static final String CHARACTER_BASIC = "CHARACTER_BASIC";
    private static final String CHARACTER_COMPLETE = "CHARACTER_COMPLETE";

    private SwCharacter characterBasic;
    private SwCharacter characterComplete;

    public CharacterDetailPresenterImpl(SwCharacter characterBasic) {
        this.characterBasic = characterBasic;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState!=null){
            characterBasic = savedInstanceState.getParcelable(CHARACTER_BASIC);
            characterComplete = savedInstanceState.getParcelable(CHARACTER_COMPLETE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CHARACTER_BASIC, characterBasic);
        outState.putParcelable(CHARACTER_COMPLETE, characterComplete);
    }

    @Override
    public void prepareElements() {
        if (characterComplete==null){
            fetchAllDetails();
        }else {
            view.mapFullCharacter(characterComplete);
        }
    }

    /**
     * this is the method where we merge all the api calls into the existing SwCharacter before displaying it.
     */
    private void fetchAllDetails(){
        view.showProgressView();

        //We add to the list of methods to call only those really needed to avoid fails while fetching items
        //if we try to fetch elements that the character does not have, the Api fails.
        final List<List<String>> fetchableLists = characterBasic.getAllFetchableList();
        List<Observable<List<SwGenericElement>>> observableList = new ArrayList<>();
        for (List<String> fetchableList : fetchableLists) {
            observableList.add(fetchAllListElements(fetchableList));
        }

        /**
         * We create a zip of all the list of fetchable items. we get them back as an array of objects
         * and save them all as the combined list of all generique elements. The View should know how
         * to handle this combine List.
         */
        Observable.zip(
                observableList,
                new Function<Object[], List<List<SwGenericElement>>> () {
                    @Override
                    public List<List<SwGenericElement>> apply(Object[] objects) throws Exception {
                        List<List<SwGenericElement>> combinedList = new ArrayList<>();
                        for (Object object : objects) {
                            if (object instanceof ArrayList){
                                combinedList.add((ArrayList)object);
                            }
                        }
                        return combinedList;
                    }
                }
        ).subscribe(new Consumer<List<List<SwGenericElement>>>() {
            @Override
            public void accept(List<List<SwGenericElement>> combinedList) throws Exception {
                view.hideProgressView();
                view.mapCombinedList(combinedList);
                if (characterComplete==null)
                    characterComplete = new SwCharacter();
                characterComplete.setCombineList(combinedList);
            }
        }, new ApiErrorHandler(view));
    }

    /**
     * We create a list of all elements to fetch and call parallel api calls on them.
     * @param urls
     * @return
     */
    public Observable<List<SwGenericElement>> fetchAllListElements(List<String> urls){
        SwapiClient client = new SwapiClient();
        List <Observable<SwGenericElement>> observables = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            observables.add(client.getGenericElement(urls.get(i)));
        }

        return Observable.zip(observables, new Function<Object[], List<SwGenericElement>>() {
            @Override
            public List<SwGenericElement> apply(Object[] objects) throws Exception {
                List<SwGenericElement> elements = new ArrayList<SwGenericElement>();
                for (Object object : objects) {
                    elements.add((SwGenericElement)object);
                }
                return elements;
            }
        });
    }
}
