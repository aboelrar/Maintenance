package com.carsyalla.www.cars.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.search_result_chassia;

/**
 * A simple {@link Fragment} subclass.
 */
public class searchStolenCars extends Fragment {
    View view;
  EditText chassie;
  Button search;

    public searchStolenCars() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_stolen_cars, container, false);
        chassie=(EditText)view.findViewById(R.id.chassia);
        search=(Button)view.findViewById(R.id.search);
        //GO TO SEARCH RESULT BY CHASSIE
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),search_result_chassia.class);
                intent.putExtra("chassieNum",chassie.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }

}
