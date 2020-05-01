package com.carsyalla.www.cars.Data;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.interfaces.popup_ads;
import com.carsyalla.www.cars.network.apis;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

public class popup_adsData implements popup_ads.interfaces.Model {
    apis apis=new apis();
    static String num="2";
    @Override
    public String getdata(final LinearLayout imageView, final Context context, final Dialog dialog) {
        String ads_url=apis.ads;
        Log.e("ads_url",ads_url);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, ads_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        if(response.getString("image").equals("null"))
                        {

                        }
                        else {
                        Picasso.with(context).load(response.getString("image")).into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                dialog.show();
                                imageView.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });}
                }
                else if(response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                     //   Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                }catch (JSONException e) {
                    Log.e("ads",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لايوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return num;
    }
}
