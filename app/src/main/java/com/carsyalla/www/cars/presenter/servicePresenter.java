package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.carsyalla.www.cars.Data.serviceData;
import com.carsyalla.www.cars.interfaces.services;

public class servicePresenter implements services.interfaces.presenter {
    private services.interfaces.Model model;
    private services.interfaces.View views;
    Context context;
    RecyclerView recyclerView;

    public servicePresenter(services.interfaces.View view, Context context, RecyclerView recyclerView) {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        initPresenter();
    }
    private void initPresenter() {
        model = new serviceData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context);
    }

}
