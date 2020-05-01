package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.center_searchData;
import com.carsyalla.www.cars.interfaces.center_search;
import com.carsyalla.www.cars.interfaces.favourite;

import pl.droidsonroids.gif.GifImageView;

public class center_search_presenter implements center_search.interfaces.presenter {
    private center_search.interfaces.Model model;
    private center_search.interfaces.View views;
    private favourite.interfaces.View view;
    Context context;
    GifImageView gifImageView;
    RecyclerView recyclerView;
    String brand_id,service_id,city_id,user_id;
    int pageNum;
    ProgressBar loading;
     TextView nodata;

    public center_search_presenter(center_search.interfaces.View views, favourite.interfaces.View view, Context context, RecyclerView recyclerView, String brand_id, String service_id, String city_id, String user_id, int pageNum,ProgressBar loading,GifImageView  gifImageView, final TextView nodata) {
        this.views = views;
        this.view = view;
        this.context = context;
        this.recyclerView = recyclerView;
        this.brand_id = brand_id;
        this.service_id = service_id;
        this.city_id = city_id;
        this.user_id = user_id;
        this.loading=loading;
        this.gifImageView=gifImageView;
        this.pageNum = pageNum;
        this.nodata=nodata;
        initPresenter();
    }

    private void initPresenter() {
        model = new center_searchData();
        }

    @Override
    public void getData() {
         model.getdata(recyclerView,context,brand_id,service_id,city_id,user_id,pageNum,view,loading,gifImageView,nodata);
    }
}
