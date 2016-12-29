package com.netjob.raleightourguide.fragments_restaurant_categories;

import android.content.Context;
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

    private String mPhotoReferenceID = null;

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
        String restaurantName = currentRestaurant.getName();

        PhotoAcquisitionTask photoAcquisition = new PhotoAcquisitionTask();
        photoAcquisition.execute(restaurantName);
//        Log.i("PHOTO ID", mPhotoReferenceID.toString());



        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textView = (TextView) listItemView.findViewById(R.id.textView_restaurant_name);
        TextView textView1 = (TextView) listItemView.findViewById(R.id.textView_restaurant_phone);
        TextView textView2 = (TextView) listItemView.findViewById(R.id.textView_restaurant_description);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView_restaurant_preview);

        textView.setText(currentRestaurant.getName());
        textView1.setText(currentRestaurant.getPhoneNumber());
        textView2.setText(currentRestaurant.getDescription());
        imageView.setImageResource(currentRestaurant.getPreviewImageID());

        return listItemView;
    }


    private class PhotoAcquisitionTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;
            StringBuffer buffer = null;
            BufferedReader bufferedReader = null;
            String jsonToParse = null;

            String keyword_place = params[0];
            final String PARAM_KEY = "key";
            final String PARAM_LOCATION = "location";
            final String PARAM_KEYWORD = "keyword";
            final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
            final String KEY = "AIzaSyD03N7BhL74jj6H6Gy-p94NalHbcI3vxAg";
            final String LOCATION = "35.7754,-78.6382";

            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(PARAM_KEY, KEY)
                    .appendQueryParameter(PARAM_LOCATION, LOCATION)
                    .appendQueryParameter(PARAM_KEYWORD, keyword_place)
                    .build();

            Log.i("URI!!!!", builtUri.toString());

            try {
                URL url = new URL(builtUri.toString());

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                buffer = new StringBuffer();


                if (inputStream == null) {
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                jsonToParse = buffer.toString();
                Log.i("JSONTOPARSE:", jsonToParse);


            } catch (MalformedURLException e) {
                Log.e("RestaurantArrayAdapter", "Malformed URL Exception", e);
            } catch (IOException e) {
                Log.e("RestaurantArrayAdapter", "IO URL Exception", e);
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {

                    }

                }
            }

            try {
                JSONObject jsonObject = new JSONObject(jsonToParse);

                JSONArray results = jsonObject.getJSONArray("results");
                JSONObject photos = results.getJSONObject(0).getJSONArray("photos").getJSONObject(0);
                String photoReferenceID = photos.getString("photo_reference");

                if (photoReferenceID != null) {
                    return photoReferenceID;
                }


            } catch (JSONException e) {
                Log.e("RestaurantArrayAdapter", "JSON Exception", e);
            }
            return null;
        }//doInBackground()

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            mPhotoReferenceID = s;
            if (mPhotoReferenceID != null)
            Log.i("PHOTO ID", mPhotoReferenceID.toString());
        }
    }
}
