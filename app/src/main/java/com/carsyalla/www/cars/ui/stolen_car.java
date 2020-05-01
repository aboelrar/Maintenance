package com.carsyalla.www.cars.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carsyalla.www.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class stolen_car extends Fragment {


    public stolen_car() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.stolen_car, container, false);
    }

}
