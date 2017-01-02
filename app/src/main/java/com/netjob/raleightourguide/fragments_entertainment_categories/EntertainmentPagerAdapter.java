package com.netjob.raleightourguide.fragments_entertainment_categories;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by root on 12/30/16.
 */

public class EntertainmentPagerAdapter extends FragmentPagerAdapter {

    private final int ENTERTAINMENT_PAGE_COUNT = 3;
    String[] pageTitls = {"Movie Theaters", "Museums", "Activities"};

    public EntertainmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitls[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new MovieTheaterFragment();

            case 1:
                return new MuseumsFragment();

            case 2:
                return new ActivitiesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return ENTERTAINMENT_PAGE_COUNT;
    }
}
