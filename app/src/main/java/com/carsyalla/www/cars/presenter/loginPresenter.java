package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.widget.EditText;

import com.carsyalla.www.cars.Data.loginData;
import com.carsyalla.www.cars.interfaces.login;

public class loginPresenter implements login.interfaces.presenter {
    private login.interfaces.Model model;
    private login.interfaces.View views;
    Context context;
    EditText password,mail;
    String res;

    public loginPresenter(login.interfaces.View view, Context context,EditText mail,EditText password,String res) {
        this.views = view;
        this.context=context;
        this.mail=mail;
        this.password=password;
        this.res=res;
        initPresenter();
    }
    private void initPresenter() {
        model = new loginData();

    }
    @Override
    public void getData() {
        String data=model.getdata(mail,password,context,res);
    }
}

