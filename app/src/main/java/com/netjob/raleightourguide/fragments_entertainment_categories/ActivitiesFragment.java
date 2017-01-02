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
public class ActivitiesFragment extends CategoriesParentFragment {


    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initializeEstablishmentArrayList() {

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.act_skyzone),
                getString(R.string.phoneNumber_act_skyzone),
                getString(R.string.description_act_skyzone),
                getString(R.string.address_act_skyzone)
        ));
    }
}
