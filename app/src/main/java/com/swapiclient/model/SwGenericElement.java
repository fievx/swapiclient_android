package com.swapiclient.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Very simple Pojo meant to handle the main name of any element returned by the API.
 * Created by Jeremy on 18/11/2016.
 */

public class SwGenericElement implements SwElement, Parcelable {

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
