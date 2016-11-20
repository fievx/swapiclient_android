package com.swapiclient.character_detail;

import com.mvpbase.views.ActivityView;
import com.swapiclient.model.SwCharacter;
import com.swapiclient.model.SwElement;
import com.swapiclient.model.SwGenericElement;

import java.util.List;

/**
 * Created by Jeremy on 18/11/2016.
 */

public interface CharacterDetailView extends ActivityView {

    void mapFullCharacter(SwCharacter character);
}
