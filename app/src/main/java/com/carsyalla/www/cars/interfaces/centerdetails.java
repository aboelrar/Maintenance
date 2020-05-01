package com.carsyalla.www.cars.interfaces;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public interface centerdetails {
    public interface interfaces {
        interface  View
        {

        }

        interface Model{
            ArrayList getdata(Context context,String center_Id, TextView name, RatingBar ratingBar,TextView descripition,ViewPager viewPager, final CircleIndicator circleIndicator,ImageView favourite,favourite.interfaces.View view);
            ArrayList getdataSnd(Context context, TextView gouvernate, TextView city, TextView timeWork, RecyclerView brandList, String center_id, RecyclerView serviceList, ImageView fav,final TextView holidays, final TextView disccount, final LinearLayout discount_item);

        }

        interface presenter{
            void getData();
            void getScdData();
        }
    }
}
