package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.reservationDetailsData;
import com.carsyalla.www.cars.interfaces.reservationdetails;

import me.relex.circleindicator.CircleIndicator;

public class reservation_DetailsPresenter implements reservationdetails.interfaces.presenter {
    private reservationdetails.interfaces.Model model;
    private reservationdetails.interfaces.View views;
    Context context;
    RecyclerView serviceList,brandList;
    String center_id;
    TextView name,descripition,gouvernate,city,timeWork;
    RatingBar ratingBar;
    ViewPager viewPager;
    CircleIndicator circleIndicator;

    public reservation_DetailsPresenter(reservationdetails.interfaces.View view, Context context, String center_id, TextView name, RatingBar ratingBar, TextView descripition, final ViewPager viewPager, final CircleIndicator circleIndicator) {
        this.views = view;
        this.context=context;
        this.center_id=center_id;
        this.name=name;
        this.ratingBar=ratingBar;
        this.descripition=descripition;
        this.viewPager=viewPager;
        this.circleIndicator=circleIndicator;
        initPresenter();
    }

    public reservation_DetailsPresenter(reservationdetails.interfaces.View view, Context context, TextView gouvernate, TextView city, TextView timeWork, RecyclerView brandList, String center_id, RecyclerView serviceList)
    {
        this.views=view;
        this.context=context;
        this.gouvernate=gouvernate;
        this.timeWork=timeWork;
        this.brandList=brandList;
        this.center_id=center_id;
        this.serviceList=serviceList;
        this.city=city;
        initPresenter();
    }
    private void initPresenter() {
        model = new reservationDetailsData();

    }
    @Override
    public void getData() {
        model.getdata(context,center_id,name,ratingBar,descripition,viewPager,circleIndicator);
    }

    @Override
    public void getScdData() {
        model.getdataSnd(context,gouvernate,city,timeWork,brandList,center_id,serviceList);
    }
}
