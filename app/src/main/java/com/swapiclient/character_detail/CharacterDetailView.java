package com.swapiclient.character_detail;

import com.mvpbase.views.ActivityView;
import com.swapiclient.model.SwElement;

import java.util.List;

/**
 * Created by Jeremy on 18/11/2016.
 */

public interface CharacterDetailView extends ActivityView {

    void mapFilms(List<SwElement> elementList);
    void mapVehicles (List<SwElement> vehicles);
    void mapSpaceships(List<SwElement> spaceships);
    void mapHomeworld (SwElement homeworld);
    void mapSpecie (SwElement specie);
}
