package com.carsyalla.www.cars.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.presenter.centerDetailsPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class centerdetails extends Fragment implements com.carsyalla.www.cars.interfaces.centerdetails.interfaces.View {
 TextView gouvernate,city,timeWork,holidays;
 RecyclerView brandList,servicesList;
 com.carsyalla.www.cars.presenter.centerDetailsPresenter centerDetailsPresenter;
 ImageView fav;
 View view;

    public centerdetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_center_details, container, false);
        gouvernate=(TextView)view.findViewById(R.id.gouvernate);
        city=(TextView)view.findViewById(R.id.city);
        timeWork=(TextView)view.findViewById(R.id.worktime);
        holidays=(TextView)view.findViewById(R.id.holidays);
        brandList=(RecyclerView)view.findViewById(R.id.brands);
        servicesList=(RecyclerView)view.findViewById(R.id.services);
        TextView  discount=(TextView)view.findViewById(R.id.discount);
        LinearLayout  discount_item=(LinearLayout)view.findViewById(R.id.discountitem);
        centerDetailsPresenter=new centerDetailsPresenter(this,getActivity(),gouvernate,city,timeWork,brandList,getid(),servicesList,holidays,null,discount,discount_item);
        centerDetailsPresenter.getScdData();
        return view;
    }

    public String getid()
    {
        String id = getArguments().getString("center_id");
        return id;
    }
}
