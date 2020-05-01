package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.center_search_nameData;
import com.carsyalla.www.cars.interfaces.center_search_name;
import com.carsyalla.www.cars.interfaces.favourite;

import pl.droidsonroids.gif.GifImageView;

public class center_search_name_presenter implements center_search_name.interfaces.presenter {
    private center_search_name.interfaces.Model model;
    private center_search_name.interfaces.View views;
    private favourite.interfaces.View view;
    Context context;
    RecyclerView recyclerView;
    String name,user_id;
    int pageNum;
    ProgressBar loading;
    TextView nodata;
    GifImageView gifImageView;

    public center_search_name_presenter(center_search_name.interfaces.View views, favourite.interfaces.View view, Context context, RecyclerView recyclerView, String name, String user_id, int pageNum,ProgressBar loading,GifImageView gifImageView,TextView nodata) {
        this.views = views;
        this.view = view;
        this.context = context;
        this.recyclerView = recyclerView;
        this.name=name;
        this.user_id = user_id;
        this.gifImageView=gifImageView;
        this.nodata=nodata;
        this.pageNum = pageNum;
        this.loading=loading;
        initPresenter();
    }

    private void initPresenter() {
        model = new center_search_nameData();
        }

    @Override
    public void getData() {
         model.getdata(recyclerView,context,name,user_id,pageNum,view,loading,gifImageView,nodata);
    }
}
