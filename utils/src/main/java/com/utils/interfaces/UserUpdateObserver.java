package com.utils.interfaces;


import au.com.beercoin.pojos.User;

/**
 * Created by jeremy on 11/04/2016.
 */
public interface UserUpdateObserver {
    public void onUserUpdated(User user);
}
