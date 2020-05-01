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
import com.carsyalla.www.cars.Adapter.center_search_Adapter;
import com.carsyalla.www.cars.Model.icons;
import com.carsyalla.www.cars.Model.searchCenter;
import com.carsyalla.www.cars.interfaces.center_search;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class center_searchData implements center_search.interfaces.Model {
    apis apis=new apis();
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context, String brand_id, String service_id, String city_id, String user_id, int pageNum, final favourite.interfaces.View view, final ProgressBar loading, final GifImageView gifImageView, final TextView nodata) {
       final ArrayList<searchCenter>arrayList=new ArrayList<searchCenter>();
       String search_url=apis.center_search+"/"+brand_id+"/"+service_id+"/"+city_id+"/"+user_id+"/"+pageNum;
       Log.e("search_url",search_url);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, search_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("status").equals("1"))
                    {
                        loading.setVisibility(View.GONE);
                        // loading.setVisibility(View.GONE);
                        JSONArray jsonArray=response.getJSONArray("centers");
                        for (int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            ArrayList<icons>serviceList=new ArrayList<icons>();
                            JSONArray serviceArray=jsonObject.getJSONArray("services");
                            for(int index_ser=0;index_ser<serviceArray.length();index_ser++)
                            {
                                JSONObject serviceObject=serviceArray.getJSONObject(index_ser);
                                serviceList.add(new icons(serviceObject.getString("id"),serviceObject.getString("icon")));
                                Log.e("serviceList",""+serviceList.size());
                            }
                            arrayList.add(new searchCenter(jsonObject.getString("id"),jsonObject.getString("name"),null,jsonObject.getString("image"),jsonObject.getInt("favorite"),jsonObject.getInt("rating"),serviceList,jsonObject.getString("discount"),jsonObject.getString("special")));
                        }
                        adapter adapter=new adapter();
                        adapter.Adapter(recyclerView,new center_search_Adapter(context,arrayList,view),context);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                  //      loading.setVisibility(View.GONE);
                        loading.setVisibility(View.GONE);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                     //   loading.setVisibility(View.GONE);
                        loading.setVisibility(View.GONE);
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        nodata.setText(""+response.getString("message"));
                        gifImageView.setImageResource(R.drawable.datanotfound);
                    }
                } catch (JSONException e) {
                  //  loading.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                    Log.e("center_search",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errors",error.getLocalizedMessage());
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
