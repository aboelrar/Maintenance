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
import com.carsyalla.www.cars.interfaces.add_reportedCar;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class addReportedCar implements add_reportedCar.interfaces.Model {
    apis apis=new apis();
    com.carsyalla.www.cars.dialog.loading loading=new loading();
    @Override
    public String getdata(final String id_brand, final String id_model, final String phone, final String id_city, final String plate_type, final String plate_number, final String latitude, final String longitude, final String owner_address, final String notes, final String main_image, final ArrayList<String> album, String user_id, final Context context) {
        String addReportedCar=apis.addReportedCar+"/"+user_id;
        Log.e("addReportedCar",addReportedCar);
        StringRequest request=new StringRequest(Request.Method.POST, addReportedCar, new Response.Listener<String>() {
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
                        loading.dismissDialog();
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(jsonObject.getString("status").equals("3"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    loading.dismissDialog();
                    Log.e("addStolenCar",e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getLocalizedMessage()==null)
                {
                    loading.dismissDialog();
                    Toast.makeText(context, "تمت الاضافه بنجاح", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loading.dismissDialog();
                    Toast.makeText(context, "لايوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
                }
            }
        })  {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_brand",id_brand);
                params.put("id_model",id_model);
                params.put("phone",phone);
                params.put("id_city",id_city);
                params.put("plate_type",plate_type);
                params.put("plate_number",plate_number);
                params.put("address",owner_address);
                params.put("notes",notes);
                params.put("longitude",longitude);
                params.put("latitude",latitude);
                params.put("main_image",main_image);
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
