package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.chassisNumberResultsData;
import com.carsyalla.www.cars.interfaces.chassisNumberSearch;

import pl.droidsonroids.gif.GifImageView;

public class chassieNumberResultsPresenter implements chassisNumberSearch.interfaces.presenter {
    private chassisNumberSearch.interfaces.Model model;
    private chassisNumberSearch.interfaces.View views;
    Context context;
    RecyclerView recyclerView;
    String chassieNum;
    ProgressBar loading;
    TextView nodata;
    GifImageView gifImageView;
    public chassieNumberResultsPresenter(chassisNumberSearch.interfaces.View views, Context context, RecyclerView recyclerView, String chassieNum,ProgressBar loading,    TextView nodata,GifImageView gifImageView) {
        this.views = views;
        this.context = context;
        this.recyclerView = recyclerView;
        this.chassieNum=chassieNum;
        this.loading=loading;
        this.nodata=nodata;
        this.gifImageView=gifImageView;
        initPresenter();
    }

    private void initPresenter() {
        model = new chassisNumberResultsData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,chassieNum,loading,nodata,gifImageView);
    }
}
