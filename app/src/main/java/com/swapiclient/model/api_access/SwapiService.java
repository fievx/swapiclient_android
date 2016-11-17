package com.swapiclient.model.api_access;

import com.swapiclient.model.Character;
import com.swapiclient.model.ListApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jeremy on 17/11/2016.
 */

public interface SwapiService {

    @GET ("people/?page={page_number}")
    Observable<ListApiResponse<Character>> getCharactersAtPage(@Path("page_number")int pageNumber);

    @GET ("people/{character_id]")
    Observable<Character> getCharacterAtId(@Path("character_id") int id);
}
