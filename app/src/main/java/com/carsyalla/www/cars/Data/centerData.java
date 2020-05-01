package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.Adapter.centerAdapter;
import com.carsyalla.www.cars.Adapter.centerAdapterSpecial;
import com.carsyalla.www.cars.Model.center;
import com.carsyalla.www.cars.Model.icons;
import com.carsyalla.www.cars.interfaces.centers;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.network.apis;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class centerData implements centers.interfaces.Model {
    static com.carsyalla.www.cars.library.savedId savedId=new savedId();
    static apis apis=new apis();
    int number=1;
    Boolean check=false;
    int x,y,z=0;
   static centerAdapter centerAdapter;

    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context, String id, final favourite.interfaces.View view, final RecyclerView servicelist, final ImageView ads, int num, final ProgressBar loading, final String service_id) {
        final ArrayList<center>arrayList=new ArrayList<center>();
        String centers=apis.center_search+"/"+id+"/"+service_id+"/0"+"/"+savedId.getId(context)+"/"+1;
        Log.e("allCenters",centers);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, centers, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1")) {
                        loading.setVisibility(View.GONE);
                        if(response.getString("ads")=="null")
                        {
                            ads.getLayoutParams().height = 0;
                        }
                        else {
                            Picasso.with(context).load(response.getString("ads")).into(ads);
                        }
                        JSONArray jsonArray = response.getJSONArray("centers");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            JSONArray serviceArray=jsonObject.getJSONArray("services");
                            ArrayList<icons>serviceList=new ArrayList<icons>();
                     for(int index_ser=0;index_ser<serviceArray.length();index_ser++)
                            {
                                JSONObject serviceObject=serviceArray.getJSONObject(index_ser);
                                serviceList.add(new icons(serviceObject.getString("id"),serviceObject.getString("icon")));
                                Log.e("serviceList",""+serviceList.size());
                            }
                            arrayList.add(new center(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("city"),jsonObject.getString("image"),jsonObject.getInt("favorite"),jsonObject.getInt("rating"),serviceList,jsonObject.getString("discount"),jsonObject.getString("special")));
                        }
                        centerAdapter  =new centerAdapter(context,arrayList,view,loading,service_id);
                        recyclerView.setAdapter(centerAdapter);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        ads.getLayoutParams().height = 0;

                    }
                    else if(response.getString("status").equals("3"))
                    {
                        loading.setVisibility(View.GONE);
                        ads.getLayoutParams().height = 0;

                    }
                } catch (JSONException e) {
                    Log.e("brand",e.getLocalizedMessage());
                    loading.setVisibility(View.GONE);
                    ads.getLayoutParams().height = 0;

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لايوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
                ads.getLayoutParams().height = 0;

            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }

    @Override
    public ArrayList getdata2(final RecyclerView recyclerView, final Context context, String id, final RecyclerView servicelist,final favourite.interfaces.View view) {
        final ArrayList<center>arrayList=new ArrayList<center>();
        final ArrayList<icons>serviceList=new ArrayList<icons>();
        String centers=apis.specialCenters+savedId.getId(context)+"/"+"1";
        Log.e("apispecial",centers);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, centers, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1")) {
                        JSONArray jsonArray = response.getJSONArray("centers");
                        for (int index=0;index<jsonArray.length();index++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(index);
                            JSONArray serviceArray=jsonObject.getJSONArray("services");
                            ArrayList<icons>serviceList=new ArrayList<icons>();
                            for(int index_ser=0;index_ser<serviceArray.length();index_ser++)
                            {

                                JSONObject serviceObject=serviceArray.getJSONObject(index_ser);
                                serviceList.add(new icons(serviceObject.getString("id"),serviceObject.getString("icon")));
                                Log.e("serviceList",""+serviceList.size());
                            }
                            arrayList.add(new center(jsonObject.getString("id"), jsonObject.getString("name"), jsonObject.getString("city"), jsonObject.getString("image"), jsonObject.getInt("favorite"),jsonObject.getInt("rating"),serviceList,jsonObject.getString("discount"),null));
                        }
                        adapter adapter=new adapter();
                        adapter.Adapter(recyclerView,new centerAdapterSpecial(context,arrayList,view),context);
                        recyclerView.setNestedScrollingEnabled(false);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                    }
                    else if(response.getString("status").equals("3"))
                    {
               //         Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("brand",e.getLocalizedMessage());
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
    public ArrayList getdata3(final RecyclerView recyclerView, final Context context, String id, final favourite.interfaces.View view, RecyclerView serviceList, final ImageView ads, int pageNum, final ProgressBar loading, final String service_id) {
        final ArrayList<center>arrayList=new ArrayList<center>();
        String centers=apis.center_search+"/"+id+"/"+service_id+"/0"+"/"+savedId.getId(context)+"/"+number;
        Log.e("allCenters",centers);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, centers, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1")) {
                        loading.setVisibility(View.GONE);
                        if(response.getString("ads")=="null")
                        {
                            ads.getLayoutParams().height = 0;
                        }
                        else {
                            Picasso.with(context).load(response.getString("ads")).into(ads);
                        }
                        JSONArray jsonArray = response.getJSONArray("centers");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            JSONArray serviceArray=jsonObject.getJSONArray("services");
                            ArrayList<icons>serviceList=new ArrayList<icons>();
                            for(int index_ser=0;index_ser<serviceArray.length();index_ser++)
                            {

                                JSONObject serviceObject=serviceArray.getJSONObject(index_ser);
                                serviceList.add(new icons(serviceObject.getString("id"),serviceObject.getString("icon")));
                                Log.e("serviceList",""+serviceList.size());
                            }
                            arrayList.add(new center(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("city"),jsonObject.getString("image"),jsonObject.getInt("favorite"),jsonObject.getInt("rating"),serviceList,jsonObject.getString("discount"),jsonObject.getString("special")));
                        }
                        centerAdapter.addList(arrayList);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        ads.getLayoutParams().height = 0;
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        loading.setVisibility(View.GONE);
                        ads.getLayoutParams().height = 0;

                    }
                } catch (JSONException e) {
                    Log.e("brand",e.getLocalizedMessage());
                    loading.setVisibility(View.GONE);
                    ads.getLayoutParams().height = 0;

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "لايوجد اتصال بالخادم", Toast.LENGTH_SHORT).show();
                ads.getLayoutParams().height = 0;

            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }


}
