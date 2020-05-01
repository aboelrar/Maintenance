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
import com.carsyalla.www.cars.interfaces.signup;
import com.carsyalla.www.cars.library.loginTrueOrFalse;
import com.carsyalla.www.cars.network.apis;
import com.carsyalla.www.cars.signUp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signupData implements signup.interfaces.Model {
    @Override
    public String getdata(final EditText mail, final EditText password, final EditText fullName, final EditText phoneNum, final Context context, final String gouvernate_id, final String city_id) {
        apis apis=new apis();
        final loading loading=new loading();
        String signupUrl=apis.SignUp;
        Log.e("pppooo",signupUrl);
        signupUrl=signupUrl.replaceAll(" ","20%");
        final StringRequest request=new StringRequest(Request.Method.POST, signupUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("1")) {
                        loading.dismissDialog();
                        Toast.makeText(context, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,home.class));
                        loginTrueOrFalse loginTrueOrFalse=new loginTrueOrFalse();
                        loginTrueOrFalse.userId(context,jsonObject.getString("id_user"));
                        ((signUp)context).finish();

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
            params.put("governate",gouvernate_id);
            params.put("city",city_id);
            params.put("pass",password.getText().toString());
            return params;
        }};
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}