package com.carsyalla.www.cars.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.presenter.commentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class comments extends Fragment implements com.carsyalla.www.cars.interfaces.comments.interfaces.View {
    View view;
    com.carsyalla.www.cars.presenter.commentPresenter commentPresenter;
    RecyclerView comment_list;
    TextView nodata;

    public comments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_comments, container, false);
        comment_list=(RecyclerView)view.findViewById(R.id.commentList);
        nodata=(TextView)view.findViewById(R.id.nodata);
        commentPresenter=new commentPresenter(comments.this,getActivity(),getid(),comment_list,nodata);
        commentPresenter.getData();
        return view;
    }
    public String getid()
    {
        String id = getArguments().getString("center_id");
        return id;
    }
}
