package com.netjob.raleightourguide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by root on 12/28/16.
 */

public class EstablishmentArrayAdapter extends ArrayAdapter {

    ImageView imageView;

    public EstablishmentArrayAdapter(Context context, List list) {
        super(context, 0, list);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        Establishment currentEstablishment = (Establishment) getItem(position);
        Bitmap bitmap = currentEstablishment.getBitmap();

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textView = (TextView) listItemView.findViewById(R.id.textView_restaurant_name);
        TextView textView1 = (TextView) listItemView.findViewById(R.id.textView_restaurant_phone);
        TextView textView2 = (TextView) listItemView.findViewById(R.id.textView_restaurant_description);
        imageView = (ImageView) listItemView.findViewById(R.id.imageView_restaurant_preview);

        textView.setText(currentEstablishment.getName());
        textView1.setText(currentEstablishment.getPhoneNumber());
        textView2.setText(currentEstablishment.getDescription());

        if (bitmap == null) {
            imageView.setImageResource(R.drawable.dummyimage_restaurant);
        } else {
            imageView.setImageBitmap(bitmap);
        }

        return listItemView;
    }



}
