package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.centerDetailsData;
import com.carsyalla.www.cars.interfaces.centerdetails;

import com.carsyalla.www.cars.interfaces.favourite;
import me.relex.circleindicator.CircleIndicator;

public class centerDetailsPresenter implements centerdetails.interfaces.presenter {
    private centerdetails.interfaces.Model model;
    private centerdetails.interfaces.View views;
    favourite.interfaces.View setfav_view;
    Context context;
    RecyclerView serviceList,brandList;
    String center_id;
    TextView name,descripition,gouvernate,city,timeWork,holidays,discount;
    RatingBar ratingBar;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    ImageView fav;
    LinearLayout discount_item;

    public centerDetailsPresenter(centerdetails.interfaces.View view, Context context,String center_id, TextView name, RatingBar ratingBar, TextView descripition,final ViewPager viewPager, final CircleIndicator circleIndicator,ImageView fav,favourite.interfaces.View setfav_view) {
        this.views = view;
        this.context=context;
        this.center_id=center_id;
        this.name=name;
        this.ratingBar=ratingBar;
        this.descripition=descripition;
        this.viewPager=viewPager;
        this.circleIndicator=circleIndicator;
        this.fav=fav;
        this.setfav_view=setfav_view;

        initPresenter();
    }

    public centerDetailsPresenter(centerdetails.interfaces.View view, Context context,TextView gouvernate,TextView city,TextView timeWork, RecyclerView brandList,String center_id,RecyclerView serviceList,TextView holidays,ImageView fav,TextView discount,LinearLayout discount_item)
    {
        this.views=view;
        this.context=context;
        this.gouvernate=gouvernate;
        this.timeWork=timeWork;
        this.brandList=brandList;
        this.center_id=center_id;
        this.serviceList=serviceList;
        this.city=city;
        this.holidays=holidays;
        this.fav=fav;
        this.discount=discount;
        this.discount_item=discount_item;
        initPresenter();
    }
    private void initPresenter() {
        model = new centerDetailsData();

    }
    @Override
    public void getData() {
        model.getdata(context,center_id,name,ratingBar,descripition,viewPager,circleIndicator,fav,setfav_view);
    }

    @Override
    public void getScdData() {
        model.getdataSnd(context,gouvernate,city,timeWork,brandList,center_id,serviceList,fav,holidays,discount,discount_item);
    }
}
