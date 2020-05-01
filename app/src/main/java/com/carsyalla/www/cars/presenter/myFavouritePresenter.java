package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.myFavouriteData;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.interfaces.myFavourite;

import pl.droidsonroids.gif.GifImageView;

public class myFavouritePresenter implements myFavourite.interfaces.presenter {
    private myFavourite.interfaces.Model model;
    private myFavourite.interfaces.View views;
    private favourite.interfaces.View view;
    Context context;
    RecyclerView recyclerView;
    GifImageView gifImageView;
    String id;
    ProgressBar loading;
    TextView nodata;

    public myFavouritePresenter(myFavourite.interfaces.View view, Context context, RecyclerView recyclerView, String id, favourite.interfaces.View view1, ProgressBar loading,TextView nodata,GifImageView gifImageView)
     {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        this.id=id;
        this.view=view1;
        this.loading=loading;
        this.nodata=nodata;
        this.gifImageView=gifImageView;
        initPresenter();
    }
    private void initPresenter() {
        model = new myFavouriteData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,id,view,loading,nodata,gifImageView);
    }

}

