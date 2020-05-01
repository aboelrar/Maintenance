package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public interface myReservation {

    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(RecyclerView recyclerView, Context context, favourite.interfaces.View view, final addRate.interfaces.View view1, cancel_reservation.interfaces.View cancel_view, final TextView nodata, final GifImageView gifImageView, ProgressBar loading);

        }

        interface presenter{
            void getData();
        }
    }

}
