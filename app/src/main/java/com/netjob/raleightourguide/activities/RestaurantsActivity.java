package com.netjob.raleightourguide.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.netjob.raleightourguide.R;
import com.netjob.raleightourguide.RestaurantPagerAdapter;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_tablayout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RestaurantPagerAdapter rpa = new RestaurantPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(rpa);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);

        tabLayout.setupWithViewPager(viewPager);


    }
}
