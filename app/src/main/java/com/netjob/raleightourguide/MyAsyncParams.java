package com.netjob.raleightourguide;

/**
 * Created by root on 12/29/16.
 */

public class MyAsyncParams {

        BitmapSetter mBitmapSetter;
        Establishment mEstablishment;

        public MyAsyncParams(BitmapSetter bitmapSetter, Establishment establishment) {
            mBitmapSetter = bitmapSetter;
            mEstablishment = establishment;

        }

        public Establishment getEstablishment() {
            return mEstablishment;
        }

        public BitmapSetter getFragment() {
            return mBitmapSetter;

        }


}
