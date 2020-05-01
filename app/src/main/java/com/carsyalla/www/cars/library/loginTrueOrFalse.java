package com.carsyalla.www.cars.library;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class loginTrueOrFalse {
    public void userId(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("idss",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("islogin",false);
        editor.putString("id", "0");
        editor.commit();
    }

    //get user id shared prefrences
    public void userId(Context context,String id)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("idss",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("islogin",true);
        editor.putString("id", id);
        editor.commit();
    }
}
