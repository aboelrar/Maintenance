package com.carsyalla.www.change_pass;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.home;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.network.apis;

public class change_pass_data implements  change_pass_i.interfaces.Model{
    @Override
    public String getdata(final EditText oldpass, final EditText newpass, final EditText renewpass, final Context context) {
        apis apis=new apis();
        final loading loading=new loading();
        String change_pass=apis.change_pass;
        final StringRequest request=new StringRequest(Request.Method.POST, change_pass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("1")) {
                        loading.dismissDialog();
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, home.class));

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
                params.put("old_password",oldpass.getText().toString());
                params.put("new_password",newpass.getText().toString());
                params.put("re_new_password",renewpass.getText().toString());
                params.put("id_user",new savedId().getId(context));
                return params;
            }};
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
