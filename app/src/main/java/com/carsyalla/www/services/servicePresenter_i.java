package com.carsyalla.www.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.carsyalla.www.cars.Data.serviceData;
import com.carsyalla.www.cars.interfaces.services;

public class servicePresenter_i implements services_i.interfaces.presenter {
    private services_i.interfaces.Model model;
    private services_i.interfaces.View views;
    Context context;
    RecyclerView recyclerView;
    String brand_id;
    ProgressBar loading;
    public servicePresenter_i(services_i.interfaces.View view, Context context, RecyclerView recyclerView,String brand_id,ProgressBar loading) {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        this.brand_id=brand_id;
        this.loading=loading;
        initPresenter();
    }
    private void initPresenter() {
        model = new allserviceData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,brand_id,loading);
    }

}
