package com.carsyalla.www.cars.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.search_result_plate_number;

/**
 * A simple {@link Fragment} subclass.
 */
public class searchReportedCars extends Fragment {
    static EditText plateNum;
    static RadioGroup paltetype;
    static Button search;
    String plate_type="1";
    View view;
    public searchReportedCars() {
        // Required empty public constructord
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_search_reported_cars, container, false);
        paltetype=(RadioGroup)view.findViewById(R.id.paltetype);
        plateNum=(EditText)view.findViewById(R.id.plateNum);
        search=(Button)view.findViewById(R.id.search);
        sendData();
        return view;
    }

    //SEND SEARCH DATA
    private void sendData()
    {
        paltetype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radio1)
                {
                    plate_type="1";
                }else if(checkedId==R.id.radio0)
                {
                    plate_type="2";
                }
            }
        });
           search.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(getActivity(),search_result_plate_number.class);
                   intent.putExtra("plate_type",plate_type);
                   intent.putExtra("plateNum",plateNum.getText().toString());
                   startActivity(intent);
               }
           });

    }

}
