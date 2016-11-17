package com.swapiclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Jeremy on 17/11/2016.
 */

public class SwCharacter implements Parcelable {

    //<editor-fold desc="Variables">
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private String created;
    private String edited;
    private String url;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private List<String> starships;
    //</editor-fold>

    //<editor-fold desc="Getters - Setters">
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

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
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

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }
    //</editor-fold>

    //<editor-fold desc="Parcelable implementation">
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
        dest.writeString(this.homeworld);
        dest.writeString(this.created);
        dest.writeString(this.edited);
        dest.writeString(this.url);
        dest.writeStringList(this.films);
        dest.writeStringList(this.species);
        dest.writeStringList(this.vehicles);
        dest.writeStringList(this.starships);
    }

    public SwCharacter() {
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
        this.homeworld = in.readString();
        this.created = in.readString();
        this.edited = in.readString();
        this.url = in.readString();
        this.films = in.createStringArrayList();
        this.species = in.createStringArrayList();
        this.vehicles = in.createStringArrayList();
        this.starships = in.createStringArrayList();
    }

    public static final Parcelable.Creator<SwCharacter> CREATOR = new Parcelable.Creator<SwCharacter>() {
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
