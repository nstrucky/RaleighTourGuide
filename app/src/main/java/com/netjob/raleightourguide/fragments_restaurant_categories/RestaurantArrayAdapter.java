package com.netjob.raleightourguide.fragments_restaurant_categories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.netjob.raleightourguide.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by root on 12/28/16.
 */

public class RestaurantArrayAdapter extends ArrayAdapter {

    ImageView imageView;

    public RestaurantArrayAdapter(Context context, List list) {
        super(context, 0, list);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        key=AIzaSyD03N7BhL74jj6H6Gy-p94NalHbcI3vxAg
//        https://maps.googleapis.com/maps/api/place/nearbysearch/xml?key=AIzaSyD03N7BhL74jj6H6Gy-p94NalHbcI3vxAg&location=35.7754,-78.6382&radius=45000&keyword=buku
//        https://maps.googleapis.com/maps/api/place/photo?maxheight=300&key=AIzaSyD03N7BhL74jj6H6Gy-p94NalHbcI3vxAg&photoreference=CoQBdwAAAIaI6WP-HvIY1CTLbEUCFVFZoWHYwooFXbxriA9KzzDvgU8agvhETHPICBQKi6n0fmixCKcOCvzJetAE86uHo_dZg76zbQMFMfFzU1A3FwZNx2RHSHVkN0tknvuNkMgWBNv_S4Ga9_BHlTEhl0tup9j0FCBjWEgQAJ3daVP0EPolEhAMpA9ZaSWgjGWFanJlaRLPGhS4BfGl1YAbUSNjQE4gVcyZDPkjUg

        View listItemView = convertView;
        Restaurant currentRestaurant = (Restaurant) getItem(position);
        Bitmap bitmap = currentRestaurant.getBitmap();

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textView = (TextView) listItemView.findViewById(R.id.textView_restaurant_name);
        TextView textView1 = (TextView) listItemView.findViewById(R.id.textView_restaurant_phone);
        TextView textView2 = (TextView) listItemView.findViewById(R.id.textView_restaurant_description);
        imageView = (ImageView) listItemView.findViewById(R.id.imageView_restaurant_preview);

        textView.setText(currentRestaurant.getName());
        textView1.setText(currentRestaurant.getPhoneNumber());
        textView2.setText(currentRestaurant.getDescription());

        if (bitmap == null) {
            imageView.setImageResource(R.drawable.dummyimage_restaurant);
        } else {
            imageView.setImageBitmap(bitmap);
        }

        return listItemView;
    }



}
