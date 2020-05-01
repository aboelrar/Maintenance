package com.carsyalla.www.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public interface services_i {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(RecyclerView recyclerView, Context context, String brand_id, ProgressBar loading);
        }

        interface presenter{
            void getData();
        }
    }
}
