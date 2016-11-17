package com.swapiclient.model.api_access;

import com.swapiclient.model.Character;
import com.swapiclient.model.ListApiResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Collection of static methods to perform api calls
 * Created by Jeremy on 17/11/2016.
 */
public class ApiManager {

    public static Observable<ListApiResponse<Character>> getCharacterListAtPage (int page){
        return wrapper(SwapiClient.getService().getCharactersAtPage(page));
    }

    public static Observable<Character> getCharacterAtId(int id){
        return wrapper(SwapiClient.getService().getCharacterAtId(id));
    }

    /**
     * Wrapper, to reduce boilerplate in api codes and make sure the call is performed on the io
     * thread and observed on the main thread
     *
     */
    private static <T> Observable<T> wrapper(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
