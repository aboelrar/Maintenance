package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.carsyalla.www.cars.Data.brandsData;
import com.carsyalla.www.cars.interfaces.brands;

import me.relex.circleindicator.CircleIndicator;

public class brandPresenter implements brands.interfaces.presenter {
    private brands.interfaces.Model model;
    private brands.interfaces.View views;
    Context context;
    RecyclerView recyclerView;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    ProgressBar loading;

    public brandPresenter(brands.interfaces.View view, Context context, RecyclerView recyclerView,ViewPager viewPager,CircleIndicator circleIndicator,ProgressBar loading) {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        this.viewPager=viewPager;
        this.circleIndicator=circleIndicator;
        this.loading=loading;
        initPresenter();
    }
    private void initPresenter() {
        model = new brandsData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,loading);
    }

    @Override
    public void getSliderData() {
        model.getSlider(viewPager,context,circleIndicator);
        }

}

