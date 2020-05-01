package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.plateNumResultsData;
import com.carsyalla.www.cars.interfaces.plateNumSearch;

import pl.droidsonroids.gif.GifImageView;

public class plateNumResultsPresenter implements plateNumSearch.interfaces.presenter {
    private plateNumSearch.interfaces.Model model;
    private plateNumSearch.interfaces.View views;
    Context context;
    RecyclerView recyclerView;
    String plateType,plateNum;
    ProgressBar loading;
    TextView nodata;
    GifImageView gifImageView;
    public plateNumResultsPresenter(plateNumSearch.interfaces.View views, Context context, RecyclerView recyclerView, String plateType,String plateNum,ProgressBar loading,    GifImageView gifImageView,TextView nodata) {
        this.views = views;
        this.context = context;
        this.recyclerView = recyclerView;
        this.plateNum=plateNum;
        this.plateType=plateType;
        this.loading=loading;
        this.nodata=nodata;
        this.gifImageView=gifImageView;
        initPresenter();
    }

    private void initPresenter() {
        model = new plateNumResultsData();
    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,plateType,plateNum,loading,gifImageView,nodata);
    }
}
