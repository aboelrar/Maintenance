package com.carsyalla.www.cars.interfaces;

import android.content.Context;

public interface addRate  {
    public interface interfaces {
        interface  View
        {
        }
        interface Model{
            String getdata(String user_id,String center_id,Context context,int rate);
        }

        interface presenter{
            void getData();
        }
    }
}
