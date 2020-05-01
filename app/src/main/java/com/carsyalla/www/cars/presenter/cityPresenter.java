package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.carsyalla.www.cars.Data.cityData;
import com.carsyalla.www.cars.interfaces.city;

public class cityPresenter implements city.interfaces.presenter {
    private city.interfaces.Model model;
    private city.interfaces.View views;
    Context context;
    RecyclerView recyclerView;
    String id,gouvernate_name;

    public cityPresenter(city.interfaces.View view, Context context, RecyclerView recyclerView,String id,String gouvernate_name) {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        this.id=id;
        this.gouvernate_name=gouvernate_name;
        initPresenter();
    }
    private void initPresenter() {
        model = new cityData();
    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,id,gouvernate_name);
    }

}
