package com.carsyalla.www.cars.interfaces;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

public interface reservation {

    public interface interfaces {
        interface  View
        {
        }

        interface Model{
            String getdata(EditText name, EditText phone, String id_brand, String id_model, Context context, EditText model_year, TextView reservation_date, String id_user, String id_center, Dialog dialog);
        }

        interface presenter{
            void getData();
        }
    }
}
