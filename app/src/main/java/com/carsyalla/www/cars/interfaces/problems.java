package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.widget.EditText;

public interface problems {

    public interface interfaces {
        interface View {
        }

        interface Model {
            String getdata(EditText name, EditText mail, EditText message, Context context);
        }

        interface presenter {
            void getData();
        }
    }
}
