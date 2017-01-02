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
public class ItalianFragment extends CategoriesParentFragment {


    public ItalianFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initializeEstablishmentArrayList() {

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
    }
}
