package com.swapiclient.character_detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swapiclient.BaseFragment;
import com.swapiclient.R;
import com.swapiclient.custom_views.GenericElementsList;
import com.swapiclient.custom_views.KeyValueView;
import com.swapiclient.databinding.FragmentCharacterDetailBinding;
import com.swapiclient.model.SwCharacter;
import com.swapiclient.model.SwElement;
import com.swapiclient.model.SwGenericElement;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A fragment representing a single SwCharacter detail screen.
 */
public class CharacterDetailFragment extends BaseFragment <CharacterDetailPresenter> implements CharacterDetailView {

    //<editor-fold desc="Binds">
    LinearLayout llFilms;
    @BindView(R.id.kv_homeworld)
    KeyValueView kvHomeworld;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    //</editor-fold>

    private static final String CHARACTER = "CHARACTER";

    private SwCharacter character;
    private Unbinder unbinder;

    public static CharacterDetailFragment getInstance(SwCharacter character){
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CHARACTER, character);
        fragment.setArguments(bundle);
        return fragment;
    }

    public CharacterDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState!=null){
            character = savedInstanceState.getParcelable(CHARACTER);
        }else {
            character = getArguments().getParcelable(CHARACTER);
        }

        presenter = new CharacterDetailPresenterImpl(character);
        presenter.bindView(this);
        presenter.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //We rely on Databinding to populate most of the widgets
        FragmentCharacterDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false);
        binding.setCharacter(character);

        View rootView = binding.getRoot();
        unbinder = ButterKnife.bind(this, rootView);

        presenter.prepareElements();

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CHARACTER, character);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void mapFullCharacter(SwCharacter character) {
        mapCombinedList(character.getCombineList());
    }

    public void mapHomeworld(SwElement homeworld) {
        kvHomeworld.setValue(homeworld.getDisplayableName());
    }

    @Override
    public void mapCombinedList(List<List<SwGenericElement>> combinedList) {
        for (List<SwGenericElement> swGenericElements : combinedList) {
            //we handle the specific case of the Homeworld which should not be displayed in a list
            if (swGenericElements.get(0).getType().equals("planets")){
                mapHomeworld(swGenericElements.get(0));
                continue;
            }

            //all other types should be handled as lists. The specific case of Species could be handled too, but the api returns them as list so... =)
            final GenericElementsList elementListView = new GenericElementsList(getContext(), swGenericElements);
            llMain.addView(elementListView);
        }
    }
}
