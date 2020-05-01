package com.carsyalla.www.cars.interfaces;

import android.content.Context;

public interface cancel_reservation {
    public interface interfaces {
        interface  View
        {

        }
        interface Model{
            String getdata(String cancel_num, String reservation_id, Context context);
        }

        interface presenter{
            void getData();
        }

    }
}
