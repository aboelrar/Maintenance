package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.Adapter.brandAdapter;
import com.carsyalla.www.cars.Adapter.slider.pagerAdapter;
import com.carsyalla.www.cars.Model.brand;
import com.carsyalla.www.cars.Model.slider;
import com.carsyalla.www.cars.interfaces.brands;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class brandsData implements brands.interfaces.Model {
     apis apis=new apis();
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context, final ProgressBar loading) {
        final ArrayList<brand>arrayList=new ArrayList<brand>();
        String Brands=apis.Brands;
        Log.e("apibrands",Brands);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, Brands, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1")) {
                        loading.setVisibility(View.GONE);
                        JSONArray jsonArray = response.getJSONArray("brands");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                         JSONObject jsonObject=jsonArray.getJSONObject(index);
                         arrayList.add(new brand(jsonObject.getString("id"),jsonObject.getString("icon"),jsonObject.getString("name"),jsonObject.getString("total_suppliers")));
                         }
                        adapter adapter=new adapter();
                        adapter.griddAdapters(recyclerView,new brandAdapter(context,arrayList),context,4);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    loading.setVisibility(View.GONE);
                    Log.e("brand",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لاتوجد بيانات", Toast.LENGTH_SHORT).show();            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }

    int count=-2;
    @Override
    public ArrayList getSlider(final ViewPager viewPager, final Context context, final CircleIndicator circleIndicator) {
        final ArrayList<slider>arrayList=new ArrayList<slider>();
        String slider=apis.slider;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, slider, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                    final JSONArray jsonArray=response.getJSONArray("sliders");
                    for (int index=0;index<jsonArray.length();index++)
                    {
                        arrayList.add(new slider(jsonArray.getString(index)));
                    }
                        pagerAdapter viewimage = new pagerAdapter(context, arrayList);
                        viewPager.setAdapter(viewimage);
                        circleIndicator.setViewPager(viewPager);
                        final Handler handler = new Handler();
                        final Runnable runnable = new Runnable() {
                            public void run() {

                                if (count++ <jsonArray.length()){
                                    viewPager.setCurrentItem(count+ 1);
                                }
                                handler.postDelayed(this, 7000);
                                if(count==jsonArray.length()-2)
                                {
                                    count=-2;
                                }
                            }
                        };

                        // trigger first time
                        handler.post(runnable);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                       // Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                 Log.e("slider",e.getLocalizedMessage());                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لايوجد اتصال بالسرفر", Toast.LENGTH_SHORT).show();
            }
        });
        jsonObjectRequest.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
        return null;
    }
}
