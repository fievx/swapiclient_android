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
public class CharacterDetailFragment extends BaseFragment implements CharacterDetailView {

    //<editor-fold desc="Binds">
    @BindView(R.id.ll_vehicles)
    LinearLayout llVehicles;
    @BindView(R.id.ll_starships)
    LinearLayout llStarships;
    @BindView(R.id.ll_films)
    LinearLayout llFilms;
    @BindView(R.id.kv_homeworld)
    KeyValueView kvHomeworld;
    @BindView(R.id.kv_specie)
    KeyValueView kvSpecie;
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
        //We rely on Databinding to populate most of the windgets
        FragmentCharacterDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false);
        binding.setCharacter(character);

        View rootView = binding.getRoot();
        unbinder = ButterKnife.bind(this, rootView);

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
        mapList(llStarships, character.getStarshipsElement());
        mapList(llVehicles, character.getVehiclesElement());
        mapList(llFilms, character.getFilmsElement());
        kvHomeworld.setValue(character.getHomeworldElement().getDisplayableName());
        kvSpecie.setValue(character.getSpeciesElement().get(0).getDisplayableName());
    }

    private void mapList(LinearLayout layout, List<SwGenericElement> elementList) {
        for (SwElement swElement : elementList) {
            final TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_basic_text, null);
            textView.setText(swElement.getDisplayableName());
            layout.addView(textView);
        }
    }

    public void mapHomeworld(SwElement homeworld) {
        kvHomeworld.setValue(homeworld.getDisplayableName());
    }

    public void mapSpecie(SwElement specie) {
        kvSpecie.setValue(specie.getDisplayableName());
    }
}
