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
import com.carsyalla.www.cars.Adapter.gouvernateAdapter;
import com.carsyalla.www.cars.Model.gouverment;
import com.carsyalla.www.cars.interfaces.gouvernate;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class gouvernateData implements gouvernate.interfaces.Model {
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context) {
        final ArrayList<gouverment>arrayList=new ArrayList<gouverment>();
        apis apis=new apis();
        String gouvernateUrl=apis.gouvernate;
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, gouvernateUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1")) {
                        JSONArray jsonArray = response.getJSONArray("governates");
                        for (int index=0;index<jsonArray.length();index++){
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            arrayList.add(new gouverment(jsonObject.getString("id"),jsonObject.getString("name")));
                        }
                        adapter adapter=new adapter();
                        adapter.Adapter(recyclerView,new gouvernateAdapter(context,arrayList),context);
                    }
                } catch (JSONException e) {
                    Log.e("gouvernate_city",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No Connection", Toast.LENGTH_SHORT).show();
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
