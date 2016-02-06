package com.savan.codingexercise;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CacheActivity extends AppCompatActivity {
    public CustomAdapter cacheCustomAdapter;
    public Integer lvCachePoistion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cache_activity);
        setTitle(" Cache Activity ");

        SharedPreferences sharedPref = getSharedPreferences("cache", Context.MODE_PRIVATE);
        String json = sharedPref.getString("jsonCache", "");
        Log.e("CACHE String", json);

        // Parsing Json

        try {
            JSONObject jObject = new JSONObject(json);
            JSONArray jArray =jObject.getJSONArray("stores");

            for(int i = 0; i<jArray.length();i++){
                JSONObject obj =jArray.getJSONObject(i);

                // Adding Values in Mainactivity Arrays
                MainActivity.number.add("i");
                // Add Parsed data to respective Array
                MainActivity.addArray.add(obj.getString("address"));
                MainActivity.cityArray.add(obj.getString("city"));
                MainActivity.nameArray.add(obj.getString("name"));
                MainActivity.latArray.add(obj.getString("latitude"));
                MainActivity.zipArray.add(obj.getString("zipcode"));
                MainActivity.logoArray.add(obj.getString("storeLogoURL"));
                MainActivity.phoneArray.add(obj.getString("phone"));
                MainActivity.longArray.add(obj.getString("longitude"));
                MainActivity.storeArray.add(obj.getString("storeID"));
                MainActivity.stateArray.add(obj.getString("state"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cacheCustomAdapter = new CustomAdapter(getBaseContext(),MainActivity.number);

        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(cacheCustomAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get position of Row being clicked and create bundle to send Logo Image to IndividualActivity

                lvCachePoistion = position;
                //Obtain bit map from Cache

                Bitmap bit =Bitmap.createBitmap((view.findViewById(R.id.logo)).getDrawingCache());
                Bundle bundle = new Bundle();
                bundle.putParcelable("Image",bit);
                bundle.putInt("position",lvCachePoistion);
                Intent intent = new Intent(getBaseContext(), IndividualDetail.class);
                intent.putExtras(bundle);

                // After putting bundle in Intent, pass in to IndividualActivity
                startActivity(intent);

            }
        });

    }
}
