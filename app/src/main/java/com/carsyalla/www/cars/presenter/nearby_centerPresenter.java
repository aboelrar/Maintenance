package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.carsyalla.www.cars.Data.nearby_centerData;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.interfaces.nearby_centers;

public class nearby_centerPresenter implements nearby_centers.interfaces.presenter {
    private nearby_centers.interfaces.Model model;
    private nearby_centers.interfaces.View views;
    private favourite.interfaces.View view;
    Context context;
    RecyclerView recyclerView,recyclerView1,serviceList;
    String id,service_id;
    ImageView ads;
    int pageNum;
    ProgressBar loading;
    String lat_lng;

    public nearby_centerPresenter(nearby_centers.interfaces.View view, Context context, RecyclerView recyclerView, RecyclerView recyclerView1, String id, favourite.interfaces.View view1, ImageView ads, RecyclerView serviceList, int pageNum, ProgressBar loading,String lat_lng,String service_id) {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        this.recyclerView1=recyclerView1;
        this.id=id;
        this.view=view1;
        this.pageNum=pageNum;
        this.ads=ads;
        this.serviceList=serviceList;
        this.loading=loading;
        this.lat_lng=lat_lng;
        this.service_id=service_id;
        initPresenter();
    }
    private void initPresenter() {
        model = new nearby_centerData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,id,view,serviceList,ads,pageNum,loading,lat_lng,service_id);
    }

    @Override
    public void getData2() {
        //model.getdata2(recyclerView1,context,id,null,view);
    }

}

