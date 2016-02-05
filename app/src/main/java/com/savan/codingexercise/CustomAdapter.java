package com.savan.codingexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Savan on 2/4/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context,ArrayList<String> tag)
    {
        super(context,R.layout.list_view_data,tag);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View viewCustom = inflator.inflate(R.layout.list_view_data, parent, false);

        ImageView logo =(ImageView)viewCustom.findViewById(R.id.logo);
        TextView name     =  (TextView) viewCustom.findViewById(R.id.name);
        TextView phone     =  (TextView) viewCustom.findViewById(R.id.phone);
        TextView address     =  (TextView) viewCustom.findViewById(R.id.address);

        name.setText(MainActivity.nameArray.get(position));
        phone.setText(MainActivity.phoneArray.get(position));
        address.setText(MainActivity.addArray.get(position));

        return viewCustom;
    }


}
