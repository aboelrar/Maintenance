package com.carsyalla.www.cars.Data;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
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
import com.carsyalla.www.cars.interfaces.reservation;
import com.carsyalla.www.cars.network.apis;
import com.carsyalla.www.cars.reservation_done;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class reservationData implements reservation.interfaces.Model {
    apis apis;
    @Override
    public String getdata(final EditText name, final EditText phone, final String id_brand, final String id_model, final Context context, final EditText model_year, final TextView reservation_date, final String id_user, final String id_center, final Dialog dialog) {
       apis=new apis();
       String reserverUrl=apis.reserve;
       Log.e("reserverUrl",reserverUrl);
        final loading loading=new loading();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, reserverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("status").equals("1"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent intent=new Intent(context,reservation_done.class);
                        intent.putExtra("id_reservation",jsonObject.getString("id_reservation"));
                        context.startActivity(intent);
                    }
                  else if(jsonObject.getString("status").equals("2"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(jsonObject.getString("3").equals("3"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    loading.dismissDialog();
                    Log.e("reservation",e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لم يتم الحجز", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",name.getText().toString());
                params.put("phone",phone.getText().toString());
                params.put("id_brand",id_brand);
                params.put("id_model",id_model);
                params.put("model_year",model_year.getText().toString());
                params.put("reservation_date",reservation_date.getText().toString());
                params.put("id_user",id_user);
                params.put("id_center",id_center);
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
