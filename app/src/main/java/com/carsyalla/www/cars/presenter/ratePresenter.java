package com.carsyalla.www.cars.presenter;

import android.content.Context;

import com.carsyalla.www.cars.Data.rateData;

public class ratePresenter implements com.carsyalla.www.cars.interfaces.rate.interfaces.presenter {
    private com.carsyalla.www.cars.interfaces.rate.interfaces.Model model;
    private com.carsyalla.www.cars.interfaces.rate.interfaces.View views;
    Context context;
    String privacy,comment,center_id,reservation_id,user_id;
    int rate;

    public ratePresenter(com.carsyalla.www.cars.interfaces.rate.interfaces.View view, Context context, String privacy, String comment, String center_id, String reservation_id, String user_id, int rate) {
        this.views = view;
        this.context=context;
        this.privacy=privacy;
        this.comment=comment;
        this.center_id=center_id;
        this.reservation_id=reservation_id;
        this.user_id=user_id;
        this.rate=rate;
        initPresenter();
    }
    private void initPresenter() {
        model = new rateData();
    }
    @Override
    public void getData() {
        String data=model.getdata(privacy,comment,center_id,reservation_id,user_id,rate,context);
    }
}
