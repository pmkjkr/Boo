package com.donga.examples.bumin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.donga.examples.bumin.fragment.AchievFragment;
import com.donga.examples.bumin.fragment.GrageFragment;
import com.donga.examples.bumin.fragment.ScheFragment;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                ScheFragment scheFragment = new ScheFragment();
                return scheFragment;
            case 1:
                GrageFragment grageFragment = new GrageFragment();
                return grageFragment;
            case 2:
                AchievFragment achievFragment = new AchievFragment();
                return achievFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}