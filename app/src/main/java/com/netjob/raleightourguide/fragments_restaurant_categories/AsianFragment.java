package com.netjob.raleightourguide.fragments_restaurant_categories;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.netjob.raleightourguide.R;

import java.net.URI;
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

        restaurantArrayList.add(new Restaurant(
                getString(R.string.buku),
                getString(R.string.phoneNumber_buku),
                getString(R.string.description_buku),
                R.drawable.dummyimage_restaurant,
                getString(R.string.address_buku)));

        restaurantArrayList.add(new Restaurant(
                getString(R.string.davidsDumplings),
                getString(R.string.phoneNumber_davidsDumplings),
                getString(R.string.description_davidsDumplings),
                R.drawable.dummyimage_restaurant,
                getString(R.string.address_davidsDumplings)));

        restaurantArrayList.add(new Restaurant(
                getString(R.string.sushiBlues),
                getString(R.string.phoneNumber_sushiblues),
                getString(R.string.description_sushiblues),
                R.drawable.dummyimage_restaurant,
                getString(R.string.address_sushiBlues)));

        View fragmentRootView = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        final ListView listView = (ListView) fragmentRootView.findViewById(R.id.listview_restaurants);

        RestaurantArrayAdapter arrayAdapter = new RestaurantArrayAdapter(getActivity(), restaurantArrayList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant selectedRestaurant = (Restaurant) listView.getItemAtPosition(position);
                String name = selectedRestaurant.getName();

                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

                Uri restaurantAddressUri = selectedRestaurant.getAddressUri();

                Intent addressIntent = new Intent(Intent.ACTION_VIEW, restaurantAddressUri);
                startActivity(addressIntent);
            }
        });


        return fragmentRootView;

    }

}
