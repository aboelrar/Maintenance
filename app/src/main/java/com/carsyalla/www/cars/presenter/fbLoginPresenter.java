package com.carsyalla.www.cars.presenter;

import android.content.Context;

import com.carsyalla.www.cars.Data.facebook_loginData;
import com.carsyalla.www.cars.interfaces.facebook_login;

public class fbLoginPresenter implements facebook_login.interfaces.presenter {
    private facebook_login.interfaces.Model model;
    private facebook_login.interfaces.View views;
    Context context;
    String username,id;

    public fbLoginPresenter(facebook_login.interfaces.View view, Context context, String username, String id) {
        this.views = view;
        this.context=context;
        this.username=username;
        this.id=id;
        initPresenter();
    }
    private void initPresenter() {
        model = new facebook_loginData();

    }
    @Override
    public void getData() {
        String data=model.getdata(username,id,context);
    }
}

