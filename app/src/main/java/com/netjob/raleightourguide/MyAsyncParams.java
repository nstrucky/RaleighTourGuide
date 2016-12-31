package com.netjob.raleightourguide;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by root on 12/29/16.
 */

public class MyAsyncParams {

        BitmapSetter mBitmapSetter;
        Establishment[] mEstablishmentArray;

        public MyAsyncParams(BitmapSetter bitmapSetter, ArrayList<Establishment> establishment) {

            mBitmapSetter = bitmapSetter;

            Object[] objects = establishment.toArray();
            mEstablishmentArray = new Establishment[objects.length];

            for (int i = 0; i < objects.length; i++) {
                mEstablishmentArray[i] = (Establishment) objects[i];

            }

        }

        public Establishment[] getEstablishmentArray() {
            return mEstablishmentArray;
        }

        public BitmapSetter getFragment() {
            return mBitmapSetter;

        }


}
