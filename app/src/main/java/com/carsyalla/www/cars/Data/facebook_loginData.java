package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.carsyalla.www.cars.home;
import com.carsyalla.www.cars.interfaces.facebook_login;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class facebook_loginData implements facebook_login.interfaces.Model {
    apis apis;
    com.carsyalla.www.cars.dialog.loading loading=new loading();
    @Override
    public String getdata(final String username, final String id, final Context context) {
        apis=new apis();
        String fbLoginUrl = apis.faceBook_Login;
        Log.e("url",fbLoginUrl);
        StringRequest loginRequest = new StringRequest(Request.Method.POST, fbLoginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("1")) {
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject1=jsonObject.getJSONObject("user_data");
                        String id=jsonObject1.getString("id");
                        context.startActivity(new Intent(context,home.class));
                        userId( context, id);
                        } else if (jsonObject.getString("status").equals("2")) {
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();


                        } else if (jsonObject.getString("status").equals("3")) {
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لايوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",username);
                params.put("face_id",id);
                return params;
            }};
        loginRequest.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(loginRequest);
        return null;
    }

    //get user id shared prefrences
    public void userId(Context context,String id)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("idss",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("islogin",true);
        editor.putString("id", id);
        editor.commit();
    }
}
