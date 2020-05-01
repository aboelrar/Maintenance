package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.Adapter.brandsDetailsAdapter;
import com.carsyalla.www.cars.Adapter.slider.pagerAdapter;
import com.carsyalla.www.cars.LoginAndRegist;
import com.carsyalla.www.cars.Model.brandDetails;
import com.carsyalla.www.cars.Model.slider;

import com.carsyalla.www.cars.interfaces.centerdetails;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.carsyalla.www.cars.presenter.favouritePresenter;
import me.relex.circleindicator.CircleIndicator;

public class centerDetailsData implements centerdetails.interfaces.Model {
    int count=-2;
    apis apis=new apis();
    String center_Id;
    Boolean isfav=false;
    @Override
    public ArrayList getdata(final Context context, final String center_Id, final TextView name, final RatingBar ratingBar, final TextView descripition, final ViewPager viewPager, final CircleIndicator circleIndicator, final ImageView favourite, final favourite.interfaces.View view) {
       this.center_Id=center_Id;
         final String centerDetails=apis.centerDetails+"/"+center_Id+"/"+new savedId().getId(context);
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


                        if(jsonObject.getInt("favorite")==1)
                        {
                            favourite.setImageResource(R.drawable.redfav);
                            isfav = true;
                        }
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

                        //Check favourite
                        //FAV CLICK ON
                        favourite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(new savedId().getUserBoolean(context)==false)
                                {
                                    Intent intent=new Intent(context, LoginAndRegist.class);
                                    intent.putExtra("res","favdetails");
                                    context.startActivity(intent);
                                    Toast.makeText(context, "يرجى تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                if(isfav==false)
                                {
                                    favourite.setImageResource(R.drawable.redfav);
                                    favouritePresenter favouritePresenter=new favouritePresenter(view,context,apis.addFavoriteCenter,center_Id);
                                    favouritePresenter.getData();
                                    isfav=true;
                                }else if(isfav==true)
                                {
                                    favourite.setImageResource(R.drawable.whitefav);
                                    favouritePresenter  favouritePresenter=new favouritePresenter(view,context,apis.deleteFavouriteCenter,center_Id);
                                    favouritePresenter.getData();
                                    isfav=false;
                                }
                            }}
                        });

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
                Toast.makeText(context, "لايوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }

    @Override
    public ArrayList getdataSnd(final Context context, final TextView gouvernate, final TextView city, final TextView timeWork, final RecyclerView brandList, String center_id, final RecyclerView serviceList, final ImageView fav, final TextView holidays, final TextView disccount, final LinearLayout discount_item) {
        String centerDetails=apis.centerDetails+"/"+center_id;
        Log.e("myfav",centerDetails);
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
                        holidays.setText(jsonObject.getString("day_off"));

                        if(jsonObject.getString("discount").equals("0"))
                        {
                            ViewGroup.LayoutParams params = discount_item.getLayoutParams();
                            params.height = 0;
                            params.width = 0;
                            discount_item.setLayoutParams(params);
                        }
                        else {
                            disccount.setText(jsonObject.getString("discount"));
                        }
                       // holidays.setText(jsonObject.getString());
                  //get brand list
                        JSONArray jsonArray=jsonObject.getJSONArray("brands");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject1=jsonArray.getJSONObject(index);
                            arrayListBrands.add(new brandDetails(jsonObject1.getString("id"),jsonObject1.getString("name"),jsonObject1.getString("icon")));
                        }
                    adapter adapter=new adapter();
                    adapter.griddAdapters(brandList,new brandsDetailsAdapter(context,arrayListBrands),context,3);

                        //get service list
                        JSONArray jsonArray1=jsonObject.getJSONArray("services");
                        for (int index=0;index<jsonArray1.length();index++)
                        {
                            JSONObject jsonObject1=jsonArray1.getJSONObject(index);
                            arrayListservices.add(new brandDetails(jsonObject1.getString("id"),jsonObject1.getString("name"),jsonObject1.getString("icon")));
                        }
                        adapter adapter1=new adapter();
                        adapter1.griddAdapters(serviceList,new brandsDetailsAdapter(context,arrayListservices),context,3);
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
