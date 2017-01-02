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
public class SouthernFragment extends CategoriesParentFragment {


    public SouthernFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initializeEstablishmentArrayList() {
        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.rest_driftwood),
                getString(R.string.phoneNumber_rest_drifwood),
                getString(R.string.description_rest_driftwood),
                getString(R.string.address_rest_driftwood)
        ));
    }
}
