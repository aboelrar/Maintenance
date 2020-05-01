package com.carsyalla.www.cars.presenter;

import android.content.Context;

import com.carsyalla.www.cars.Data.addStolenData;
import com.carsyalla.www.cars.interfaces.add_stolen_car;

import java.util.ArrayList;

public class addStolenPresenter implements add_stolen_car.interfaces.presenter {
    private add_stolen_car.interfaces.Model model;
    private add_stolen_car.interfaces.View views;
    Context context;
    String brand_id,model_id,year_model,phone,id_city,plate_type,serial,plate_number,color,owner_address,notes,main_image,license,user_id;
    ArrayList<String>album;

    public addStolenPresenter(add_stolen_car.interfaces.View views, Context context, String brand_id, String model_id, String year_model, String phone, String id_city, String plate_type, String serial, String plate_number, String color, String owner_address, String notes, String main_image, String license, ArrayList<String> album, String user_id) {
        this.views = views;
        this.context = context;
        this.brand_id = brand_id;
        this.model_id = model_id;
        this.year_model = year_model;
        this.phone = phone;
        this.id_city = id_city;
        this.plate_type = plate_type;
        this.serial = serial;
        this.plate_number = plate_number;
        this.color = color;
        this.owner_address = owner_address;
        this.notes = notes;
        this.main_image = main_image;
        this.license = license;
        this.album = album;
        this.user_id = user_id;
        initPresenter();
    }

    private void initPresenter() {
        model = new addStolenData();
    }

    @Override
    public void getData() {
     model.getdata(brand_id,model_id,year_model,phone,id_city,plate_type,serial,plate_number,color,owner_address,notes,main_image,license,album,user_id,context);
    }
}
