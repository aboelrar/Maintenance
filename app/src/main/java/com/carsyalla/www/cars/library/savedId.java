package com.carsyalla.www.cars.library;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class savedId {
    ArrayList<String>mylist=new ArrayList<String>();
    public String getId(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("idss",MODE_PRIVATE);
        String id=sharedPreferences.getString("id","0");
        return id;
    }
    public boolean getUserBoolean(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("idss",MODE_PRIVATE);
        boolean value=sharedPreferences.getBoolean("islogin",false);
        return value;
    }

    public ArrayList retrieveServices(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("services_i",MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("key", null);
        mylist.addAll(set);
        return mylist;
    }

    public ArrayList retrieveBrands(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("brands",MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("key", null);
        mylist.addAll(set);
        return mylist;
    }

    public ArrayList retrieveServicesTitle(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("services_i",MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("title", null);
        mylist.addAll(set);
        return mylist;
    }

    public ArrayList retrieveBrandsTitle(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("brands",MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("title", null);
        mylist.addAll(set);
        return mylist;
    }

    public ArrayList retrieveServiceNum(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("services_i",MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("num", null);
        mylist.addAll(set);
        return mylist;
    }

    public ArrayList retrieveBrandsNum(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("brands",MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("num", null);
        mylist.addAll(set);
        return mylist;
    }

    public String retrieveLoca(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("location",MODE_PRIVATE);
        String id=sharedPreferences.getString("loca","no");
        return id;
    }
}
