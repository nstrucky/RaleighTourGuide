package com.netjob.raleightourguide.fragments_restaurant_categories;


import android.support.v4.app.Fragment;

import com.netjob.raleightourguide.CategoriesParentFragment;
import com.netjob.raleightourguide.Establishment;
import com.netjob.raleightourguide.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsianFragment extends CategoriesParentFragment {



    public AsianFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initializeEstablishmentArrayList() {
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
    }

}
