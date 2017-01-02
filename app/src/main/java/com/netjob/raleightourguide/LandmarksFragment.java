package com.netjob.raleightourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LandmarksFragment extends CategoriesParentFragment {


    public LandmarksFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initializeEstablishmentArrayList() {

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.landmark_nccapitol),
                getString(R.string.phoneNumber_landmark_nccapitol),
                getString(R.string.description_landmark_nccapitol),
                getString(R.string.address_landmark_nccapitol)
        ));

        mEstablishmentArrayList.add(new Establishment(
                getString(R.string.landmark_govmansion),
                getString(R.string.phoneNumer_govmansion),
                getString(R.string.description_govmansion),
                getString(R.string.address_landmark_govmansion)
        ));

    }
}
