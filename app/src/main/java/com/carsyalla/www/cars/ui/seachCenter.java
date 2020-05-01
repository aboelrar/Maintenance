package com.carsyalla.www.cars.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.search_by_name;
import com.carsyalla.www.cars.search_center_details;

/**
 * A simple {@link Fragment} subclass.
 */
public class seachCenter extends Fragment {


    public seachCenter() {
        // Required empty public constructor
    }
View view;
  LinearLayout detailssearch,searchName;

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_seach_center, container, false);
        detailssearch=(LinearLayout)view.findViewById(R.id.detailssearch);
          searchName=(LinearLayout)view.findViewById(R.id.searchName);
        goToSeachDetails();
        return view;
    }

    //ON CLICK BUTTON
    private void goToSeachDetails()
    {
        //DETAILS SEARCH
        detailssearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),search_center_details.class);
                startActivity(intent);
            }
        });
        searchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),search_by_name.class);
                startActivity(intent);
            }
        });
    }

}

