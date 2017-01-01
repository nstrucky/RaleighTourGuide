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
public class HotelsFragment extends CategoriesParentFragment {


    public HotelsFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initializeEstablishmentArrayList() {
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
    }

}
