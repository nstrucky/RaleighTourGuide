package com.netjob.raleightourguide;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.net.ConnectivityManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.CacheResponse;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesParentFragment extends Fragment {

    protected ArrayList<Establishment> mEstablishmentArrayList;
    protected ListView listView;
    protected EstablishmentArrayAdapter arrayAdapter;

    public CategoriesParentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentRootView = inflater.inflate(R.layout.fragment_establishment_list, container, false);

        listView = (ListView) fragmentRootView.findViewById(R.id.listview_establishments);

        mEstablishmentArrayList = new ArrayList<>();
        initializeEstablishmentArrayList();

        PhotoIDAcquisitionTask photoIDAcquisition = new PhotoIDAcquisitionTask();
        photoIDAcquisition.execute(new MyAsyncParams(this, mEstablishmentArrayList));

        arrayAdapter = new EstablishmentArrayAdapter(getActivity(), mEstablishmentArrayList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Establishment selectedEstablishment = (Establishment) listView.getItemAtPosition(position);
                Toast.makeText(getActivity(), selectedEstablishment.getName(), Toast.LENGTH_SHORT).show();
                Uri restaurantAddressUri = selectedEstablishment.getAddressUri();
                Intent addressIntent = new Intent(Intent.ACTION_VIEW, restaurantAddressUri);
                startActivity(addressIntent);
            }
        });

        return fragmentRootView;
    }

    protected void initializeEstablishmentArrayList() {
        //Override in subclasses to set up data

    }

    public void setEstBitmap(Establishment[] establishments) {
        mEstablishmentArrayList.clear();
        mEstablishmentArrayList.addAll(Arrays.asList(establishments));
        listView.setAdapter(arrayAdapter);

    }

    public void closeActivityLoadingDialog() {
        CategoryActivityMethods categoryActivity =
                (CategoryActivityMethods) getActivity();

        categoryActivity.closeProgressDialog();

    }

}
