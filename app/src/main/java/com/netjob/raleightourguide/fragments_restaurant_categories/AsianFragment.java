package com.netjob.raleightourguide.fragments_restaurant_categories;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.netjob.raleightourguide.BitmapSetter;
import com.netjob.raleightourguide.Establishment;
import com.netjob.raleightourguide.EstablishmentArrayAdapter;
import com.netjob.raleightourguide.MyAsyncParams;
import com.netjob.raleightourguide.PhotoIDAcquisitionTask;
import com.netjob.raleightourguide.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */


public class AsianFragment extends Fragment implements BitmapSetter {

    private final String LOG_TAG = "AsianFragment";
    ArrayList<Establishment> mEstablishmentArrayList;
    ListView listView;
    EstablishmentArrayAdapter arrayAdapter;

    public AsianFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentRootView = inflater.inflate(R.layout.fragment_establishment_list, container, false);

        mEstablishmentArrayList = new ArrayList<>();

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.buku),
                getString(R.string.phoneNumber_rest_buku),
                getString(R.string.description_rest_buku),
                getString(R.string.address_rest_buku)));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.davidsDumplings),
                getString(R.string.phoneNumber_rest_davidsDumplings),
                getString(R.string.description_rest_davidsDumplings),
                getString(R.string.address_rest_davidsDumplings)));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.sushiBlues),
                getString(R.string.phoneNumber_rest_sushiblues),
                getString(R.string.description_rest_sushiblues),
                getString(R.string.address_rest_sushiBlues)));

        for (Establishment establishment : mEstablishmentArrayList) {

            PhotoIDAcquisitionTask photoIDAcquisition = new PhotoIDAcquisitionTask();
            photoIDAcquisition.execute(new MyAsyncParams(this, establishment));
        }

        listView = (ListView) fragmentRootView.findViewById(R.id.listview_establishments);

        arrayAdapter = new EstablishmentArrayAdapter(getActivity(), mEstablishmentArrayList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Establishment selectedEstablishment = (Establishment) listView.getItemAtPosition(position);
                String name = selectedEstablishment.getName();

                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

                Uri restaurantAddressUri = selectedEstablishment.getAddressUri();

                Intent addressIntent = new Intent(Intent.ACTION_VIEW, restaurantAddressUri);
                startActivity(addressIntent);
            }
        });

        return fragmentRootView;
    }

    @Override
    public void setEstBitmap(Bitmap bitMap, Establishment establishment) {

        establishment.setBitmap(bitMap);
        listView.setAdapter(arrayAdapter);
    }

}
