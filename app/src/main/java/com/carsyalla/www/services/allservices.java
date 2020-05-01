package com.carsyalla.www.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.all_service;
import com.carsyalla.www.cars.presenter.servicePresenter;

public class allservices extends AppCompatActivity implements services_i.interfaces.View {
    RecyclerView serviceList;
    servicePresenter_i servicePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allservices);
        Intent intent=getIntent();
        String brand_id=intent.getStringExtra("brand_id");
        serviceList=(RecyclerView)findViewById(R.id.serviceList);
        ProgressBar loading=(ProgressBar)findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);
        servicePresenter=new servicePresenter_i(allservices.this,allservices.this,serviceList,brand_id,loading);
        servicePresenter.getData();
    }
}
