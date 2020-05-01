package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.widget.EditText;

import com.carsyalla.www.cars.Data.addCenterData;
import com.carsyalla.www.cars.interfaces.addCenter;

import java.util.ArrayList;

public class addCenterPresenter implements addCenter.interfaces.presenter {
    private addCenter.interfaces.Model model;
    private addCenter.interfaces.View views;
    Context context;
    EditText name,descripition,email,phone,address,workTimeFrom,workTimeTo,workName,discount,holidays;
    String gouvernateId,cityId,lat,lng;
    String mainImage;
    ArrayList<String>brands,service,Album;

    public addCenterPresenter(addCenter.interfaces.View views, Context context, EditText name, EditText descripition, EditText email, EditText phone, EditText address, EditText workTimeFrom,EditText workTimeTo, EditText workName, String gouvernateId, String cityId, String lat, String lng,String mainImage,ArrayList<String>brands,ArrayList<String>service,ArrayList<String>Album,EditText discount,EditText holidays) {
        this.views = views;
        this.context = context;
        this.name = name;
        this.descripition = descripition;
        this.email = email;
        this.discount=discount;
        this.phone = phone;
        this.address = address;
        this.workTimeFrom = workTimeFrom;
        this.workTimeTo=workTimeTo;
        this.workName = workName;
        this.gouvernateId = gouvernateId;
        this.cityId = cityId;
        this.lat = lat;
        this.lng = lng;
        this.mainImage=mainImage;
        this.brands=brands;
        this.service=service;
        this.Album=Album;
        this.holidays=holidays;
        initPresenter();
    }

    private void initPresenter() {
        model = new addCenterData();
    }
    @Override
    public void getData() {
        String data=model.getdata(name,descripition,email,phone,gouvernateId,cityId,address,lat,lng,workTimeFrom,workTimeTo,workName,context,mainImage,brands,service,Album,discount,holidays);
    }
}
