package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public interface navigation_userName {
    interface  View
    {

    }

    interface Model{
        ArrayList getdata(Context context, String user_id, TextView name);

    }

    interface presenter{
        void getData();
    }

}
