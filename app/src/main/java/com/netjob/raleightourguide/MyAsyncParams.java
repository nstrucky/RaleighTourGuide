package com.netjob.raleightourguide;

import java.util.ArrayList;

/**
 * Created by root on 12/29/16.
 */

public class MyAsyncParams {

        CategoriesParentFragment mFragment;
        Establishment[] mEstablishmentArray;

        public MyAsyncParams(CategoriesParentFragment bitmapSetter, ArrayList<Establishment> establishment) {

            mFragment = bitmapSetter;

            Object[] objects = establishment.toArray();
            mEstablishmentArray = new Establishment[objects.length];

            for (int i = 0; i < objects.length; i++) {
                mEstablishmentArray[i] = (Establishment) objects[i];

            }

        }

        public Establishment[] getEstablishmentArray() {
            return mEstablishmentArray;
        }

        public CategoriesParentFragment getFragment() {
            return mFragment;

        }


}
