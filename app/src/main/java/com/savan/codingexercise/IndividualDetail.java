package com.savan.codingexercise;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class IndividualDetail extends AppCompatActivity {
    ImageView logo;
    TextView name,city,latitude,longitude,zipCode,phone,storeId,state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_detail);

        logo = (ImageView) findViewById(R.id.logo);
        name =(TextView) findViewById(R.id.name);
        city =(TextView) findViewById(R.id.city);
        latitude =(TextView) findViewById(R.id.latitude);
        longitude =(TextView) findViewById(R.id.longitude);
        zipCode =(TextView) findViewById(R.id.zipcode);
        phone =(TextView) findViewById(R.id.phone);
        storeId =(TextView) findViewById(R.id.storeId);
        state =(TextView) findViewById(R.id.state);


    }
}
