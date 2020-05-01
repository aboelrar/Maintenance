package com.carsyalla.www.cars.interfaces;

import android.content.Context;

import java.util.ArrayList;

public interface favourite {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(final String Url, Context context, String id);
        }

        interface presenter{
            void getData();
            }
    }
}
