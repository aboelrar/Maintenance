package com.carsyalla.www.cars.presenter;

import android.content.Context;

import com.carsyalla.www.cars.Data.cancel_reservationData;
import com.carsyalla.www.cars.interfaces.cancel_reservation;

public class cancel_reservationPresenter implements cancel_reservation.interfaces.presenter {
    private cancel_reservation.interfaces.Model model;
    private cancel_reservation.interfaces.View views;
    Context context;
    String reservation_id,status_id;

    public cancel_reservationPresenter(cancel_reservation.interfaces.View view, Context context, String reservation_id,String status_id) {
        this.views = view;
        this.context=context;
        this.reservation_id=reservation_id;
        this.status_id=status_id;
        initPresenter();
    }
    private void initPresenter() {
        model = new cancel_reservationData();

    }
    @Override
    public void getData() {
         model.getdata(status_id,reservation_id,context);
    }
}
