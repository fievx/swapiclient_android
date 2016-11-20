package com.swapiclient;

import com.google.gson.Gson;
import com.swapiclient.character_detail.CharacterDetailPresenterImpl;
import com.swapiclient.model.SwCharacter;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jeremy on 20/11/2016.
 */

public class CharacterDetailPresenterTest {

    //<editor-fold desc="Character Default JSON">
    private String characterJson = "{\n" +
            "\t\t\t\"name\": \"Luke Skywalker\",\n" +
            "\t\t\t\"height\": \"172\",\n" +
            "\t\t\t\"mass\": \"77\",\n" +
            "\t\t\t\"hair_color\": \"blond\",\n" +
            "\t\t\t\"skin_color\": \"fair\",\n" +
            "\t\t\t\"eye_color\": \"blue\",\n" +
            "\t\t\t\"birth_year\": \"19BBY\",\n" +
            "\t\t\t\"gender\": \"male\",\n" +
            "\t\t\t\"homeworld\": \"http://swapi.co/api/planets/1/\",\n" +
            "\t\t\t\"films\": [\n" +
            "\t\t\t\t\"http://swapi.co/api/films/6/\",\n" +
            "\t\t\t\t\"http://swapi.co/api/films/3/\",\n" +
            "\t\t\t\t\"http://swapi.co/api/films/2/\",\n" +
            "\t\t\t\t\"http://swapi.co/api/films/1/\",\n" +
            "\t\t\t\t\"http://swapi.co/api/films/7/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"species\": [\n" +
            "\t\t\t\t\"http://swapi.co/api/species/1/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"vehicles\": [\n" +
            "\t\t\t\t\"http://swapi.co/api/vehicles/14/\",\n" +
            "\t\t\t\t\"http://swapi.co/api/vehicles/30/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"starships\": [\n" +
            "\t\t\t\t\"http://swapi.co/api/starships/12/\",\n" +
            "\t\t\t\t\"http://swapi.co/api/starships/22/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"created\": \"2014-12-09T13:50:51.644000Z\",\n" +
            "\t\t\t\"edited\": \"2014-12-20T21:17:56.891000Z\",\n" +
            "\t\t\t\"url\": \"http://swapi.co/api/people/1/\"\n" +
            "\t\t}";
    //</editor-fold>

    private SwCharacter character;
    private CharacterDetailPresenterImpl presenter;

    @Before
    public void preparePresenter(){
        Gson gson = new Gson();
        character = gson.fromJson(characterJson, SwCharacter.class);

        presenter = new CharacterDetailPresenterImpl(character);
    }

    @Test
    public void testGetAllSpaceships(){

    }
}
