package com.carsyalla.www.cars.interfaces;

import android.content.Context;

import java.util.ArrayList;

public interface add_reportedCar {
    public interface interfaces {
        interface  View
        {
        }

        interface Model{
            String getdata(String id_brand, String id_model,String phone, String id_city, String plate_type, String plate_number, String latitude,String longitude, String owner_address, String notes, String main_image,ArrayList<String> album, String user_id, Context context);
        }

        interface presenter{
            void getData();
        }
    }}
