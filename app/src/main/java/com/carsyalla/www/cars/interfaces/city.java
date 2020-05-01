package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public interface city {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(RecyclerView recyclerView, Context context,String id,String gouvernate_name);
        }

        interface presenter{
            void getData();
        }
    }
}
