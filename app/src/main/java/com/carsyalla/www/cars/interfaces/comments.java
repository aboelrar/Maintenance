package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public interface comments  {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(RecyclerView recyclerView, Context context, String center_id, int pageNum, TextView nodata);
        }

        interface presenter{
            void getData();
        }
    }
}
