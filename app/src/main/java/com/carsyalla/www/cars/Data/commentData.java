package com.carsyalla.www.cars.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.carsyalla.www.cars.Adapter.commentAdapter;
import com.carsyalla.www.cars.Model.comment;
import com.carsyalla.www.cars.interfaces.comments;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.network.apis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class commentData implements comments.interfaces.Model {
    apis apis=new apis();
    @Override
    public ArrayList getdata(final RecyclerView recyclerView, final Context context, String center_id, int pageNum, final TextView nodata) {
        final ArrayList<comment>arrayList=new ArrayList<comment>();
        String comments=apis.center_comments+"/"+center_id+"/"+"1";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, comments, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("status").equals("1"))
                    {
                        JSONArray jsonArray=response.getJSONArray("comments");
                        for(int index=0;index<jsonArray.length();index++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(index);
                            arrayList.add(new comment(null,null,jsonObject.getString("userName"),jsonObject.getString("comment"),jsonObject.getString("date")));
                        }
                        adapter adapter=new adapter();
                        adapter.Adapter(recyclerView,new commentAdapter(context,arrayList),context);
                    }
                    else if(response.getString("status").equals("2"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("status").equals("3"))
                    {
                        Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        nodata.setText(response.getString("message"));
                    }
                } catch (JSONException e) {
                    Log.e("comments",e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errors", error.getLocalizedMessage());
            }
        });
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        return null;
    }
}
