package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public ListNeighbourPagerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    /**
     * To get fragment corresponding to tab
     * getItem is called to return an instantiated fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return NeighbourFragment.newInstance();
            case 1:
                return FavoriteFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.totalTabs;
    }


}
