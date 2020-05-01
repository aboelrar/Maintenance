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

import com.carsyalla.www.cars.interfaces.center_search;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.center_search_presenter;

import pl.droidsonroids.gif.GifImageView;

public class search_result extends AppCompatActivity implements center_search.interfaces.View, favourite.interfaces.View {
    Intent intent;
    RecyclerView search_result_list;
    com.carsyalla.www.cars.presenter.center_search_presenter center_search_presenter;
    com.carsyalla.www.cars.library.savedId savedId;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.search_result);
        search_result_list=(RecyclerView)findViewById(R.id.search_result_list);
        intent=getIntent();
        String brand_id=intent.getStringExtra("brand_id");
        String service_id=intent.getStringExtra("service_id");
        String city_id=intent.getStringExtra("city_id");
        savedId=new savedId();
           TextView nodata=(TextView)findViewById(R.id.nodata);
            loading=(ProgressBar)findViewById(R.id.loading);
            GifImageView  gifImageView=(GifImageView)findViewById(R.id.nodatagif);
            gifImageView.setImageResource(0);
        center_search_presenter=new center_search_presenter(search_result.this,search_result.this,search_result.this,search_result_list,brand_id,service_id,city_id,savedId.getId(search_result.this),1,loading,gifImageView,nodata);
        center_search_presenter.getData();
    }
    else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
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
