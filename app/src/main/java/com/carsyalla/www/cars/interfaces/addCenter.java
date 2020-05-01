package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.widget.EditText;

import java.util.ArrayList;

public interface addCenter  {
    public interface interfaces {
        interface  View
        {
        }

        interface Model{
            String getdata(EditText name, EditText descripition, EditText  email, EditText phone, String gouvernateId, String cityId, EditText address, String lat, String lng, EditText workTimeFrom, EditText workTimeTo, EditText workName, Context context, String mainImage, ArrayList<String> brands, ArrayList<String> service, ArrayList<String> Album, EditText discount,EditText holidays );
        }

        interface presenter{
            void getData();
        }
    }}
