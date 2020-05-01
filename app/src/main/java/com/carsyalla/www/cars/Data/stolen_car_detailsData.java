package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.Adapter.slider.pagerAdapter;
import com.carsyalla.www.cars.Model.slider;
import com.carsyalla.www.cars.interfaces.stolenCar_details;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class stolen_car_detailsData implements stolenCar_details.interfaces.Model {
    apis apis=new apis();
    int count=-2;
    @Override
    public String getdata(final Context context, final TextView brand, final TextView plate_num, final TextView notes, final TextView brand1, final TextView model1, final TextView modelyear, final TextView serial, final TextView address, String stolen_car_id, final ViewPager viewPager, final CircleIndicator circleIndicator) {
        String stolen_car_details=apis.stolenCarDetails+"/"+stolen_car_id;
        Log.e("stolen_car_details",stolen_car_details);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, stolen_car_details, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                 JSONObject jsonObject=response.getJSONObject("carData");
                        brand.setText(jsonObject.getString("brand"));
                        plate_num.setText(jsonObject.getString("plate_number"));
                        notes.setText(jsonObject.getString("notes"));
                        brand1.setText(jsonObject.getString("brand"));
                        model1.setText(jsonObject.getString("model"));
                        modelyear.setText(jsonObject.getString("year"));
                        serial.setText(jsonObject.getString("serial"));
                        address.setText(jsonObject.getString("address"));


                        //Slider for Details
                        final ArrayList<slider> arrayList=new ArrayList<slider>();
                        final JSONArray jsonArray=jsonObject.getJSONArray("images_album");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            arrayList.add(new slider(jsonArray.getString(index)));
                        }
                        pagerAdapter viewimage = new pagerAdapter(context, arrayList);
                        viewPager.setAdapter(viewimage);
                        circleIndicator.setViewPager(viewPager);
                        final Handler handler = new Handler();
                        final Runnable runnable = new Runnable() {
                            public void run() {
                                if (count++ <jsonArray.length()){
                                    viewPager.setCurrentItem(count+ 1);
                                    if(count==jsonArray.length()-2)
                                    {
                                        count=-2;
                                    }
                                }

                                handler.postDelayed(this, 7000);
                            }
                        };
                        // trigger first time
                        handler.post(runnable);
                        }
                        else if(response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("stolenCarDetails",e.getLocalizedMessage());
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
}
