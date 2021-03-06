package com.swapiclient.model.api_access;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.swapiclient.Constants;
import com.swapiclient.model.ListApiResponse;
import com.swapiclient.model.SwCharacter;
import com.swapiclient.model.SwGenericElement;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Jeremy on 17/11/2016.
 */
public class SwapiClient {
    private SwapiService service;

    public SwapiClient() {
    }

    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();

    private Retrofit retrofit =
            new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

    private SwapiService getService() {
        if (service == null) {
            service = retrofit.create(SwapiService.class);
        }
        return service;
    }

    /**
     * Create a generic Service that can be used to pass any url and get back any SwGenericElement
     * @return
     */
    private GenericService getGenericService(String url){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(GenericService.class);
    }

    public interface GenericService{
        @GET
        Observable<SwGenericElement> getGenericElement(@Url String url);
    }

    //<editor-fold desc="Api Call Methods">
    public Observable<ListApiResponse<SwCharacter>> getCharacterListAtPage (int page){
        return wrapper(getService().getCharactersAtPage(page));
    }

    public Observable<SwCharacter> getCharacterAtId(int id){
        return wrapper(getService().getCharacterAtId(id));
    }

    /**
     * This method will call any passed url retrieved from the Character Json object in order to
     * retrieve the films, species... which are stored as urls in the swCharacter Object
     * @param url
     * @return
     */
    public Observable<SwGenericElement> getGenericElement(String url){
        //we need to split the url at a "/" to pass it as base url and parameter
        //the easiest way is to cut just after the api word
        int positionApi = url.indexOf("api/")+4;
        String base = url.substring(0, positionApi);
        String param = url.substring(positionApi, url.length());

        return wrapper(getGenericService(base).getGenericElement(url));
    }

    /**
     * Wrapper, to reduce boilerplate in api codes and make sure the call is performed on the io
     * thread and observed on the main thread
     *
     */
    private <T> Observable<T> wrapper(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //</editor-fold>


}
