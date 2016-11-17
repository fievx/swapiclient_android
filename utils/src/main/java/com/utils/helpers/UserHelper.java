package com.utils.helpers;

import android.content.Context;
import android.content.Intent;

import com.facebook.login.LoginManager;

import au.com.beercoin.login.LoginActivity;
import au.com.beercoin.model.networking.ApiManager;
import au.com.beercoin.model.networking.MyCallback;
import au.com.beercoin.model.networking.RetrofitHelper;
import au.com.beercoin.pojos.ApiData;
import au.com.beercoin.pojos.User;
import com.utils.interfaces.UserUpdateObserver;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class UserHelper {

    private static final String TAG = "UserHelper";

    User user;
    static UserHelper helper;
    Context ctxt;

    private UserHelper (Context context){
        this.ctxt = context;
    }

    public static UserHelper getInstance(Context context){
        if (helper !=null)
            return helper;
        else {
            helper = new UserHelper(context);
            return helper;
        }
    }

    public void login(User user) {
        SharedPrefsHelper.getInstance().setAuthToken(user.getAuthToken());

        this.user = user;
    }

    /**
     * Logs out from facebook and removes auth tokent from shared preferences.
     * Finally displays the login page.
     */
    public void logout() {
        SharedPrefsHelper.getInstance().setFacebookToken("");
        SharedPrefsHelper.getInstance().setAuthToken("");
        LoginManager.getInstance().logOut();

        ctxt.startActivity(new Intent(ctxt, LoginActivity.class));
    }

//    /**
//     * Will see if a user token is store. If yes, will attempt to refresh the user by calling the api.
//     * @param observer
//     */
//    public void refreshUser (final UserUpdateObserver observer){
//        if(observer==null)
//            throw new NullPointerException("Observer cannot be null");
//
//        this.observer = observer;
//
//        //If no token is store, return null
//        if (!SharedPrefsHelper.getInstance().haveAuthToken()) {
//            observer.onUserUpdated(null);
//            return ;
//        }
//
//        ApiManager.getCurrentUser().subscribe(new My)
//        RetrofitHelper.getBeerCoinApi().getCurrentUser().(new MyCallback<ApiData<User>>(ctxt, false) {
//            @Override
//            public void onSuccess(ApiData<User> data) {
//                User user = data.getData();
//                if(user!=null){
//                    UserHelper.this.user = user;
//                }
//
//                if(observer!=null)
//                    observer.onUserUpdated(user);
//            }
//
//            @Override
//            public void onFail() {
//                if(observer!=null)
//                    observer.onUserUpdated(user);
//            }
//        });
//    }

    /**
     * Will see if a facebook user token is stored. If yes, will attempt to refresh the user by calling the api.
     * @param observer
     */
    public void refreshUserFromFacebook (final UserUpdateObserver observer){
        if(observer==null)
            throw new NullPointerException("Observer cannot be null");

        //If no token is store, return null
        if (!SharedPrefsHelper.getInstance().hasFacebookToken()) {
            observer.onUserUpdated(null);
            return ;
        }

        Map <String, String> body = new HashMap<>();
        body.put("access_token", SharedPrefsHelper.getInstance().getFacebookToken());

        RetrofitHelper.getBeerCoinApi().createUserFromFacebook(body)
                .asObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ApiData<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ApiData<User> userApiData) {
                        User user = userApiData.getData();
                        if(user!=null){
                            UserHelper.this.user = user;
                        }

                        if(observer!=null){
                            observer.onUserUpdated(user);
                        }
                    }
                });
    }


    /**
     * retrieves the current user, either the one in variable of this class, or calls the api to get a
     * fresh one if none is available
     */
    public User getCurrentUser(){
        return user;
//        if (user!=null){
//            observer.onUserUpdated(user);
//        }else if (SharedPrefsHelper.getInstance().haveAuthToken()) {
//            refreshUser(observer);
//        } else {
//            observer.onUserUpdated(null);
//        }
    }

}
