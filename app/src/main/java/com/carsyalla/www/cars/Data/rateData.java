package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.interfaces.rate;
import com.carsyalla.www.cars.my_reservations;
import com.carsyalla.www.cars.network.apis;
import com.carsyalla.www.cars.rating;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class rateData implements rate.interfaces.Model {
   apis apis=new apis();
    //Login Data Connected Server
    com.carsyalla.www.cars.dialog.loading loading=new loading();
    @Override
    public String getdata(final String privacy, final String comment, final String center_id, final String reservation_id, final String user_id, final int rate, final Context context) {
        String rate_url=apis.addRate;
        Log.e("rate_url",""+rate);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, rate_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("1"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,my_reservations.class));
                        ((rating)context).finish();
                    }
                    else if(jsonObject.getString("status").equals("2"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(jsonObject.getString("status").equals("3"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    loading.dismissDialog();
                    Log.e("rateDetail",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismissDialog();
                Log.e("rateError", error.getLocalizedMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("private",privacy);
                params.put("comment",comment);
                params.put("id_center",center_id);
                params.put("id_reservation",reservation_id);
                params.put("id_user",user_id);
                params.put("rate",""+rate);
                return params;
            }};
        stringRequest.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return null;
    }
}
