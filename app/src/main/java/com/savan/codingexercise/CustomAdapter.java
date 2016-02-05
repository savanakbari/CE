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



import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Savan on 2/4/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    Bitmap bitmap;
    ImageView logo;
    ArrayList<Bitmap> bitmapArray;

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
        bitmapArray = new ArrayList<Bitmap>();
        name.setText(MainActivity.nameArray.get(position));
        phone.setText(MainActivity.phoneArray.get(position));
        address.setText(MainActivity.addArray.get(position));
      new BgImage().execute(MainActivity.logoArray.get(position));
//       try {
//           bitmap = BitmapFactory.decodeStream((InputStream) new URL(MainActivity.logoArray.get(position)).getContent());
//           logo.setImageBitmap(bitmap);
//           bitmap.recycle();
//       }
//       catch (Exception e)
//       {e.printStackTrace();}


        return viewCustom;
    }

    private class BgImage extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
                }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap image) {
            if (image !=null)
            {
               // bitmapArray.add(image);
                logo.setImageBitmap(image);
            }
            else
            {
                Log.e("IMAGE SET" , "Fail to set image");
            }
        }
    }


}
