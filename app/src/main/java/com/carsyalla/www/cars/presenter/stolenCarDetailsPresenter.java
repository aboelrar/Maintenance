package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.stolen_car_detailsData;
import com.carsyalla.www.cars.interfaces.stolenCar_details;

import me.relex.circleindicator.CircleIndicator;

public class stolenCarDetailsPresenter implements stolenCar_details.interfaces.presenter {
    private stolenCar_details.interfaces.Model model;
    private stolenCar_details.interfaces.View views;
    Context context;
    TextView brand,plate_num,notes,brand1,model1,modelyear,serial,address;
    String stolen_car_id;
    ViewPager viewPager;
    CircleIndicator circleIndicator;

    public stolenCarDetailsPresenter(stolenCar_details.interfaces.View views, Context context, TextView brand, TextView plate_num, TextView notes, TextView brand1, TextView model1, TextView modelyear, TextView serial, TextView address, String stolen_car_id, ViewPager viewPager, CircleIndicator circleIndicator) {
        this.views = views;
        this.context = context;
        this.brand = brand;
        this.plate_num = plate_num;
        this.notes = notes;
        this.brand1 = brand1;
        this.model1 = model1;
        this.modelyear = modelyear;
        this.serial = serial;
        this.address = address;
        this.stolen_car_id = stolen_car_id;
        this.viewPager = viewPager;
        this.circleIndicator = circleIndicator;
        initPresenter();
    }
    private void initPresenter() {
        model = new stolen_car_detailsData();
    }
    @Override
    public void getData() {
        model.getdata(context,brand,plate_num,notes,brand1,model1,modelyear,serial,address,stolen_car_id,viewPager,circleIndicator);

    }
}
