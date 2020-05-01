package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.carsyalla.www.cars.Data.centerData;
import com.carsyalla.www.cars.interfaces.centers;
import com.carsyalla.www.cars.interfaces.favourite;

public class centerPresenter implements centers.interfaces.presenter {
    private centers.interfaces.Model model;
    private centers.interfaces.View views;
    private favourite.interfaces.View view;
    Context context;
    RecyclerView recyclerView,recyclerView1,serviceList;
    String id,service_id;
    ImageView ads;
    int pageNum;
    ProgressBar loading;

    public centerPresenter(centers.interfaces.View view, Context context, RecyclerView recyclerView,RecyclerView recyclerView1,String id,favourite.interfaces.View view1,ImageView ads,RecyclerView serviceList,int pageNum,ProgressBar loading,String service_id) {
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
        this.service_id=service_id;
        initPresenter();
    }
    private void initPresenter() {
        model = new centerData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context,id,view,serviceList,ads,pageNum,loading,service_id);
    }

    @Override
    public void getData2() {
      //  model.getdata2(recyclerView1,context,id,null,view);
    }

    @Override
    public void getData3() {
        model.getdata3(recyclerView,context,id,view,serviceList,ads,pageNum,loading,service_id);
    }

}

