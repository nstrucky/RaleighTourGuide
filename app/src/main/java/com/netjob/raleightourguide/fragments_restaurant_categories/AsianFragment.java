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


        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>();

        restaurantArrayList.add(new Restaurant("Buku",
                getString(R.string.phoneNumber_buku),
                getString(R.string.description_buku),
                R.drawable.dummyimage_restaurant));

        restaurantArrayList.add(new Restaurant("Masu", "612-555-5645", "This is a test", R.drawable.dummyimage_restaurant));

        restaurantArrayList.add(new Restaurant("Sushi Blues",
                getString(R.string.phoneNumber_sushiblues),
                getString(R.string.description_sushiblues),
                R.drawable.dummyimage_restaurant));

        View fragmentRootView = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        ListView listView = (ListView) fragmentRootView.findViewById(R.id.listview_restaurants);

        RestaurantArrayAdapter arrayAdapter = new RestaurantArrayAdapter(getActivity(), restaurantArrayList);

        listView.setAdapter(arrayAdapter);


        return fragmentRootView;

    }

}
