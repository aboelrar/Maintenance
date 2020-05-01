package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.carsyalla.www.cars.Data.model_reservationData;
import com.carsyalla.www.cars.interfaces.model_reservation;

public class model_reservationPresenter implements model_reservation.interfaces.presenter {
    private model_reservation.interfaces.Model model;
    private model_reservation.interfaces.View views;
    Context context;
    RecyclerView recyclerView;
    String brand_id;

    public model_reservationPresenter(model_reservation.interfaces.View view, Context context, RecyclerView recyclerView,String brand_id) {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        this.brand_id=brand_id;
        initPresenter();
    }
    private void initPresenter() {
        model = new model_reservationData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,brand_id);
    }

}
