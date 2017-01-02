package com.netjob.raleightourguide.fragments_restaurant_categories;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.netjob.raleightourguide.CategoriesParentFragment;
import com.netjob.raleightourguide.Establishment;
import com.netjob.raleightourguide.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestAllFragment extends CategoriesParentFragment {


    public RestAllFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initializeEstablishmentArrayList() {
        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.buku),
                getString(R.string.phoneNumber_rest_buku),
                getString(R.string.description_rest_buku),
                getString(R.string.address_rest_buku)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.davidsDumplings),
                getString(R.string.phoneNumber_rest_davidsDumplings),
                getString(R.string.description_rest_davidsDumplings),
                getString(R.string.address_rest_davidsDumplings)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.sushiBlues),
                getString(R.string.phoneNumber_rest_sushiblues),
                getString(R.string.description_rest_sushiblues),
                getString(R.string.address_rest_sushiBlues)
        ));


        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.rest_amedeos),
                getString(R.string.phoneNumber_rest_amedeos),
                getString(R.string.description_rest_amedeos),
                getString(R.string.address_rest_amedeos)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.rest_brioTuscan),
                getString(R.string.phoneNumber_rest_brioTuscan),
                getString(R.string.description_rest_brioTuscan),
                getString(R.string.address_rest_brioTuscan)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.rest_casaCarbone),
                getString(R.string.phoneNumber_rest_casaCarbone),
                getString(R.string.description_rest_casaCarbone),
                getString(R.string.address_rest_casaCarbone)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.rest_chubbytacos),
                getString(R.string.phoneNumber_rest_chubbytacos),
                getString(R.string.description_rest_chubbytacos),
                getString(R.string.address_rest_chubbytacos)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.rest_elrodeo),
                getString(R.string.phoneNumber_rest_elrodeo),
                getString(R.string.description_rest_elrodeo),
                getString(R.string.address_rest_elrodeo)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.rest_driftwood),
                getString(R.string.phoneNumber_rest_drifwood),
                getString(R.string.description_rest_driftwood),
                getString(R.string.address_rest_driftwood)
        ));

    }

}
