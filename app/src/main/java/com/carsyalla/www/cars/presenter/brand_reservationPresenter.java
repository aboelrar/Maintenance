package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.carsyalla.www.cars.Data.brand_reservationData;
import com.carsyalla.www.cars.interfaces.brand_reservation;

public class brand_reservationPresenter implements brand_reservation.interfaces.presenter {
    private brand_reservation.interfaces.Model model;
    private brand_reservation.interfaces.View views;
    Context context;
    RecyclerView recyclerView;


    public brand_reservationPresenter(brand_reservation.interfaces.View view, Context context, RecyclerView recyclerView) {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        initPresenter();
    }
    private void initPresenter() {
        model = new brand_reservationData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context);
    }

}
