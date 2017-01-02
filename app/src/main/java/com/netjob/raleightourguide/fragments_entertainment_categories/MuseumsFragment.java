package com.netjob.raleightourguide.fragments_entertainment_categories;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.netjob.raleightourguide.CategoriesParentFragment;
import com.netjob.raleightourguide.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MuseumsFragment extends CategoriesParentFragment {


    public MuseumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("Museums!");
        return textView;
    }

}
