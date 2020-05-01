package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.carsyalla.www.cars.Data.checkData;
import com.carsyalla.www.cars.interfaces.checkList;

public class checkPresenter implements checkList.interfaces.presenter {
    private checkList.interfaces.Model model;
    private checkList.interfaces.View views;
    Context context;
    RecyclerView recyclerView,recyclerView1;

    public checkPresenter(checkList.interfaces.View view, Context context, RecyclerView recyclerView) {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        this.recyclerView1=recyclerView1;
        initPresenter();
    }
    private void initPresenter() {
        model = new checkData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context);
    }

    @Override
    public void getSecondData() {
        model.getSeconddata(recyclerView,context);
    }

}
