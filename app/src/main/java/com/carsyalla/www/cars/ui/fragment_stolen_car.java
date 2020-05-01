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
import com.carsyalla.www.cars.interfaces.stolen_Car;
import com.carsyalla.www.cars.presenter.stolenCarPresenter;

import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_stolen_car extends Fragment implements stolen_Car.interfaces.View {
    View view;
    com.carsyalla.www.cars.presenter.stolenCarPresenter stolenCarPresenter;
    RecyclerView stolencarList;
    GifImageView gifImageView;
    ProgressBar loading;
    public fragment_stolen_car() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_stolen_car, container, false);
        stolencarList=(RecyclerView)view.findViewById(R.id.stolencarList);
        gifImageView=(GifImageView)view.findViewById(R.id.nodatagif);
        loading=(ProgressBar)view.findViewById(R.id.loading);
        TextView nodata=(TextView)view.findViewById(R.id.nodata);
        gifImageView=(GifImageView)view.findViewById(R.id.nodatagif);
        gifImageView.setImageResource(0);
        stolenCarPresenter=new stolenCarPresenter(fragment_stolen_car.this,getActivity(),stolencarList,1,loading,gifImageView,nodata);
        stolenCarPresenter.getData();

        return view;
    }

}
