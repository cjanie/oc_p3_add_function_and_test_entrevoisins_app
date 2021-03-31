package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.container)
    ViewPager mViewPager;

    private ListNeighbourPagerAdapter mListPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);
        setSupportActionBar(this.mToolbar);

        // List handling in the ViewPager container: neighbours or favorites controlled in ListPagerAdapter
        this.mListPagerAdapter = new ListNeighbourPagerAdapter(this.getApplicationContext(), this.getSupportFragmentManager(), this.mTabLayout.getTabCount());
        this.mViewPager.setAdapter(mListPagerAdapter);
        this.mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.mTabLayout));
        this.mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(this.mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
        });



    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }
}
