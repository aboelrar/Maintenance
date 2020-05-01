package com.carsyalla.www.change_pass;

import android.content.Context;
import android.widget.EditText;

public interface change_pass_i {

    public interface interfaces {
        interface  View
        {
        }

        interface Model{
            String getdata(EditText oldpass, EditText newpass, EditText renewpass, Context context);
        }

        interface presenter{
            void getData();
        }
    }
}
