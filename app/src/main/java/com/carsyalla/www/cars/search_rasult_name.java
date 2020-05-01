package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.interfaces.center_search_name;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.center_search_name_presenter;

import pl.droidsonroids.gif.GifImageView;

public class search_rasult_name extends AppCompatActivity implements center_search_name.interfaces.View, favourite.interfaces.View {
    com.carsyalla.www.cars.presenter.center_search_name_presenter center_search_name_presenter;
    RecyclerView search_result_list;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
            setContentView(R.layout.search_rasult_name);
            getData();
        }else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    private void getData()
    {
        //get name
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        search_result_list=(RecyclerView)findViewById(R.id.search_result_list);
        TextView nodata=(TextView)findViewById(R.id.nodata);
        loading=(ProgressBar)findViewById(R.id.loading);
        GifImageView  gifImageView=(GifImageView)findViewById(R.id.nodatagif);
        gifImageView.setImageResource(0);
        //GET DATA
        center_search_name_presenter=new center_search_name_presenter(this,this,this,search_result_list,name,new savedId().getId(search_rasult_name.this),1,loading,gifImageView,nodata);
        center_search_name_presenter.getData();
    }

    //refresh page
    public void onclickonconnection()
    {
        Button noConnection=(Button)findViewById(R.id.tryagain);
        noConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=getIntent();
                startActivity(intent);
            }
        });
    }
}
