package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.carsyalla.www.cars.Adapter.centerAdapter;

import java.util.ArrayList;

public interface centers {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(RecyclerView recyclerView, Context context, String id, favourite.interfaces.View view, RecyclerView serviceList, ImageView ads, int pageNum, ProgressBar loading, String service_id);
            ArrayList getdata2(RecyclerView recyclerView, Context context,String id,RecyclerView serviceList,final favourite.interfaces.View view);
            ArrayList getdata3(RecyclerView recyclerView, Context context, String id, favourite.interfaces.View view, RecyclerView serviceList, ImageView ads, int pageNum, ProgressBar loading, String service_id);

        }

        interface presenter{
            void getData();
            void getData2();
            void getData3();
        }
    }
}
