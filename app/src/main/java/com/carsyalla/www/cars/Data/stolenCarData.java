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
import com.carsyalla.www.cars.Adapter.stolenCarAdapter;
import com.carsyalla.www.cars.Model.stolenCar;
import com.carsyalla.www.cars.interfaces.stolen_Car;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class stolenCarData implements stolen_Car.interfaces.Model {
    apis apis=new apis();
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context, int number, final ProgressBar loading, final GifImageView gifImageView, final TextView nodata) {
        final ArrayList<stolenCar>arrayList=new ArrayList<stolenCar>();
        String stolenCar=apis.stolenCar;
        Log.e("stolenCar",stolenCar);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, stolenCar, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        loading.setVisibility(View.GONE);
                        JSONArray jsonArray=response.getJSONArray("cars");
                        for(int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            arrayList.add(new stolenCar(jsonObject.getString("id"),jsonObject.getString("image"),jsonObject.getString("model"),jsonObject.getString("plate_number"),jsonObject.getString("brand"),jsonObject.getString("date"),jsonObject.getString("user_name"),jsonObject.getString("color"),jsonObject.getString("serial"),jsonObject.getString("year"),jsonObject.getString("city")));
                            }
                        adapter adapter=new adapter();
                        adapter.Adapter(recyclerView,new stolenCarAdapter(arrayList,context),context);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        loading.setVisibility(View.GONE);
                        // gifImageView.setImageResource(R.id.);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        nodata.setText(""+response.getString("message"));
                        gifImageView.setImageResource(R.drawable.datanotfound);
                    }
                } catch (JSONException e) {
                    Log.e("stolenCar",e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لا يوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
            }
        });

        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
