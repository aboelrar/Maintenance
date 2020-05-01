package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.my_reservationData;
import com.carsyalla.www.cars.interfaces.addRate;
import com.carsyalla.www.cars.interfaces.cancel_reservation;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.interfaces.myReservation;

import pl.droidsonroids.gif.GifImageView;

public class my_reservationPresenter implements myReservation.interfaces.presenter {
    private myReservation.interfaces.Model model;
    private myReservation.interfaces.View views;
    private addRate.interfaces.View view1;
    private favourite.interfaces.View view;
    cancel_reservation.interfaces.View cancel_view;
    Context context;
    RecyclerView recyclerView;
    TextView nodata;
    GifImageView gifImageView;
    ProgressBar loading;

    public my_reservationPresenter( myReservation.interfaces.View views, favourite.interfaces.View view, Context context, RecyclerView recyclerView,addRate.interfaces.View view1,cancel_reservation.interfaces.View cancel_view,TextView nodata,GifImageView gifImageView,ProgressBar loading) {
        this.views = views;
        this.view = view;
        this.context = context;
        this.recyclerView = recyclerView;
        this.cancel_view=cancel_view;
        this.view1=view1;
        this.gifImageView=gifImageView;
        this.nodata=nodata;
        this.loading=loading;
        initPresenter();
    }

    private void initPresenter() {
        model = new my_reservationData();

    }

    @Override
    public void getData() {
     model.getdata(recyclerView,context,view,view1,cancel_view,nodata,gifImageView,loading);
    }
}
