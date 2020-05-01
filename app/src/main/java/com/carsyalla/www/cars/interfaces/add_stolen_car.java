package com.carsyalla.www.cars.interfaces;

import android.content.Context;

import java.util.ArrayList;

public interface add_stolen_car {
    public interface interfaces {
        interface  View
        {
        }

        interface Model{
            String getdata(String id_brand,String id_model,String year_model,String phone,String id_city,String plate_type,String serial,String plate_number,String color,String owner_address,String notes,String main_image,String license,ArrayList<String> album,String user_id,Context context);
        }

        interface presenter{
            void getData();
        }
    }}
