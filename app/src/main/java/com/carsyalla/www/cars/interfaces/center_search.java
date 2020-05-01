package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public interface center_search {
    public interface interfaces {
        interface  View
        {
        }

        interface Model{
            ArrayList getdata(RecyclerView recyclerView, Context context, String brand_id, String service_id, String city_id, String user_id, int pageNum, final favourite.interfaces.View view, ProgressBar loading, GifImageView gifImageView, TextView nodata);

        }

        interface presenter{
            void getData();
        }
    }
}
