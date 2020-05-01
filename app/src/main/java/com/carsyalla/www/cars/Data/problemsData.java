package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.home;
import com.carsyalla.www.cars.interfaces.problems;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class problemsData implements problems.interfaces.Model {
    apis apis=new apis();
    com.carsyalla.www.cars.dialog.loading loading=new loading();
    @Override
    public String getdata(final EditText name, final EditText phone, final EditText message, final Context context) {
        String problems_url=apis.problems;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, problems_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("status").equals("1"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context,home.class);
                        context.startActivity(intent);
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
                    Log.e("problems",e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismissDialog();
                Log.e("errors",error.getLocalizedMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",name.getText().toString());
                params.put("phone",phone.getText().toString());
                params.put("message",message.getText().toString());
                return params;
            }};
        stringRequest.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return null;
    }
}
