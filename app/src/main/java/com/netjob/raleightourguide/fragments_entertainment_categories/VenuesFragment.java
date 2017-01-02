package com.netjob.raleightourguide.fragments_entertainment_categories;


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
public class VenuesFragment extends CategoriesParentFragment {


    public VenuesFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initializeEstablishmentArrayList() {

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.venue_southlandBallroom),
                getString(R.string.phoneNumber_venue_southlandBallroom),
                getString(R.string.description_southlandBallroom),
                getString(R.string.address_venue_southlandBallroom)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.venue_redhatamp),
                getString(R.string.phoneNumber_venue_redhatamp),
                getString(R.string.description_redhatamp),
                getString(R.string.address_venue_redhatamp)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.venue_pourhouse),
                getString(R.string.phoneNumber_venue_pourhouse),
                getString(R.string.description_pourhouse),
                getString(R.string.address_venue_pourhouse)
        ));
    }
}
