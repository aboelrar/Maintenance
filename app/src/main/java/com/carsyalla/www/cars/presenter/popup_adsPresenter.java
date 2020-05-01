package com.carsyalla.www.cars.presenter;

import android.app.Dialog;
import android.content.Context;
import android.widget.LinearLayout;

import com.carsyalla.www.cars.Data.popup_adsData;
import com.carsyalla.www.cars.interfaces.popup_ads;

public class popup_adsPresenter implements com.carsyalla.www.cars.interfaces.popup_ads.interfaces.presenter {
    private popup_ads.interfaces.Model model;
    private popup_ads.interfaces.View views;
    Context context;
    LinearLayout popup_ads;
    Dialog dialog;

    public popup_adsPresenter(popup_ads.interfaces.View views, Context context, LinearLayout popup_ads,Dialog dialog) {
        this.views = views;
        this.context = context;
        this.popup_ads = popup_ads;
        this.dialog=dialog;
        initPresenter();
    }

    private void initPresenter() {
        model = new popup_adsData();
    }

    @Override
    public void getData() {
        model.getdata(popup_ads,context,dialog);
    }
}
