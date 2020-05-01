package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.carsyalla.www.cars.Data.gouvernateData;
import com.carsyalla.www.cars.interfaces.gouvernate;

public class gouvernatePresenter implements gouvernate.interfaces.presenter {
    private gouvernate.interfaces.Model model;
    private gouvernate.interfaces.View views;
    Context context;
    RecyclerView recyclerView;

    public gouvernatePresenter(gouvernate.interfaces.View view, Context context, RecyclerView recyclerView) {
        this.views = view;
        this.context=context;
        this.recyclerView=recyclerView;
        initPresenter();
    }
    private void initPresenter() {
        model = new gouvernateData();

    }
    @Override
    public void getData() {
        model.getdata(recyclerView,context);
    }

}
