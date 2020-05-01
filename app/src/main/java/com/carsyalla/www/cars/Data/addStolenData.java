package com.carsyalla.www.cars.Data;

import android.content.Context;
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
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.interfaces.add_stolen_car;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class addStolenData implements add_stolen_car.interfaces.Model {
    apis apis=new apis();
    final com.carsyalla.www.cars.dialog.loading loading=new loading();
    @Override
    public String getdata(final String id_brand, final String id_model, final String year_model, final String phone, final String id_city, final String plate_type, final String serial, final String plate_number, final String color, final String owner_address, final String notes, final String main_image, final String license, final ArrayList<String> album, String user_id, final Context context) {
         String add_stolen_car=apis.addStolenCar+"/"+user_id;
         Log.e("add_stolen_car",add_stolen_car);
        StringRequest request=new StringRequest(Request.Method.POST, add_stolen_car, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("status").equals("1"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(jsonObject.getString("status").equals("2"))
                    {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        loading.dismissDialog();
                    }
                    else if(jsonObject.getString("status").equals("3"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                   Log.e("addStolenCar",e.getLocalizedMessage());
                    loading.dismissDialog();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getLocalizedMessage()==null)
                {
                    Toast.makeText(context, "تمت الاضافه بنجاح", Toast.LENGTH_SHORT).show();
                    loading.dismissDialog();
                }
                else
                {
                    Toast.makeText(context, "لايوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
                    loading.dismissDialog();
                }
            }
        })  {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_brand",id_brand);
                params.put("id_model",id_model);
                params.put("year_model",year_model);
                params.put("phone",phone);
                params.put("id_city",id_city);
                params.put("plate_type",plate_type);
                params.put("serial",serial);
                params.put("plate_number",plate_number);
                params.put("color",color);
                params.put("owner_address",owner_address);
                params.put("notes",notes);
                params.put("main_image",main_image);
                params.put("license",license);
                int albumnum=album.size();
                for(int index=0;index<albumnum;index++)
                {
                    params.put("album["+index+"]",album.get(index).toString());
                }
                return params;
            }};
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
