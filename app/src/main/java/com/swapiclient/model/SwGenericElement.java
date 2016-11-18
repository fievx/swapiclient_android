package com.swapiclient.model;

/**
 * Very simple Pojo meant to handle the main name of any element returned by the API.
 * Created by Jeremy on 18/11/2016.
 */

public class SwGenericElement implements SwElement {

    private String name;
    private String title;

    @Override
    public String getDisplayableName() {
        if (name !=null){
            return name;
        }else if (title!=null){
            return title;
        }else return "";
    }
}
