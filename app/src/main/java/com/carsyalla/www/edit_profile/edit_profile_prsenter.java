package com.carsyalla.www.edit_profile;

import android.content.Context;
import android.widget.EditText;

public class edit_profile_prsenter implements my_profile_data_and_edit_i.presenter {
    private my_profile_data_and_edit_i.Model model;
    private my_profile_data_and_edit_i.View views;
    Context context;
    EditText name,phone,mail;
    String user_id;

    public edit_profile_prsenter(my_profile_data_and_edit_i.View views, Context context, EditText name,EditText phone,EditText mail, String user_id) {
        this.views = views;
        this.context = context;
        this.name = name;
        this.user_id = user_id;
        this.phone=phone;
        this.mail=mail;
        initPresenter();
    }

    private void initPresenter() {
        model = new my_profile_data_and_edit_data();
    }

    @Override
    public void getData() {
 model.getdata(mail,name,phone,context);
    }

    @Override
    public void secondData() {
        model.getdataedit(mail,name,phone,context);
    }
}
