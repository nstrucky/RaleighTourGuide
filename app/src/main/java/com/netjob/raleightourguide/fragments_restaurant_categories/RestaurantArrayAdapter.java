package com.netjob.raleightourguide.fragments_restaurant_categories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.netjob.raleightourguide.R;

import java.util.List;

/**
 * Created by root on 12/28/16.
 */

public class RestaurantArrayAdapter extends ArrayAdapter {

//    private String mRestaurantName;
//    private String mRestaurantDescription;


    public RestaurantArrayAdapter(Context context, List list) {
        super(context, 0, list);

//        mRestaurantName = name;
//        mRestaurantDescription = description;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        Restaurant currentRestaurant = (Restaurant) getItem(position);

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
}
