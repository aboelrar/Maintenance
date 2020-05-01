package com.carsyalla.www.cars.presenter;

import android.content.Context;

import com.carsyalla.www.cars.Data.addReportedCar;
import com.carsyalla.www.cars.interfaces.add_reportedCar;

import java.util.ArrayList;

public class addReportedCarPresenter implements add_reportedCar.interfaces.presenter {
    private add_reportedCar.interfaces.Model model;
    private add_reportedCar.interfaces.View views;
    Context context;
    String brand_id,model_id,phone,id_city,plate_type,plate_number,owner_address,notes,main_image,user_id,latitude,longtiude;
    ArrayList<String>album;

    public addReportedCarPresenter(add_reportedCar.interfaces.View views, Context context, String brand_id, String model_id, String phone, String id_city, String plate_type, String plate_number, String owner_address, String notes, String main_image, ArrayList<String> album, String user_id, String longtiude, String latitude) {
        this.views = views;
        this.context = context;
        this.brand_id = brand_id;
        this.model_id = model_id;
        this.phone = phone;
        this.id_city = id_city;
        this.plate_type = plate_type;
        this.plate_number = plate_number;
        this.owner_address = owner_address;
        this.notes = notes;
        this.main_image = main_image;
        this.album = album;
        this.user_id = user_id;
        this.latitude=latitude;
        this.longtiude=longtiude;
        initPresenter();
    }

    private void initPresenter() {
        model = new addReportedCar();
    }

    @Override
    public void getData() {
     model.getdata(brand_id,model_id,phone,id_city,plate_type,plate_number,latitude,longtiude,owner_address,notes,main_image,album,user_id,context);
    }
}
