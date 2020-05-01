package com.carsyalla.www.forget_forget;

import android.content.Context;

public class forget_password_presenter implements  forget_password_i.interfaces.presenter{
    private forget_password_i.interfaces.Model model;
    private forget_password_i.interfaces.View views;
    Context context;
    String user_id,mobile;


    public forget_password_presenter(forget_password_i.interfaces.View views, Context context, String user_id,String mobile) {
        this.views = views;
        this.context = context;
        this.user_id = user_id;
        this.mobile = mobile;
        initPresenter();
    }
    private void initPresenter() {
        model = new forget_passwrd_data();
    }
    @Override
    public void getData() {
        model.getdata(user_id,context,mobile);
    }
}
