package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public interface reservation_details {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(Context context, TextView center_title, TextView date, TextView phone, TextView model, TextView gouvernate, TextView city, TextView address, LinearLayout viewmap,String id_reservation);

        }

        interface presenter{
            void getData();
        }
    }
}
