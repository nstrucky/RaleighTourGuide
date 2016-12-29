package com.netjob.raleightourguide.fragments_restaurant_categories;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * A simple {@link Fragment} subclass.
 */


public class AsianFragment extends Fragment {

    String mPhotoReferenceID = "placeholder";
    Bitmap mBitmap;
    ArrayList<Restaurant> mRestaurantArrayList;

    public AsianFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void photoTasks(String restaurantName) {
        PhotoIDAcquisitionTask photoIDAcquisition = new PhotoIDAcquisitionTask();
        photoIDAcquisition.execute(restaurantName);
//        Log.i("PHOTO ID BEFORE EXEC2", mPhotoReferenceID);




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

        photoTasks(mRestaurantArrayList.get(0).getName());


        final ListView listView = (ListView) fragmentRootView.findViewById(R.id.listview_restaurants);

        RestaurantArrayAdapter arrayAdapter = new RestaurantArrayAdapter(getActivity(), mRestaurantArrayList);

        listView.setAdapter(arrayAdapter);
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



    class PhotoIDAcquisitionTask extends AsyncTask<String, Void, String> {


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
                if (results.length() < 1) {
                    Log.e("JSON ERROR", "Could not find results for " + keyword_place);
                    return null;
                }
                JSONObject photos = results.getJSONObject(0).getJSONArray("photos").getJSONObject(0);
                String photoReferenceID = photos.getString("photo_reference");
                Log.i("PHOTO ID AFTER PARSE", photoReferenceID);//++++++++++++++++++++++++++++++++++++++++

                if (photoReferenceID != null) {
                    Log.i("PHOTO ID BEFORE RETURN", photoReferenceID);//++++++++++++++++++++++++++++++++++
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

            PhotoRetrievalTask photoRetrievalTask = new PhotoRetrievalTask();

            photoRetrievalTask.execute(s);

            Log.i("PHOTO ID POST EXEC1", s);//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//            mPhotoReferenceID = s;
//            if (mPhotoReferenceID != null)
//                Log.i("PHOTO ID", mPhotoReferenceID);
        }
    }

    class PhotoRetrievalTask extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {

            URL url = null;
            Bitmap bitmap = null;
//            HttpURLConnection httpURLConnection = null;
            String photoID = params[0];
            if (photoID != null)
            Log.i("PHOTO ID TASK2", photoID);

            try {
                url = new URL("https://maps.googleapis.com/maps/api/place/photo?maxheight=800&key=AIzaSyD03N7BhL74jj6H6Gy-p94NalHbcI3vxAg&photoreference="+photoID);
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("GET");
//                httpURLConnection.connect();
//
//                InputStream inputStream = httpURLConnection.getInputStream();

                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            } catch (MalformedURLException e) {
                Log.e("RestaurantArrayAdapter", "Malformed URL Exception", e);
            } catch (IOException e) {
                Log.e("RestaurantArrayAdapter", "IO URL Exception", e);
            } finally {
//                if (httpURLConnection != null) {
//                    httpURLConnection.disconnect();
//
//                }

            }

                return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (bitmap != null) {
                Log.i("BITMAP IN POSTEXEC", "NOT NULL");
            }
            setBitMap(bitmap);

        }
    }

    public void setBitMap(Bitmap bitMap) {

        Log.i("BITMAP", bitMap.toString());
        mRestaurantArrayList.get(0).setBitmap(bitMap);
    }

}
