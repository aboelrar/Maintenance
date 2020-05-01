package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.stolenCarData;
import com.carsyalla.www.cars.interfaces.stolen_Car;

import pl.droidsonroids.gif.GifImageView;

public class stolenCarPresenter implements stolen_Car.interfaces.presenter {
    private stolen_Car.interfaces.Model model;
    private stolen_Car.interfaces.View views;
    Context context;
    GifImageView gifImageView;
    RecyclerView recyclerView;
    ProgressBar loading;
    int pageNum;
    TextView nodata;
    public stolenCarPresenter( stolen_Car.interfaces.View views, Context context, RecyclerView recyclerView, int pageNum,ProgressBar loading,GifImageView gifImageView,TextView nodata) {
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
        model = new stolenCarData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,pageNum,loading,gifImageView,nodata);
    }
}
