package com.netjob.raleightourguide.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.netjob.raleightourguide.AppActivityMethods;
import com.netjob.raleightourguide.LandmarksFragment;
import com.netjob.raleightourguide.R;

public class LandmarksActivity extends AppCompatActivity implements AppActivityMethods {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkNetworkConnection();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.listview_container, new LandmarksFragment())
                .commit();
    }


    @Override
    public void closeProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    @Override
    public void checkNetworkConnection() {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected()) {
            Toast.makeText(this, "Cannot load images, no internet connection", Toast.LENGTH_LONG).show();
        }
    }

}
