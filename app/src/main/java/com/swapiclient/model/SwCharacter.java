package com.swapiclient.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import org.abego.treelayout.internal.util.java.util.ListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jeremy on 17/11/2016.
 */

public class SwCharacter implements Parcelable, SwElement {

    //<editor-fold desc="Variables">
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    @SerializedName("homeworld") private String homeworldUrl;
    private SwGenericElement homeworldElement;
    private String created;
    private String edited;
    private String url;
    @SerializedName("films")
    private List<String> filmUrls;
    @SerializedName("species")
    private List<String> speciesUrls;
    @SerializedName("vehicles")
    private List<String> vehiclesUrls;
    @SerializedName("starships")
    private List<String> starshipsUrls;
    private List<SwGenericElement> filmsElement, speciesElement, vehiclesElement, starshipsElement;
    private List<List<SwGenericElement>> combineList;
    //</editor-fold>

    //<editor-fold desc="Getters - Setters">
    public SwGenericElement getHomeworldElement() {
        return homeworldElement;
    }

    public void setHomeworldElement(SwGenericElement homeworldElement) {
        this.homeworldElement = homeworldElement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public void setSkin_color(String skin_color) {
        this.skin_color = skin_color;
    }

    public String getEye_color() {
        return eye_color;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworldUrl() {
        return homeworldUrl;
    }

    public void setHomeworldUrl(String homeworldUrl) {
        this.homeworldUrl = homeworldUrl;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getFilmUrls() {
        return filmUrls;
    }

    public void setFilmUrls(List<String> filmUrls) {
        this.filmUrls = filmUrls;
    }

    public List<String> getSpeciesUrls() {
        return speciesUrls;
    }

    public void setSpeciesUrls(List<String> speciesUrls) {
        this.speciesUrls = speciesUrls;
    }

    public List<String> getVehiclesUrls() {
        return vehiclesUrls;
    }

    public void setVehiclesUrls(List<String> vehiclesUrls) {
        this.vehiclesUrls = vehiclesUrls;
    }

    public List<String> getStarshipsUrls() {
        return starshipsUrls;
    }

    public void setStarshipsUrls(List<String> starshipsUrls) {
        this.starshipsUrls = starshipsUrls;
    }

    public List<SwGenericElement> getFilmsElement() {
        return filmsElement;
    }

    public void setFilmsElement(List<SwGenericElement> filmsElement) {
        this.filmsElement = filmsElement;
    }

    public List<SwGenericElement> getSpeciesElement() {
        return speciesElement;
    }

    public void setSpeciesElement(List<SwGenericElement> speciesElement) {
        this.speciesElement = speciesElement;
    }

    public List<SwGenericElement> getStarshipsElement() {
        return starshipsElement;
    }

    public void setStarshipsElement(List<SwGenericElement> starshipsElement) {
        this.starshipsElement = starshipsElement;
    }

    public List<SwGenericElement> getVehiclesElement() {
        return vehiclesElement;
    }

    public void setVehiclesElement(List<SwGenericElement> vehiclesElement) {
        this.vehiclesElement = vehiclesElement;
    }

    public List<List<SwGenericElement>> getCombineList() {
        return combineList;
    }

    public void setCombineList(List<List<SwGenericElement>> combineList) {
        this.combineList = combineList;
    }


    //</editor-fold>

    public SwCharacter() {
    }

    @Override
    public String getDisplayableName() {
        return name;
    }

    /**
     * return all non null, non empty lists of fetchable elements (ie: vehicles, films...)
     * @return
     */
    public List<List<String>> getAllFetchableList (){
        List<List<String>> list = new ArrayList<>();
        if (isListNotEmpty(vehiclesUrls))
            list.add(vehiclesUrls);
        if (isListNotEmpty(filmUrls))
            list.add(filmUrls);
        if (isListNotEmpty(speciesUrls))
            list.add(speciesUrls);
        if (isListNotEmpty(starshipsUrls))
            list.add(starshipsUrls);
        if(!TextUtils.isEmpty(homeworldUrl)){
            final ArrayList homewoldList = new ArrayList();
            homewoldList.add(homeworldUrl);
            list.add(homewoldList);
        }

        return list;
    }

    private boolean isListNotEmpty (List list){
        return list!=null && !list.isEmpty();
    }

    //<editor-fold desc="Parcelable impl">
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.height);
        dest.writeString(this.mass);
        dest.writeString(this.hair_color);
        dest.writeString(this.skin_color);
        dest.writeString(this.eye_color);
        dest.writeString(this.birth_year);
        dest.writeString(this.gender);
        dest.writeString(this.homeworldUrl);
        dest.writeParcelable(this.homeworldElement, flags);
        dest.writeString(this.created);
        dest.writeString(this.edited);
        dest.writeString(this.url);
        dest.writeStringList(this.filmUrls);
        dest.writeStringList(this.speciesUrls);
        dest.writeStringList(this.vehiclesUrls);
        dest.writeStringList(this.starshipsUrls);
        dest.writeTypedList(this.filmsElement);
        dest.writeTypedList(this.speciesElement);
        dest.writeTypedList(this.vehiclesElement);
        dest.writeTypedList(this.starshipsElement);
        dest.writeList(this.combineList);
    }

    protected SwCharacter(Parcel in) {
        this.name = in.readString();
        this.height = in.readString();
        this.mass = in.readString();
        this.hair_color = in.readString();
        this.skin_color = in.readString();
        this.eye_color = in.readString();
        this.birth_year = in.readString();
        this.gender = in.readString();
        this.homeworldUrl = in.readString();
        this.homeworldElement = in.readParcelable(SwGenericElement.class.getClassLoader());
        this.created = in.readString();
        this.edited = in.readString();
        this.url = in.readString();
        this.filmUrls = in.createStringArrayList();
        this.speciesUrls = in.createStringArrayList();
        this.vehiclesUrls = in.createStringArrayList();
        this.starshipsUrls = in.createStringArrayList();
        this.filmsElement = in.createTypedArrayList(SwGenericElement.CREATOR);
        this.speciesElement = in.createTypedArrayList(SwGenericElement.CREATOR);
        this.vehiclesElement = in.createTypedArrayList(SwGenericElement.CREATOR);
        this.starshipsElement = in.createTypedArrayList(SwGenericElement.CREATOR);
        this.combineList = new ArrayList<List<SwGenericElement>>();
        in.readList(this.combineList, List.class.getClassLoader());
    }

    public static final Creator<SwCharacter> CREATOR = new Creator<SwCharacter>() {
        @Override
        public SwCharacter createFromParcel(Parcel source) {
            return new SwCharacter(source);
        }

        @Override
        public SwCharacter[] newArray(int size) {
            return new SwCharacter[size];
        }
    };

    //</editor-fold>

}
