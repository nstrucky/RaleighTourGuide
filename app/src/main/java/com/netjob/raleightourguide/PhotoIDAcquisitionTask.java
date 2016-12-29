package com.netjob.raleightourguide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.netjob.raleightourguide.fragments_restaurant_categories.Establishment;

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

/**
 * Created by root on 12/29/16.
 */




public class PhotoIDAcquisitionTask extends AsyncTask<MyAsyncParams, Void, MyAsyncParams> {

    private final String LOG_TAG = "PhotoAsyncTasks";

    Establishment passedEstablishment = null;
    BitmapSetter passedBitmapSetter = null;
    MyAsyncParams passedMyAsyncParams = null;

    @Override
    protected MyAsyncParams doInBackground(MyAsyncParams... params) {

        passedMyAsyncParams = params[0];
        passedEstablishment = passedMyAsyncParams.getEstablishment();
        passedBitmapSetter = passedMyAsyncParams.getFragment();

        HttpURLConnection httpURLConnection = null;
        StringBuffer buffer = null;
        BufferedReader bufferedReader = null;
        String jsonToParse = null;

        String keyword_place = passedEstablishment.getName();
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
                passedEstablishment.setPhotoReferenceID(photoReferenceID);
                return passedMyAsyncParams;
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON Exception", e);
        }
        return null;
    }//doInBackground()

    @Override
    protected void onPostExecute(MyAsyncParams s) {
        super.onPostExecute(s);

        PhotoRetrievalTask photoRetrievalTask = new PhotoRetrievalTask();
        photoRetrievalTask.execute(s);
    }
}

class PhotoRetrievalTask extends AsyncTask<MyAsyncParams, Void, Bitmap> {

    MyAsyncParams passedMyAsyncParams = null;
    Establishment passedEstablishment = null;
    BitmapSetter passedBitmapSetter = null;

    private final String LOG_TAG = "PhotoRetrievalTask";

    @Override
    protected Bitmap doInBackground(MyAsyncParams... params) {

        passedMyAsyncParams = params[0];
        passedEstablishment = params[0].getEstablishment();
        passedBitmapSetter = params[0].getFragment();

        URL url = null;
        Bitmap bitmap = null;
        HttpURLConnection httpURLConnection = null;


        final String BASE_URL = "https://maps.googleapis.com/maps/api/place/photo?";
        final String PARAM_KEY = "key";
        final String PARAM_PHOTOREFERENCE = "photoreference";
        final String PARAM_MAXHEIGHT = "maxheight";
        String key = "AIzaSyD03N7BhL74jj6H6Gy-p94NalHbcI3vxAg";
        String photoID = passedEstablishment.getPhotoReferenceID();
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
        passedBitmapSetter.setEstBitmap(bitmap, passedEstablishment);

    }
}