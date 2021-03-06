package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.savedId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class favouriteData implements favourite.interfaces.Model {
    com.carsyalla.www.cars.library.savedId savedId=new savedId();
    @Override
    public ArrayList getdata(String Url, final Context context, String id) {
        Log.e("favouriteUrl",Url+"/"+savedId.getId(context)+"/"+id);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, Url+"/"+savedId.getId(context)+"/"+id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();

                    }  else if(response.getString("status").equals("3"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    Log.e("fav",e.getLocalizedMessage());
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
