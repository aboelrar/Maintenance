package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.reservation_details_data;
import com.carsyalla.www.cars.interfaces.reservation_details;

public class reservationDetailsPresenter implements reservation_details.interfaces.presenter {
    private reservation_details.interfaces.Model model;
    private reservation_details.interfaces.View views;
    Context context;
    TextView center_title,date,phone,car_model,gouvernate,city,address;
    LinearLayout viewMap;
    String id_reservation;

    public reservationDetailsPresenter( reservation_details.interfaces.View views, Context context, TextView center_title, TextView date, TextView phone, TextView car_model, TextView gouvernate, TextView city, TextView address, LinearLayout viewMap,String id_reservation) {
        this.views = views;
        this.context = context;
        this.center_title = center_title;
        this.date = date;
        this.phone = phone;
        this.car_model = car_model;
        this.gouvernate = gouvernate;
        this.city = city;
        this.address = address;
        this.viewMap = viewMap;
        this.id_reservation=id_reservation;
        initPresenter();
    }

    private void initPresenter() {
        model = new reservation_details_data();

    }
    @Override
    public void getData() {
        model.getdata(context,center_title,date,phone,car_model,gouvernate,city,address,viewMap,id_reservation);
    }

}
