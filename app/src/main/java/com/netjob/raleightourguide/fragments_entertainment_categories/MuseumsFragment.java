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
public class MuseumsFragment extends CategoriesParentFragment {


    public MuseumsFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initializeEstablishmentArrayList() {

        mEstablishmentArrayList.add(new Establishment(
            getString(R.string.muse_ncmusNatSci),
                getString(R.string.phoneNumber_muse_ncmuseNatSci),
                getString(R.string.description_musncNatSci),
                getString(R.string.address_muse_ncmuseNatSci)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.muse_ncmuseHist),
                getString(R.string.phoneNumber_muse_ncmuseHist),
                getString(R.string.description_muse_ncmuseHist),
                getString(R.string.address_muse_ncmuseHist)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.muse_ncmuseArt),
                getString(R.string.phoneNumber_muse_ncmuseArt),
                getString(R.string.description_muse_ncmuseArt),
                getString(R.string.address_muse_ncmuseArt)
        ));


    }
}
