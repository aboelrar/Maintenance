package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.interfaces.navigation_userName;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class usernameData implements navigation_userName.Model {
    apis apis=new apis();
    @Override
    public ArrayList getdata(final Context context, String user_id, final TextView name) {
        String user_profile=apis.userProfile+"/"+new savedId().getId(context);
        Log.e("user_profile",user_profile);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, user_profile, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        JSONObject jsonObject=response.getJSONObject("user");
                        name.setText(jsonObject.getString("name"));
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
                    Log.e("user",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
