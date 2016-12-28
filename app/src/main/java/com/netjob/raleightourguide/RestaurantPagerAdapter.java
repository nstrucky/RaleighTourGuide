package com.netjob.raleightourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.netjob.raleightourguide.fragments_restaurant_categories.AsianFragment;
import com.netjob.raleightourguide.fragments_restaurant_categories.ItalianFragment;
import com.netjob.raleightourguide.fragments_restaurant_categories.MexicanFragment;
import com.netjob.raleightourguide.fragments_restaurant_categories.OtherFragment;
import com.netjob.raleightourguide.fragments_restaurant_categories.SouthernFragment;

/**
 * Created by root on 12/27/16.
 */

public class RestaurantPagerAdapter extends FragmentPagerAdapter {

    private final int RESTAURANT_COUNT = 5;
    private final String[] categoryNames = {"Asian", "Italian", "Mexican", "Southern", "Other"};

    public RestaurantPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryNames[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new AsianFragment();

            case 1:
                return new ItalianFragment();

            case 2:
                return new MexicanFragment();

            case 3:
                return new SouthernFragment();

            case 4:
                return new OtherFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return RESTAURANT_COUNT;
    }
}
