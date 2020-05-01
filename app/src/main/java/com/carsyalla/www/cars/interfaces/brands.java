package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public interface brands {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(RecyclerView recyclerView, Context context, ProgressBar loading);
            ArrayList getSlider(ViewPager viewPager, Context context,final CircleIndicator circleIndicator);
        }

        interface presenter{
            void getData();
            void getSliderData();
        }
    }
}
