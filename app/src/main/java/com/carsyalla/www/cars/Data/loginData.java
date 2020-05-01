package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.carsyalla.www.cars.LoginAndRegist;
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.home;
import com.carsyalla.www.cars.interfaces.login;
import com.carsyalla.www.cars.my_favourite;
import com.carsyalla.www.cars.my_reservations;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class loginData implements login.interfaces.Model{
    apis apis;
    loading loading=new loading();
    @Override
    public String getdata(final EditText mail, final EditText password, final Context context, final String res) {
        apis=new apis();
        String LoginUrl = apis.Login;
        Log.e("url",LoginUrl);
        StringRequest loginRequest = new StringRequest(Request.Method.POST, LoginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("1")) {
                        loading.dismissDialog();
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject1 = jsonObject.getJSONObject("user_data");
                        String id = jsonObject1.getString("id");
                        userId(context, id);

                        if(res.equals("res"))
                        {
                            ((LoginAndRegist) context).finish();
                        }
                        else if(res.equals("allcenters"))
                        {
                            ((LoginAndRegist) context).finish();
                        }
                        else if(res.equals("edit"))
                        {
                            ((LoginAndRegist) context).finish();
                        }
                        else if(res.equals("favdetails"))
                        {
                            ((LoginAndRegist) context).finish();
                        }
                        else if(res.equals("myres"))
                        {
                            context.startActivity(new Intent(context, my_reservations.class));
                            ((LoginAndRegist) context).finish();
                        }
                        else if(res.equals("myfav"))
                        {
                            context.startActivity(new Intent(context, my_favourite.class));
                            ((LoginAndRegist) context).finish();
                        }
                        else {
                            context.startActivity(new Intent(context, home.class));
                            ((LoginAndRegist) context).finish();
                        }
                    } else if (jsonObject.getString("status").equals("2")) {
                        loading.dismissDialog();
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        mail.setError("Something Error");
                        } else if (jsonObject.getString("status").equals("3")) {
                        loading.dismissDialog();
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    loading.dismissDialog();
                    Toast.makeText(context, ""+ e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismissDialog();
                Toast.makeText(context, "لايوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mail",mail.getText().toString());
                params.put("password",password.getText().toString());
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
