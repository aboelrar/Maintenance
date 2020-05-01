package com.carsyalla.www.forget_forget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.LoginAndRegist;
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class forget_passwrd_data implements forget_password_i.interfaces.Model {
    apis apis=new apis();
    com.carsyalla.www.cars.dialog.loading loading=new loading();
    @Override
    public String getdata(String user_id, final Context context, final String phone) {
        apis=new apis();
        String forget_password=apis.forget_password;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, forget_password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("status").equals("1"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, LoginAndRegist.class);
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
                    Log.e("catch",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 Log.e("errors", error.getLocalizedMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone",phone);
                return params;
            }};
        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return null;
    }
}
