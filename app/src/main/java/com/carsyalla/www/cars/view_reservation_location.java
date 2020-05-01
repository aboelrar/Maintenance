package com.carsyalla.www.cars;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.carsyalla.www.R;

public class view_reservation_location extends FragmentActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reservation_location);
        goToMap();
        finish();
    }

    public void goToMap() {
        Intent intent=getIntent();
        double lat=intent.getDoubleExtra("latitude",0.0);
        double lng=intent.getDoubleExtra("longitude",0.0);
        Log.e("lat",lat+"/"+lng);
        String center_name=intent.getStringExtra("center_name");
        //set Things Here
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+lat+","+lng);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }
}
