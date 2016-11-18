package com.swapiclient.character_list;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.swapiclient.BaseActivity;

import com.swapiclient.R;
import com.swapiclient.character_detail.CharacterDetailFragment;
import com.swapiclient.model.SwCharacter;
import com.utils.rv_utils.EndlessRecyclerOnScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Characters. This activity
 * has different presentations for handset and tablet-size devices.
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

    private static final String FRAGMENT_DETAIL = "FRAGMENT_DETAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        ButterKnife.bind(this);

        presenter = new CharacterListPresenterImpl();
        presenter.bindView(this);
        presenter.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
    }

    @Override
    public void onRvAdapterReady(RecyclerView.Adapter adapter) {
        rvCharacters.setAdapter(adapter);

        //we attach a scroll listener to enable infinite scrolling
        rvCharacters.addOnScrollListener(new EndlessRecyclerOnScrollListener((LinearLayoutManager) rvCharacters.getLayoutManager(), 10) {
            @Override
            public void onLoadMore(int current_page) {
                presenter.loadNextPage();
            }
        });
    }

    @Override
    public void openCharacterDetail(SwCharacter character) {
        CharacterDetailFragment fragment = CharacterDetailFragment.getInstance(character);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!isTablet){
            transaction.setCustomAnimations(com.utils.R.anim.enter_from_right, 0, 0, com.utils.R.anim.exit_to_right);
        }
        transaction.add(R.id.character_detail_container, fragment, FRAGMENT_DETAIL);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
