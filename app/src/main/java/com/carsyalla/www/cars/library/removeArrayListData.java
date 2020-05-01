package com.carsyalla.www.cars.library;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class removeArrayListData {
    ArrayList<String>arrayList=new ArrayList<>();
   savedId savedId=new savedId();

    public void removeArrayBrands(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("brands", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set = new HashSet<String>();
        Set<String> set_name = new HashSet<String>();
        Set<String> set_num = new HashSet<String>();
        arrayList.clear();
        set.addAll(arrayList);
        set_name.addAll(arrayList);
        set_num.addAll(arrayList);

        editor.putStringSet("key", set);
        editor.putStringSet("title",set_name);
        editor.putStringSet("num",set_num);
        editor.commit();
    }


    public void removeArrayListServices(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("services_i", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set = new HashSet<String>();
        Set<String> set_name = new HashSet<String>();
        Set<String> set_num = new HashSet<String>();
        arrayList.clear();

        set.addAll(arrayList);
        set_name.addAll(arrayList);
        set_num.addAll(arrayList);

        editor.putStringSet("key", set);
        editor.putStringSet("title",set_name);
        editor.putStringSet("num",set_num);
        editor.commit();
    }
}
