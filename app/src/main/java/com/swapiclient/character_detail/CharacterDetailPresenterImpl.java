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
import io.reactivex.functions.Function5;

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
            if (characterComplete==null){
                fetchAllDetails();
            }else {
                view.mapFullCharacter(characterComplete);
            }
        }else {
            fetchAllDetails();
        }
    }


    /**
     * this is the method where we merge all the api calls into the existing SwCharacter before displaying it.
     * in this method, it is important to respect the order in which we zip each call because the same
     * order is the output and the names don't provide much information.
     */
    private void fetchAllDetails(){
        SwapiClient client = new SwapiClient();
        Observable.zip(
                fetchAllListElements(characterBasic.getFilmUrls()),
                fetchAllListElements(characterBasic.getSpeciesUrls()),
                fetchAllListElements(characterBasic.getVehiclesUrls()),
                fetchAllListElements(characterBasic.getStarshipsUrls()),
                client.getGenericElement(characterBasic.getHomeworldUrl()),
                new Function5<List<SwGenericElement>, List<SwGenericElement>, List<SwGenericElement>, List<SwGenericElement>, SwGenericElement, SwCharacter>() {
                    @Override
                    public SwCharacter apply(List<SwGenericElement> swGenericElements, List<SwGenericElement> swGenericElements2, List<SwGenericElement> swGenericElements3, List<SwGenericElement> swGenericElements4, SwGenericElement swGenericElement) throws Exception {
                        final SwCharacter character = new SwCharacter();
                        character.setFilmsElement(swGenericElements);
                        character.setSpeciesElement(swGenericElements2);
                        character.setVehiclesElement(swGenericElements3);
                        character.setStarshipsElement(swGenericElements4);
                        character.setHomeworldElement(swGenericElement);
                        return character;
                    }
                }
        ).subscribe(new Consumer<SwCharacter>() {
            @Override
            public void accept(SwCharacter character) throws Exception {
                characterComplete = character;
                view.mapFullCharacter(characterComplete);

            }
        }, new ApiErrorHandler(view));
    }

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
