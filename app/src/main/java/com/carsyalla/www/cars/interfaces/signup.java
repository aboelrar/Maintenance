package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.widget.EditText;

public interface signup {

    public interface interfaces {
        interface  View
        {
        }

        interface Model{
            String getdata(EditText mail, EditText password, EditText fullName, EditText  phoneNum, Context context,String gouvernate_id,String city_id);
        }

        interface presenter{
            void getData();
        }
    }
}
