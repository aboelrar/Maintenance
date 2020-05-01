package com.carsyalla.www.edit_profile;

import android.content.Context;
import android.widget.EditText;

public interface my_profile_data_and_edit_i {
    interface  View
    {
    }

    interface Model{
        String getdata(EditText mail, EditText fullName, EditText  phoneNum, Context context);
        String getdataedit(EditText mail, EditText fullName, EditText  phoneNum, Context context);

    }

    interface presenter{
        void getData();
        void secondData();
    }
}
