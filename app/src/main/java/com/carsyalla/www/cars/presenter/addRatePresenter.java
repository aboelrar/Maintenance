package com.carsyalla.www.cars.presenter;

import android.content.Context;

import com.carsyalla.www.cars.Data.addRateData;
import com.carsyalla.www.cars.interfaces.addRate;

public class addRatePresenter implements addRate.interfaces.presenter {
    private addRate.interfaces.Model model;
    private addRate.interfaces.View views;
    Context context;
    String user_id,center_id;
    int Rate;

    public addRatePresenter(addRate.interfaces.View views, Context context, String user_id, String center_id, int rate) {
        this.views = views;
        this.context = context;
        this.user_id = user_id;
        this.center_id = center_id;
        Rate = rate;
        initPresenter();
    }
    private void initPresenter() {
        model = new addRateData();
    }
    @Override
    public void getData() {
        model.getdata(user_id,center_id,context,Rate);
    }
}
