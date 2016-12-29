package com.netjob.raleightourguide.fragments_restaurant_categories;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by root on 12/28/16.
 */

public class Restaurant {

    private String mName;
    private String mPhoneNumber;
    private String mDescription;
    private int mPreviewImageID;
    private Uri mAddressUri;
//    private ... mGoogleMapsVariable TODO figure out variable needed to find on maps


//    TODO learn regular expressions and implement for phone number variable
    public Restaurant(String name, String phoneNumber, String description, int previewImageID, String addressUri) {
        mName = name;
        mPhoneNumber = phoneNumber;
        mDescription = description;
        mPreviewImageID = previewImageID;
        mAddressUri = Uri.parse("geo:0,0?q="+addressUri);

    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getPreviewImageID() {
        return mPreviewImageID;
    }

    public void setPreviewImageID(int previewImageID) {
        mPreviewImageID = previewImageID;
    }

    public Uri getAddressUri() {
        return mAddressUri;
    }

    public void setAddressUri(String addressUri) {
        mAddressUri = Uri.parse("geo:0,0?q="+addressUri);
    }

}

