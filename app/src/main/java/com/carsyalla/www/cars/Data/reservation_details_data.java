package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.interfaces.reservation_details;
import com.carsyalla.www.cars.network.apis;
import com.carsyalla.www.cars.view_reservation_location;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class reservation_details_data implements reservation_details.interfaces.Model {
    apis apis=new apis();
    @Override
    public ArrayList getdata(final Context context, final TextView center_title, final TextView date, final TextView phone, final TextView model, final TextView gouvernate, final TextView city, final TextView address, final LinearLayout viewmap, String id_reservation) {
        String reservation_details_url=apis.Reservation_details+"/"+id_reservation;
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, reservation_details_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                      center_title.setText(response.getString("center_name"));
                      date.setText(response.getString("reservation_date"));
                      phone.setText(response.getString("user_phone"));
                      model.setText(response.getString("model"));
                      gouvernate.setText(response.getString("center_governate"));
                      city.setText(response.getString("center_city"));
                      address.setText(response.getString("center_address"));
                      //go to map
                        viewmap.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(context,view_reservation_location.class);
                                try {
                                    intent.putExtra("longitude",response.getDouble("longitude"));
                                    intent.putExtra("latitude",response.getDouble("latitude"));
                                    intent.putExtra("center_name",response.getString("center_name"));
                                    context.startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("message").equals("3"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("reservaion_details",e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.getLocalizedMessage());
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
