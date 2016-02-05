package com.savan.codingexercise;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Savan on 2/4/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    Bitmap bitmap;
    ImageView logo;


    CustomAdapter(Context context,ArrayList<String> tag)
    {
        super(context,R.layout.list_view_data,tag);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View viewCustom = inflator.inflate(R.layout.list_view_data, parent, false);

        logo =(ImageView)viewCustom.findViewById(R.id.logo);
        TextView name     =  (TextView) viewCustom.findViewById(R.id.name);
        TextView phone     =  (TextView) viewCustom.findViewById(R.id.phone);
        TextView address     =  (TextView) viewCustom.findViewById(R.id.address);

        name.setText(MainActivity.nameArray.get(position));
        phone.setText(MainActivity.phoneArray.get(position));
        address.setText(MainActivity.addArray.get(position));

        Picasso.with(getContext()).load(MainActivity.logoArray.get(position)).fit().into(logo);
        logo.setDrawingCacheEnabled(true);
        logo.buildDrawingCache(true);

       return viewCustom;
    }




}
