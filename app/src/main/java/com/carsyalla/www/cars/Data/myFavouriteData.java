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
import com.carsyalla.www.cars.Adapter.centerAdapter;
import com.carsyalla.www.cars.Model.center;
import com.carsyalla.www.cars.Model.icons;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.interfaces.myFavourite;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class myFavouriteData implements myFavourite.interfaces.Model {
    apis apis=new apis();
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context, String id, final favourite.interfaces.View view, final ProgressBar loading, final TextView nodata, final GifImageView gifImageView) {
        String myFavourite=apis.myFavourite+"/"+new savedId().getId(context);
        final ArrayList<center>arrayList=new ArrayList<center>();
        Log.e("my_favourite",myFavourite);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, myFavourite, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1")) {
                        loading.setVisibility(View.GONE);
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
                        adapter adapter=new adapter();
                        recyclerView.setNestedScrollingEnabled(false);
                        recyclerView.setHasFixedSize(false);
                        adapter.Adapter(recyclerView,new centerAdapter(context,arrayList,view,loading,null),context);
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
                    Log.e("myfav",e.getLocalizedMessage());

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
