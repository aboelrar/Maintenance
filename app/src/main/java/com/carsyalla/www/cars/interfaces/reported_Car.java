package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public interface reported_Car {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(RecyclerView recyclerView, Context context, int number, ProgressBar loading, GifImageView gifImageView, TextView nodata);
        }

        interface presenter{
            void getData();
        }
    }
}
