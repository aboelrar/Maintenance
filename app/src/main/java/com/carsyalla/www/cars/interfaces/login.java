package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.widget.EditText;

public interface login {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            String getdata(EditText mail, EditText password, Context context,String res);
        }

        interface presenter{
            void getData();
        }

    }
}
