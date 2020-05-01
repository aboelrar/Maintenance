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
import com.carsyalla.www.cars.Adapter.checkAdapter;
import com.carsyalla.www.cars.Model.check;
import com.carsyalla.www.cars.interfaces.checkList;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class checkData implements checkList.interfaces.Model {
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context) {
        final ArrayList<check>arrayList=new ArrayList<check>();
        apis apis=new apis();
        String serviceUrl=apis.services;
        Log.e("serviceUrl",serviceUrl);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, serviceUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1")) {
                        JSONArray jsonArray = response.getJSONArray("services");
                        Toast.makeText(context, ""+jsonArray.length(), Toast.LENGTH_SHORT).show();
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            arrayList.add(new check(jsonObject.getString("id"),jsonObject.getString("name")));
                        }
                        adapter adapter=new adapter();
                        adapter.Adapter(recyclerView,new checkAdapter(context,arrayList,0),context);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        Toast.makeText(context, ""+response.getString("messgae"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("checkData",e.getLocalizedMessage());
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
        return null;
    }

    @Override
    public ArrayList getSeconddata(final RecyclerView recyclerView, final Context context) {
        apis apis=new apis();
        final ArrayList<check>arrayList=new ArrayList<check>();
        String Brands=apis.Brands;
        Log.e("apibrands",Brands);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, Brands, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1")) {
                        JSONArray jsonArray = response.getJSONArray("brands");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            arrayList.add(new check(jsonObject.getString("id"),jsonObject.getString("name")));
                        }
                        adapter adapter=new adapter();
                        adapter.Adapter(recyclerView,new checkAdapter(context,arrayList,1),context);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("brand",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
