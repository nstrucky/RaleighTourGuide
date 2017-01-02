package com.netjob.raleightourguide.fragments_entertainment_categories;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.netjob.raleightourguide.R;

/**
 * Created by root on 12/30/16.
 */

public class EntertainmentPagerAdapter extends FragmentPagerAdapter {

    private final int ENTERTAINMENT_PAGE_COUNT = 3;
    private String[] pageTitles; /*{"Movie Theaters", "Museums", "Activities"};*/

    public EntertainmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        pageTitles = new String[] {
                context.getString(R.string.ent_cat_movietheatres),
                context.getString(R.string.ent_cat_museums),
                context.getString(R.string.ent_cat_activities)
        };
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new VenuesFragment();

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
