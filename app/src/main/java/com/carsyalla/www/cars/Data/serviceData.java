package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.Adapter.slider.servicesAdapter;
import com.carsyalla.www.cars.Model.serviceall;
import com.carsyalla.www.cars.interfaces.services;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class serviceData implements services.interfaces.Model {
    ArrayList<serviceall>arrayList=new ArrayList<serviceall>();
    apis apis=new apis();
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context) {
        String service_url=apis.services;
        Log.e("service_url",service_url);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, service_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        JSONArray jsonArray=response.getJSONArray("services");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            arrayList.add(new serviceall(jsonObject.getString("id"),jsonObject.getString("name")));
                        }
                        adapter adapter=new adapter();
                        adapter.Adapter(recyclerView,new servicesAdapter(arrayList,context),context);
                    }
                    else if (response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("allserviceData",e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "someThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
