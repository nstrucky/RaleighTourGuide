package com.netjob.raleightourguide.fragments_restaurant_categories;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * A simple {@link Fragment} subclass.
 */


public class AsianFragment extends Fragment {

    private final String LOG_TAG = "AsianFragment";
    ArrayList<Restaurant> mRestaurantArrayList;
    ListView listView;
    RestaurantArrayAdapter arrayAdapter;

    public AsianFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentRootView = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        mRestaurantArrayList = new ArrayList<>();

        mRestaurantArrayList.add(new Restaurant(
                getString(R.string.buku),
                getString(R.string.phoneNumber_buku),
                getString(R.string.description_buku),
                getString(R.string.address_buku)));

        mRestaurantArrayList.add(new Restaurant(
                getString(R.string.davidsDumplings),
                getString(R.string.phoneNumber_davidsDumplings),
                getString(R.string.description_davidsDumplings),
                getString(R.string.address_davidsDumplings)));

        mRestaurantArrayList.add(new Restaurant(
                getString(R.string.sushiBlues),
                getString(R.string.phoneNumber_sushiblues),
                getString(R.string.description_sushiblues),
                getString(R.string.address_sushiBlues)));

        for (Restaurant restaurant : mRestaurantArrayList) {

            PhotoIDAcquisitionTask photoIDAcquisition = new PhotoIDAcquisitionTask();
            photoIDAcquisition.execute(restaurant);
        }

        listView = (ListView) fragmentRootView.findViewById(R.id.listview_restaurants);

        arrayAdapter = new RestaurantArrayAdapter(getActivity(), mRestaurantArrayList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant selectedRestaurant = (Restaurant) listView.getItemAtPosition(position);
                String name = selectedRestaurant.getName();

                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

                Uri restaurantAddressUri = selectedRestaurant.getAddressUri();

                Intent addressIntent = new Intent(Intent.ACTION_VIEW, restaurantAddressUri);
                startActivity(addressIntent);
            }
        });

        return fragmentRootView;
    }

    class PhotoIDAcquisitionTask extends AsyncTask<Restaurant, Void, Restaurant> {

        Restaurant passedRestaurant = null;

        @Override
        protected Restaurant doInBackground(Restaurant... params) {
            passedRestaurant = params[0];
            HttpURLConnection httpURLConnection = null;
            StringBuffer buffer = null;
            BufferedReader bufferedReader = null;
            String jsonToParse = null;

            String keyword_place = passedRestaurant.getName();
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
                Log.i(LOG_TAG, jsonToParse);

            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Malformed URL Exception", e);
            } catch (IOException e) {
                Log.e(LOG_TAG, "IO URL Exception", e);
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
                if (results.length() < 1) {
                    Log.e(LOG_TAG, "Could not find results for " + keyword_place);
                    return null;
                }
                JSONObject photos = results.getJSONObject(0).getJSONArray("photos").getJSONObject(0);
                String photoReferenceID = photos.getString("photo_reference");

                if (photoReferenceID != null) {
                    passedRestaurant.setPhotoReferenceID(photoReferenceID);
                    return passedRestaurant;
                }

            } catch (JSONException e) {
                Log.e(LOG_TAG, "JSON Exception", e);
            }
            return null;
        }//doInBackground()

        @Override
        protected void onPostExecute(Restaurant s) {
            super.onPostExecute(s);

            PhotoRetrievalTask photoRetrievalTask = new PhotoRetrievalTask();
            photoRetrievalTask.execute(s);
        }
    }

    class PhotoRetrievalTask extends AsyncTask<Restaurant, Void, Bitmap> {
        Restaurant passedRestaurant = null;

        @Override
        protected Bitmap doInBackground(Restaurant... params) {

            passedRestaurant = params[0];
            URL url = null;
            Bitmap bitmap = null;
            HttpURLConnection httpURLConnection = null;


            final String BASE_URL = "https://maps.googleapis.com/maps/api/place/photo?";
            final String PARAM_KEY = "key";
            final String PARAM_PHOTOREFERENCE = "photoreference";
            final String PARAM_MAXHEIGHT = "maxheight";
            String key = "AIzaSyD03N7BhL74jj6H6Gy-p94NalHbcI3vxAg";
            String photoID = passedRestaurant.getPhotoReferenceID();
            String maxheight = "800";


            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(PARAM_KEY, key)
                    .appendQueryParameter(PARAM_PHOTOREFERENCE, photoID)
                    .appendQueryParameter(PARAM_MAXHEIGHT, maxheight)
                    .build();

            try {
                /*
                 *   Reference for google place api URL:
                 *  https://maps.googleapis.com/maps/api/place/photo?maxheight=800&key=AIzaSyD03N7BhL74jj6H6Gy-p94NalHbcI3vxAg&photoreference=[photoID]
                 */
                url = new URL(builtUri.toString());
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();

                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Malformed URL Exception", e);
            } catch (IOException e) {
                Log.e(LOG_TAG, "IO URL Exception", e);
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (bitmap != null) {
                Log.i("BITMAP IN POSTEXEC", "NOT NULL");
            }
            setRestBitmap(bitmap, passedRestaurant);

        }
    }

    public void setRestBitmap(Bitmap bitMap, Restaurant restaurant) {

        restaurant.setBitmap(bitMap);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
