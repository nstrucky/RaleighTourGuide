package com.netjob.raleightourguide;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RestaurantPagerAdapter rpa = new RestaurantPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_restaurants);
        viewPager.setAdapter(rpa);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_restaurants);

        tabLayout.setupWithViewPager(viewPager);


    }
}
