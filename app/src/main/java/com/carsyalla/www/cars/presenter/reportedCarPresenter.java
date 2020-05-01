package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.reportedCarData;
import com.carsyalla.www.cars.interfaces.reported_Car;

import pl.droidsonroids.gif.GifImageView;

public class reportedCarPresenter implements reported_Car.interfaces.presenter {
    private reported_Car.interfaces.Model model;
    private reported_Car.interfaces.View views;
    Context context;
    ProgressBar loading;
    RecyclerView recyclerView;
    int pageNum;
    GifImageView gifImageView;
    TextView nodata;
    public reportedCarPresenter(reported_Car.interfaces.View views, Context context, RecyclerView recyclerView, int pageNum,ProgressBar loading,TextView nodata,GifImageView gifImageView) {
        this.views = views;
        this.context = context;
        this.recyclerView = recyclerView;
        this.pageNum=pageNum;
        this.loading=loading;
        this.gifImageView=gifImageView;
        this.nodata=nodata;
        initPresenter();
    }

    private void initPresenter() {
        model = new reportedCarData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,pageNum,loading,gifImageView,nodata);
    }
}
