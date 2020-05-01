package com.carsyalla.www.forget_forget;

import android.content.Context;

public interface forget_password_i {
    public interface interfaces {
        interface  View
        {
        }
        interface Model{
            String getdata(String user_id, Context context,String phone);
        }

        interface presenter{
            void getData();
        }
    }
}
