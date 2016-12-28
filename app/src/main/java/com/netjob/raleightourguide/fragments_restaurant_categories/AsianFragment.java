package com.netjob.raleightourguide.fragments_restaurant_categories;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.netjob.raleightourguide.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsianFragment extends Fragment {


    public AsianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ArrayList<Restaurant> testArrayList = new ArrayList<>();

        testArrayList.add(new Restaurant("Buku", "919-555-6512", "This is a test", R.drawable.dummyimage_restaurant));
        testArrayList.add(new Restaurant("Masu", "612-555-5645", "This is a test", R.drawable.dummyimage_restaurant));
        testArrayList.add(new Restaurant("Sushi Blues", "919-555-4521", "This is a test", R.drawable.dummyimage_restaurant));

        View fragmentRootView = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        ListView listView = (ListView) fragmentRootView.findViewById(R.id.listview_restaurants);

        RestaurantArrayAdapter arrayAdapter = new RestaurantArrayAdapter(getActivity(), testArrayList);

        listView.setAdapter(arrayAdapter);


        return fragmentRootView;

    }

}
