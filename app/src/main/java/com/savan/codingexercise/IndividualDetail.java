package com.savan.codingexercise;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;



public class IndividualDetail extends AppCompatActivity {
    ImageView logoImage;
    TextView name,city,latitude,longitude,zipCode,phone,storeId,state,address;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_detail);

        logoImage = (ImageView) findViewById(R.id.logo);
        name =(TextView) findViewById(R.id.name);
        city =(TextView) findViewById(R.id.city);
        latitude =(TextView) findViewById(R.id.latitude);
        longitude =(TextView) findViewById(R.id.longitude);
        zipCode =(TextView) findViewById(R.id.zipcode);
        phone =(TextView) findViewById(R.id.phone);
        storeId =(TextView) findViewById(R.id.storeId);
        state =(TextView) findViewById(R.id.state);
        address= (TextView)findViewById(R.id.address);

        Bundle bundle = getIntent().getExtras();
        Bitmap logoBitmap = (Bitmap) bundle.getParcelable("Image");
        logoImage.setImageBitmap(logoBitmap);

        position = bundle.getInt("position");
        Log.e("POSITION CLICKED", position + "");

        name.setText(MainActivity.nameArray.get(position));
        city.setText(MainActivity.cityArray.get(position));
        latitude.setText(MainActivity.latArray.get(position));
        longitude.setText(MainActivity.longArray.get(position));
        zipCode.setText(MainActivity.zipArray.get(position));
        phone.setText(MainActivity.phoneArray.get(position));
        storeId.setText(MainActivity.storeArray.get(position));
        state.setText(MainActivity.stateArray.get(position));
        address.setText(MainActivity.addArray.get(position));

    }
}
