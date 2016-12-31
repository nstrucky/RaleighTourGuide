package com.netjob.raleightourguide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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
import java.util.Arrays;

/**
 * Created by root on 12/29/16.
 */




public class PhotoIDAcquisitionTask extends AsyncTask<MyAsyncParams, Void, MyAsyncParams> {

    private final String LOG_TAG = "PhotoAsyncTasks";

    private Establishment[] passedEstablishments = null;
    private MyAsyncParams passedMyAsyncParams = null;


    private MyAsyncParams setPhotoIDs(MyAsyncParams passedMyAsyncParams, int index) {

        Establishment currentEstablishment = passedMyAsyncParams.getEstablishmentArray()[index];

        HttpURLConnection httpURLConnection = null;
        StringBuffer buffer = null;
        BufferedReader bufferedReader = null;
        String jsonToParse = null;

        String keyword_place = currentEstablishment.getName();
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
                Log.e(LOG_TAG, "Could not find results for establishment");
                return null;
            }
            JSONObject photos = results.getJSONObject(0).getJSONArray("photos").getJSONObject(0);
            String photoReferenceID = photos.getString("photo_reference");

            if (photoReferenceID != null) {
                currentEstablishment.setPhotoReferenceID(photoReferenceID);
                return passedMyAsyncParams;
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON Exception", e);
        }

        return null;
    }

    @Override
    protected MyAsyncParams doInBackground(MyAsyncParams... params) {

        passedMyAsyncParams = params[0];
        passedEstablishments = passedMyAsyncParams.getEstablishmentArray();
        ArrayList<String> photoIDArrayList = new ArrayList<>();

        for (int i = 0; i < passedEstablishments.length; i++) {
            setPhotoIDs(passedMyAsyncParams, i);
        }



        return passedMyAsyncParams;
    }//doInBackground()

    @Override
    protected void onPostExecute(MyAsyncParams myAsyncParams) {
        super.onPostExecute(myAsyncParams);

        PhotoRetrievalTask photoRetrievalTask = new PhotoRetrievalTask();
        photoRetrievalTask.execute(myAsyncParams);
    }
}



class PhotoRetrievalTask extends AsyncTask<MyAsyncParams, Void, Bitmap[]> {

    MyAsyncParams passedMyAsyncParams = null;
    Establishment[] passedEstablishments = null;
    BitmapSetter passedBitmapSetter = null;

    private final String LOG_TAG = "PhotoRetrievalTask";



    @Override
    protected Bitmap[] doInBackground(MyAsyncParams... params) {

        passedMyAsyncParams = params[0];
        passedBitmapSetter = passedMyAsyncParams.getFragment();
        passedEstablishments = passedMyAsyncParams.getEstablishmentArray();
        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        for (int i = 0; i < passedEstablishments.length; i++) {
            setBitMap(passedEstablishments, i);
        }

        return new Bitmap[4];

    }

    private Bitmap setBitMap(Establishment[] passedEstablishments, int index) {

        Establishment currentEstablishment = passedEstablishments[index];//get establishment array instead?

        URL url = null;
        Bitmap bitmap = null;
        HttpURLConnection httpURLConnection = null;


        final String BASE_URL = "https://maps.googleapis.com/maps/api/place/photo?";
        final String PARAM_KEY = "key";
        final String PARAM_PHOTOREFERENCE = "photoreference";
        final String PARAM_MAXHEIGHT = "maxheight";
        final String PARAM_MINHEIGHT = "minheight";
        String key = "AIzaSyD03N7BhL74jj6H6Gy-p94NalHbcI3vxAg";
        String photoID = currentEstablishment.getPhotoReferenceID();
        String maxheight = "800";
        String minheight = "600";

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_KEY, key)
                .appendQueryParameter(PARAM_PHOTOREFERENCE, photoID)
                .appendQueryParameter(PARAM_MAXHEIGHT, maxheight)
                .appendQueryParameter(PARAM_MINHEIGHT, minheight)
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


//            TODO handle no internet connection
            InputStream inputStream = httpURLConnection.getInputStream();

            bitmap = BitmapFactory.decodeStream(inputStream);
            currentEstablishment.setBitmap(bitmap);

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
    protected void onPostExecute(Bitmap[] bitmap) {
        super.onPostExecute(bitmap);

        if (bitmap != null) {
            Log.i("BITMAP IN POSTEXEC", "NOT NULL");
        }
//        passedBitmapSetter.setEstBitmap(bitmap, passedEstablishments);

        passedBitmapSetter.setEstBitmap(passedEstablishments);
    }
}