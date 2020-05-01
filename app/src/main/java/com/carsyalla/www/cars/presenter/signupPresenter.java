package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.widget.EditText;

import com.carsyalla.www.cars.Data.signupData;
import com.carsyalla.www.cars.interfaces.signup;

public class signupPresenter implements signup.interfaces.presenter {
    private signup.interfaces.Model model;
    private signup.interfaces.View views;
    Context context;
    EditText password,mail,fullname,phome;
    String gouvernate_id,city_id;

    public signupPresenter(signup.interfaces.View view, Context context,EditText password,EditText mail,EditText fullname,EditText phome,String gouvernate_id,String city_id) {
        this.views = view;
        this.context=context;
        this.mail=mail;
        this.password=password;
        this.fullname=fullname;
        this.phome=phome;
        this.gouvernate_id=gouvernate_id;
        this.city_id=city_id;
        initPresenter();
    }
    private void initPresenter() {
        model = new signupData();
    }
    @Override
    public void getData() {
        String data=model.getdata(mail,password,fullname,phome,context,gouvernate_id,city_id);
    }
}
