package com.carsyalla.www.cars.presenter;

import android.content.Context;
import android.widget.EditText;

import com.carsyalla.www.cars.Data.problemsData;
import com.carsyalla.www.cars.interfaces.problems;

public class problrmsPresenter implements problems.interfaces.presenter {
    private problems.interfaces.Model model;
    private problems.interfaces.View views;
    Context context;
    EditText name, phone, message;

    public problrmsPresenter(problems.interfaces.View views, Context context, EditText name, EditText phone, EditText message) {
        this.views = views;
        this.context = context;
        this.name = name;
        this.phone = phone;
        this.message = message;
        initPresenter();
    }

    private void initPresenter() {
        model = new problemsData();
    }

    @Override
    public void getData() {
        model.getdata(name, phone, message, context);
    }
}
