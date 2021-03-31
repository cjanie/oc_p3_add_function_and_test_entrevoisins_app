package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class ListFavoritePagerAdapter extends ListPagerAdapter {

    public ListFavoritePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return FavoriteFragment.newInstance();
    }
}
