package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.Adapter.reservationAdapter;
import com.carsyalla.www.cars.Model.reservation;
import com.carsyalla.www.cars.interfaces.addRate;
import com.carsyalla.www.cars.interfaces.cancel_reservation;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.interfaces.myReservation;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class my_reservationData implements myReservation.interfaces.Model {
    apis apis=new apis();
    savedId savedI=new savedId();
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context, final favourite.interfaces.View view, final addRate.interfaces.View view1, final cancel_reservation.interfaces.View cancel_view, final TextView nodata, final GifImageView gifImageView, final ProgressBar loading) {
        final ArrayList<reservation>arrayList=new ArrayList<reservation>();

        String my_reservation=apis.my_reservation+"/"+savedI.getId(context);
        Log.e("my_reservation",my_reservation);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, my_reservation, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        loading.setVisibility(View.GONE);
                        JSONArray jsonArray=response.getJSONArray("reservations");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            arrayList.add(new reservation(jsonObject.getString("id"),jsonObject.getString("center_name"),jsonObject.getString("center_address"),jsonObject.getString("image"),jsonObject.getInt("favorite"),jsonObject.getInt("rating"),jsonObject.getString("reservation_date"),jsonObject.getDouble("latitude"),jsonObject.getDouble("longitude"),jsonObject.getString("can_cancel"),jsonObject.getString("can_rate"),jsonObject.getString("last_status"),jsonObject.getString("user_name"),jsonObject.getString("center_name"),jsonObject.getString("center_id"),jsonObject.getString("id_reservation_status")));
                        }
                        adapter adapter=new adapter();
                        recyclerView.setNestedScrollingEnabled(false);
                        recyclerView.setHasFixedSize(false);
                        adapter.Adapter(recyclerView,new reservationAdapter(context,arrayList,view1,cancel_view),context);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        nodata.setText(""+response.getString("message"));
                        gifImageView.setImageResource(R.drawable.datanotfound);
                    }
                } catch (JSONException e) {
                    loading.setVisibility(View.GONE);
                    Log.e("myreservation",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لايمكن الاتصال بالسرفر"+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
