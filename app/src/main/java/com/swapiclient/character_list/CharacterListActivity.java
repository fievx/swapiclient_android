package com.swapiclient.character_list;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.swapiclient.BaseActivity;
import com.swapiclient.character_detail.CharacterDetailActivity;
import com.swapiclient.R;
import com.swapiclient.model.SwCharacter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Characters. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CharacterDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class CharacterListActivity extends BaseActivity <CharacterListPresenter> implements CharacterListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.character_list)
    RecyclerView rvCharacters;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        ButterKnife.bind(this);

        presenter = new CharacterListPresenterImpl();
        presenter.bindView(this);

        isTablet = getResources().getBoolean(R.bool.isTablet);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onRvAdapterReady(RecyclerView.Adapter adapter) {
        rvCharacters.setAdapter(adapter);
    }

    @Override
    public void openCharacterDetail(SwCharacter character) {

    }
}
