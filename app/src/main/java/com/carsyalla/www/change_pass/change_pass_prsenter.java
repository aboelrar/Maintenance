package com.carsyalla.www.change_pass;

import android.content.Context;
import android.widget.EditText;

public class change_pass_prsenter implements change_pass_i.interfaces.presenter {
    private change_pass_i.interfaces.Model model;
    private change_pass_i.interfaces.View views;
    Context context;
    EditText oldpass,newpass,renewpass;
    String user_id;

    public change_pass_prsenter(change_pass_i.interfaces.View views, Context context, EditText newpass, EditText oldpass, EditText renewpass, String user_id) {
        this.views = views;
        this.context = context;
        this.newpass = newpass;
        this.user_id = user_id;
        this.oldpass=oldpass;
        this.renewpass=renewpass;
        initPresenter();
    }

    private void initPresenter() {
        model = new change_pass_data();
    }

    @Override
    public void getData() {
 model.getdata(oldpass,newpass,renewpass,context);
    }

}
