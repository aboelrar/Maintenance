package com.carsyalla.www.cars.presenter;

import android.content.Context;

import com.carsyalla.www.cars.Data.favouriteData;
import com.carsyalla.www.cars.interfaces.favourite;

public class favouritePresenter implements favourite.interfaces.presenter {
    private favourite.interfaces.Model model;
    private favourite.interfaces.View views;
    Context context;
    String id,url;

    public favouritePresenter(favourite.interfaces.View view, Context context,String url,String id) {
        this.views = view;
        this.context=context;
        this.url=url;
        this.id=id;
        initPresenter();
    }
    private void initPresenter() {
        model = new favouriteData();

    }
    @Override
    public void getData() {
        model.getdata(url,context,id);
    }
}
