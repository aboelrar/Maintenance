package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.widget.TextView;

import com.carsyalla.www.cars.Data.usernameData;
import com.carsyalla.www.cars.interfaces.navigation_userName;

public class userPresenter implements navigation_userName.presenter {
    private navigation_userName.Model model;
    private navigation_userName.View views;
    Context context;
    TextView name;
    String user_id;

    public userPresenter(navigation_userName.View views, Context context, TextView name, String user_id) {
        this.views = views;
        this.context = context;
        this.name = name;
        this.user_id = user_id;
        initPresenter();
    }

    private void initPresenter() {
        model = new usernameData();
    }

    @Override
    public void getData() {
 model.getdata(context,user_id,name);
    }
}
