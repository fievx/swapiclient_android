package com.swapiclient.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;

import com.swapiclient.Constants;

/**
 * Very simple Pojo meant to handle the main name of any element returned by the API.
 * Created by Jeremy on 18/11/2016.
 */

public class SwGenericElement implements SwElement, Parcelable {

    private String name;
    private String title;
    private String url;

    @Override
    public String getDisplayableName() {
        if (name !=null){
            return name;
        }else if (title!=null){
            return title;
        }else return "";
    }

    /**
     * Reads the url and gets back the type of element (ie:film, vehicles...)
     * The safest way I think is to read what comes after the base Endpoint and stop reading at the next
     * slash
     * @return
     */
    public String getType (){
        String endpoint = Constants.ENDPOINT;
        String type = url.substring(endpoint.length());
        type = type.substring(0, type.indexOf('/'));
        return type;
    }

    //<editor-fold desc="Getters Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //</editor-fold>

    //<editor-fold desc="Parcelable impl">
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.title);
    }

    public SwGenericElement() {
    }

    protected SwGenericElement(Parcel in) {
        this.name = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<SwGenericElement> CREATOR = new Parcelable.Creator<SwGenericElement>() {
        @Override
        public SwGenericElement createFromParcel(Parcel source) {
            return new SwGenericElement(source);
        }

        @Override
        public SwGenericElement[] newArray(int size) {
            return new SwGenericElement[size];
        }
    };
    //</editor-fold>
}
