package com.netjob.raleightourguide.fragments_restaurant_categories;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.netjob.raleightourguide.R;

/**
 * Created by root on 12/27/16.
 */

public class RestaurantPagerAdapter extends FragmentPagerAdapter {

    private final int RESTAURANT_COUNT = 5;

    private String[] categoryNames;

    public RestaurantPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        categoryNames = new String[] {
                context.getString(R.string.rest_cat_asian),
                context.getString(R.string.rest_cat_italian),
                context.getString(R.string.rest_cat_mexican),
                context.getString(R.string.rest_cat_southern),
                context.getString(R.string.rest_cat_other)
        };
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
