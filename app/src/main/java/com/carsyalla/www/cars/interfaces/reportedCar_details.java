package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;

public interface reportedCar_details {
    public interface interfaces {
        interface  View
        {
        }

        interface Model{
            String getdata(Context context, TextView brand, TextView plate_num, TextView notes, TextView brand1, TextView model1, TextView address, String reported_car_id, ViewPager viewPager, final CircleIndicator circleIndicator);
        }

        interface presenter{
            void getData();
        }
    }
}
