package com.carsyalla.www.cars.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.interfaces.reported_Car;
import com.carsyalla.www.cars.presenter.reportedCarPresenter;

import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class reported_car extends Fragment implements reported_Car.interfaces.View {

    View view;
   com.carsyalla.www.cars.presenter.reportedCarPresenter reportedCarPresenter;
    RecyclerView stolencarList;
    ProgressBar loading;
    public reported_car() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.reported_car, container, false);
        stolencarList=(RecyclerView)view.findViewById(R.id.stolencarList);
        loading=(ProgressBar)view.findViewById(R.id.loading);
        TextView nodata=(TextView)view.findViewById(R.id.nodata);
        GifImageView gifImageView=(GifImageView)view.findViewById(R.id.nodatagif);
        gifImageView.setImageResource(0);
        reportedCarPresenter=new reportedCarPresenter(reported_car.this,getActivity(),stolencarList,1,loading,nodata,gifImageView);
        reportedCarPresenter.getData();

        return view;
    }

}
