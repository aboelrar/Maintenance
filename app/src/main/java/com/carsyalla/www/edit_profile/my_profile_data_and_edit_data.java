package com.carsyalla.www.edit_profile;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.network.apis;

public class my_profile_data_and_edit_data implements my_profile_data_and_edit_i.Model {
    apis apis=new apis();
    @Override
    public String getdata(final EditText mail, final EditText fullName, final EditText phoneNum, final Context context) {
        String user_profile=apis.userProfile+"/"+new savedId().getId(context);
        Log.e("user_profile",user_profile);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, user_profile, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        JSONObject jsonObject=response.getJSONObject("user");
                        fullName.setText(jsonObject.getString("name"));
                        mail.setText(jsonObject.getString("mail"));
                        phoneNum.setText(jsonObject.getString("phone"));
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
                Toast.makeText(context, "لايوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }

    @Override
    public String getdataedit(final EditText mail, final EditText fullName, final EditText phoneNum, final Context context) {
        apis apis=new apis();
        final loading loading=new loading();
        String signupUrl=apis.edit_profile;
        Log.e("edit",signupUrl);
        signupUrl=signupUrl.replaceAll(" ","20%");
        final StringRequest request=new StringRequest(Request.Method.POST, signupUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("1")) {
                        loading.dismissDialog();
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    } else if (jsonObject.getString("status").equals("2")) {
                        loading.dismissDialog();
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();


                    } else if (jsonObject.getString("status").equals("3")) {
                        loading.dismissDialog();
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    loading.dismissDialog();
                    Toast.makeText(context, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                params.put("name",fullName.getText().toString());
                params.put("mail",mail.getText().toString());
                params.put("phone",phoneNum.getText().toString());
                params.put("id_user",new savedId().getId(context));
                return params;
            }};
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);

        return null;
    }
}
