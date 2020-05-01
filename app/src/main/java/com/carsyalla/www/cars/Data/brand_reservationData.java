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
import com.carsyalla.www.cars.Adapter.reservationBrandsAdapter;
import com.carsyalla.www.cars.Model.brandsall;
import com.carsyalla.www.cars.interfaces.brand_reservation;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class brand_reservationData implements brand_reservation.interfaces.Model {
    apis apis=new apis();
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context) {
        final ArrayList<brandsall>brands=new ArrayList<brandsall>();
        String Brands=apis.Brands;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Brands, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1")) {
                        JSONArray jsonArray = response.getJSONArray("brands");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            brands.add(new brandsall(jsonObject.getString("id"),jsonObject.getString("name")));
                        }
                        adapter adapter=new adapter();
                        adapter.Adapter(recyclerView,new reservationBrandsAdapter(brands,context),context);
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
                Log.e("error",error.getLocalizedMessage());
            }
        });
        jsonObjectRequest.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
        return null;
    }
}
