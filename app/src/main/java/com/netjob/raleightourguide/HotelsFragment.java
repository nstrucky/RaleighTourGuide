package com.netjob.raleightourguide;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelsFragment extends Fragment implements BitmapSetter {

    ArrayList<Establishment> mEstablishmentArrayList;
    ListView listView;
    EstablishmentArrayAdapter mEstablishmentArrayAdapter;

    public HotelsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentRootView = inflater.inflate(R.layout.fragment_establishment_list, container, false);
        listView = (ListView) fragmentRootView.findViewById(R.id.listview_establishments);

        mEstablishmentArrayList = new ArrayList<>();

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.hotel_sheraton),
                getString(R.string.phoneNumber_hotel_sheraton),
                getString(R.string.description_hotel_sheraton),
                getString(R.string.address_hotel_sheraton)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.hotel_holidaydowntown),
                getString(R.string.phoneNumber_hotel_holidaydowntown),
                getString(R.string.description_hotel_holidaydowntown),
                getString(R.string.address_hotel_holidadowntown)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.hotel_marriottcitycenter),
                getString(R.string.phoneNumber_hotel_marriottcitycenter),
                getString(R.string.description_hotel_marriottcitycenter),
                getString(R.string.address_hotel_marriottcitycenter)
        ));

        mEstablishmentArrayAdapter = new EstablishmentArrayAdapter(getContext(), mEstablishmentArrayList);

            PhotoIDAcquisitionTask photoIDAcquisitionTask = new PhotoIDAcquisitionTask();
            photoIDAcquisitionTask.execute(new MyAsyncParams(this, mEstablishmentArrayList));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Establishment establishment = (Establishment) listView.getItemAtPosition(position);
                Uri establishmentUri = establishment.getAddressUri();
                Toast.makeText(getContext(), establishment.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, establishmentUri);
                startActivity(intent);
            }
        });

        return fragmentRootView;
    }

    @Override
    public void setEstBitmap(Establishment[] establishments) {

        mEstablishmentArrayList.clear();

        mEstablishmentArrayList.addAll(Arrays.asList(establishments));

        listView.setAdapter(mEstablishmentArrayAdapter);

//        establishment.setBitmap(bitMap);
//        listView.setAdapter(arrayAdapter);
    }
}
