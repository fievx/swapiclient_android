package com.swapiclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 17/11/2016.
 */

public class ListApiResponse <T extends Parcelable> implements Parcelable {

    //<editor-fold desc="Variables">
    private int count;
    private String next;
    private String previous;
    private List<T> list;
    //</editor-fold>

    //<editor-fold desc="getters - Setters">
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    //</editor-fold>

    //<editor-fold desc="Parcelable">
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.next);
        dest.writeString(this.previous);
        dest.writeList(this.list);
    }

    public ListApiResponse() {
    }

    protected ListApiResponse(Parcel in) {
        this.count = in.readInt();
        this.next = in.readString();
        this.previous = in.readString();
        this.list = in.readArrayList(null);
    }

    public static final Parcelable.Creator<ListApiResponse> CREATOR = new Parcelable.Creator<ListApiResponse>() {
        @Override
        public ListApiResponse createFromParcel(Parcel source) {
            return new ListApiResponse(source);
        }

        @Override
        public ListApiResponse[] newArray(int size) {
            return new ListApiResponse[size];
        }
    };
    //</editor-fold>
}
