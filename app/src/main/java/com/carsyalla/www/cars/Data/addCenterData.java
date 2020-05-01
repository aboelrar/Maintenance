package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
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
import com.carsyalla.www.cars.home;
import com.carsyalla.www.cars.interfaces.addCenter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class addCenterData implements addCenter.interfaces.Model {
    @Override
    public String getdata(final EditText name, final EditText description, final EditText email, final EditText phone, final String gouvernateId, final String cityId, final EditText address, final String lat, final String lng, final EditText TimeFrom, EditText workTimeTo, final EditText owner_name, final Context context, final String mainImage, final ArrayList<String>  brands, final ArrayList<String> service, final ArrayList<String> Album, final EditText discount, final EditText holidays ) {
        apis apis=new apis();
        final loading loading=new loading();
         final ArrayList<String> arrayList_id_ser=new ArrayList<String>();
         ArrayList<String> arrayList_id_brand=new ArrayList<String>();

            final ArrayList<String> arrayList_title_ser=new ArrayList<String>();
            ArrayList<String> arrayList_title_brand=new ArrayList<String>();

          final ArrayList<String> arrayList_num_ser=new ArrayList<String>();
         ArrayList<String> arrayList_num_brand=new ArrayList<String>();
        String addCenter=apis.addCenter;
        addCenter = addCenter.replaceAll(" ", "%20");
        Log.e("discount",discount.getText().toString());
        final StringRequest stringRequest=new StringRequest(Request.Method.POST, addCenter, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("status").equals("1"))
                    {
                        loading.dismissDialog();
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,home.class));
                        SharedPreferences sharedPreferences = context.getSharedPreferences("services_i", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        SharedPreferences sharedPreferences_b = context.getSharedPreferences("brands", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.clear();
                        editor1.commit();
                    }
                    else if(jsonObject.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        loading.dismissDialog();

                    }
                    else if(jsonObject.getString("status").equals("3"))
                    {
                        Toast.makeText(context, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        loading.dismissDialog();
                    }

                } catch (JSONException e) {
                    Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    loading.dismissDialog();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لا يمكن الاتصال بالخادم", Toast.LENGTH_SHORT).show();
                loading.dismissDialog();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",name.getText().toString());
                params.put("owner_name",owner_name.getText().toString());
                params.put("description",description.getText().toString());
                params.put("email",email.getText().toString());
                params.put("main_phone",phone.getText().toString());
                params.put("governate_id",gouvernateId);
                params.put("city_id",cityId);
                params.put("address",address.getText().toString());
                params.put("longitude",lng);
                params.put("latitude",lat);
                params.put("day_off",holidays.getText().toString());
                params.put("discount",discount.getText().toString());
                params.put("work_time",TimeFrom.getText().toString());
                params.put("main_image", mainImage);

                for(int index=0;index<service.size();index++)
                {
                    params.put("services["+index+"]",service.get(index).toString());
                 }
                for(int index=0;index<brands.size();index++)
                {
                    params.put("brands["+index+"]",brands.get(index).toString());
                }

                if(Album!= null) {
                    int album=Album.size();
                    for(int index=0;index<album;index++)
                    {
                        params.put("album["+index+"]",Album.get(index).toString());
                    }
                }
                return params;
            }
            }
        ;
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
