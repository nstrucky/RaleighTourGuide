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
public class MexicanFragment extends CategoriesParentFragment {


    public MexicanFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initializeEstablishmentArrayList() {
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
    }
}
