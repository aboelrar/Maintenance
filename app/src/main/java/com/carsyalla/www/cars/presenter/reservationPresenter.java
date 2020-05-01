package com.carsyalla.www.cars.presenter;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.reservationData;
import com.carsyalla.www.cars.interfaces.reservation;

public class reservationPresenter implements reservation.interfaces.presenter {
    private reservation.interfaces.Model model;
    private reservation.interfaces.View views;
    Context context;
    EditText name,phone,model_year;
    String id_brand,id_model,id_user,id_center;
    TextView reservation_date;
    Dialog dialog;

    public reservationPresenter(reservation.interfaces.View view, Context context,EditText name,EditText phone,EditText model_year,TextView reservation_date,String id_brand,String id_model,String id_user,String id_center,Dialog dialog) {
        this.views = view;
        this.context=context;
        this.name=name;
        this.phone=phone;
        this.model_year=model_year;
        this.reservation_date=reservation_date;
        this.id_brand=id_brand;
        this.id_model=id_model;
        this.dialog=dialog;
        this.id_user=id_user;
        this.id_center=id_center;
        initPresenter();
    }
    private void initPresenter() {
        model = new reservationData();
    }
    @Override
    public void getData() {
        String data=model.getdata(name,phone,id_brand,id_model,context,model_year,reservation_date,id_user,id_center,dialog);
    }

}
