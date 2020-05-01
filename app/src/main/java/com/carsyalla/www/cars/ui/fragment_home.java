package com.carsyalla.www.cars.ui;


import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.interfaces.brands;
import com.carsyalla.www.cars.presenter.brandPresenter;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_home extends Fragment implements brands.interfaces.View {
com.carsyalla.www.cars.dialog.searchDialog searchDialog;
    ImageView search;
    RecyclerView maintancelist;
    ProgressBar loading;
    com.carsyalla.www.cars.presenter.brandPresenter brandPresenter;
    View view;

    public fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        CircleIndicator circleIndicator = (CircleIndicator)view.findViewById(R.id.indicator);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        maintancelist=(RecyclerView)view.findViewById(R.id.maintancelist);
        maintancelist.setNestedScrollingEnabled(false);
        loading=(ProgressBar)view.findViewById(R.id.loading);
        brandPresenter=new brandPresenter(fragment_home.this,getActivity(),maintancelist,viewPager,circleIndicator,loading);
        brandPresenter.getData();
       brandPresenter.getSliderData();
        return view;
    }

}
