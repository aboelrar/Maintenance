package com.carsyalla.www.cars.interfaces;

import android.app.Dialog;
import android.content.Context;
import android.widget.LinearLayout;

public interface popup_ads {
    public interface interfaces {
        interface  View
        {
        }

        interface Model{
            String getdata(LinearLayout imageView, Context context, Dialog dialog);
        }

        interface presenter{
            void getData();
        }
    }
}
