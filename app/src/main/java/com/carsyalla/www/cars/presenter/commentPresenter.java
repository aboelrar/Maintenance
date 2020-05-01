package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.commentData;
import com.carsyalla.www.cars.interfaces.comments;

public class commentPresenter implements comments.interfaces.presenter {
    private comments.interfaces.Model model;
    private comments.interfaces.View views;
    Context context;
    String center_id;
    RecyclerView recyclerView;
    TextView nodata;

    public commentPresenter(comments.interfaces.View views, Context context, String center_id,RecyclerView recyclerView,TextView nodata) {
        this.views = views;
        this.context = context;
        this.center_id = center_id;
        this.recyclerView=recyclerView;
        this.nodata=nodata;
        initPresenter();
    }

    private void initPresenter() {
        model = new commentData();
    }

    @Override
    public void getData() {
     model.getdata(recyclerView,context,center_id,1,nodata);
    }
}
