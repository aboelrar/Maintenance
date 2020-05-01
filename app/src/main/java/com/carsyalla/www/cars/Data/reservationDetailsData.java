package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.Adapter.brandsDetailsAdapter;
import com.carsyalla.www.cars.Adapter.slider.pagerAdapter;
import com.carsyalla.www.cars.Model.brandDetails;
import com.carsyalla.www.cars.Model.slider;
import com.carsyalla.www.cars.interfaces.reservationdetails;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class reservationDetailsData implements reservationdetails.interfaces.Model {
    int count=-2;
    apis apis=new apis();
    String center_Id;
    @Override
    public ArrayList getdata(final Context context, String center_Id, final TextView name, final RatingBar ratingBar, final TextView descripition, final ViewPager viewPager, final CircleIndicator circleIndicator) {
       this.center_Id=center_Id;
         String centerDetails=apis.Reservation_details+"/"+center_Id;
         Log.e("centerdetails",centerDetails);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, centerDetails, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        JSONObject jsonObject=response.getJSONObject("center");
                        name.setText(jsonObject.getString("name"));
                        ratingBar.setRating(jsonObject.getInt("rating"));
                        descripition.setText(jsonObject.getString("description"));

               //Slider for Details
                        final ArrayList<slider>arrayList=new ArrayList<slider>();
                        final JSONArray jsonArray=jsonObject.getJSONArray("images");
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
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("details",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }

    @Override
    public ArrayList getdataSnd(final Context context, final TextView gouvernate, final TextView city, final TextView timeWork, final RecyclerView brandList, String center_id, final RecyclerView serviceList) {
        String centerDetails=apis.centerDetails+"/"+center_id;
        final ArrayList<brandDetails>arrayListBrands=new ArrayList<brandDetails>();
        final ArrayList<brandDetails>arrayListservices=new ArrayList<brandDetails>();
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, centerDetails, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        JSONObject jsonObject=response.getJSONObject("center");
                        gouvernate.setText(jsonObject.getString("governate"));
                        city.setText(jsonObject.getString("city"));
                        timeWork.setText(jsonObject.getString("work_time"));
                  //get brand list
                        JSONArray jsonArray=jsonObject.getJSONArray("brands");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject1=jsonArray.getJSONObject(index);
                            arrayListBrands.add(new brandDetails(jsonObject1.getString("id"),jsonObject1.getString("name"),jsonObject1.getString("icon")));
                        }
                    adapter adapter=new adapter();
                    adapter.griddAdapters(brandList,new brandsDetailsAdapter(context,arrayListBrands),context,4);

                        //get service list
                        JSONArray jsonArray1=jsonObject.getJSONArray("services_i");
                        for (int index=0;index<jsonArray1.length();index++)
                        {
                            JSONObject jsonObject1=jsonArray1.getJSONObject(index);
                            arrayListservices.add(new brandDetails(jsonObject1.getString("id"),jsonObject1.getString("name"),jsonObject1.getString("icon")));
                        }
                        adapter adapter1=new adapter();
                        adapter1.griddAdapters(serviceList,new brandsDetailsAdapter(context,arrayListservices),context,5);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                   Log.e("details",e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }

}
