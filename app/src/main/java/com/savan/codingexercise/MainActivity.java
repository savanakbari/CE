package com.savan.codingexercise;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    ArrayList<String> number ;
    CustomAdapter customAdapter;
    Boolean activeConnection;
    static String response;
    public static ArrayList<String> addArray,cityArray,nameArray,latArray,zipArray,logoArray,phoneArray,longArray,storeArray,stateArray;
    private static String url= "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/stores.json";
    public static int lvPoistion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Decleration starts from here
        addArray =new ArrayList<String>();cityArray =new ArrayList<String>();
        nameArray =new ArrayList<String>();latArray =new ArrayList<String>();
        zipArray =new ArrayList<String>();logoArray =new ArrayList<String>();
        phoneArray =new ArrayList<String>();longArray =new ArrayList<String>();
        storeArray =new ArrayList<String>();stateArray =new ArrayList<String>();
        number = new ArrayList<String>();


        activeConnection=isConnected();


        if(activeConnection){
            // connected to the internet
            Snackbar.make(findViewById(android.R.id.content)," Active Internet Connection",Snackbar.LENGTH_LONG).show();
            new BgFetch().execute();
        }
        else{
            Snackbar snack =Snackbar.make(findViewById(android.R.id.content),"No Internet Connection",Snackbar.LENGTH_INDEFINITE);
            snack.setAction("GET ONLINE", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            });
            snack.setActionTextColor(Color.YELLOW);
            snack.show();
        }

    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean state = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return state;
    }

    private class BgFetch extends AsyncTask<Void,Void,Void>{

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this, "Stores Menu", "Loading...", false);
        }
        @Override
        protected Void doInBackground(Void... params) {
            try{
                URL url1 = new URL(url);
                HttpURLConnection httpConn = (HttpURLConnection) url1.openConnection();
                httpConn.setRequestMethod("GET");
                int status = httpConn.getResponseCode();

                if(status == 200){
                    String str;
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    while ((str= br.readLine()) != null){
                        response +=str;
                    }
                    Log.d("RESPONSE ARRAY", response);
                    //works good
                     String response1=response.substring(4);
                    Log.d("After Compression", response1);

                    JSONObject jObject = new JSONObject(response1);
                    JSONArray jArray =jObject.getJSONArray("stores");

                    for(int i = 0; i<jArray.length();i++){
                        JSONObject obj =jArray.getJSONObject(i);
                        number.add("i");

                        addArray.add(obj.getString("address"));
                        cityArray.add(obj.getString("city"));
                        nameArray.add(obj.getString("name"));
                        latArray.add(obj.getString("latitude"));
                        zipArray.add(obj.getString("zipcode"));
                        logoArray.add(obj.getString("storeLogoURL"));
                        phoneArray.add(obj.getString("phone"));
                        longArray.add(obj.getString("longitude"));
                        storeArray.add(obj.getString("storeID"));
                        stateArray.add(obj.getString("state"));

                    }
                }
                else{
                    Log.d("MSG","Unable to fetch");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            customAdapter =new CustomAdapter(getBaseContext(),number);

            ListView lv =(ListView)findViewById(R.id.list);
            lv.setAdapter(customAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    lvPoistion = position;
                    Bitmap bit =Bitmap.createBitmap((view.findViewById(R.id.logo)).getDrawingCache());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Image",bit);
                    bundle.putInt("position",lvPoistion);
                    Intent intent = new Intent(getBaseContext(), IndividualDetail.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }


    }

}
