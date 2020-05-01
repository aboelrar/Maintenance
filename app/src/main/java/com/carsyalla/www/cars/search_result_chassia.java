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

import com.carsyalla.www.cars.interfaces.chassisNumberSearch;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.chassieNumberResultsPresenter;

import pl.droidsonroids.gif.GifImageView;

public class search_result_chassia extends AppCompatActivity implements chassisNumberSearch.interfaces.View {
  RecyclerView search_result_chassie;
    com.carsyalla.www.cars.presenter.chassieNumberResultsPresenter chassieNumberResultsPresenter;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.search_result_chassia);
        loading=(ProgressBar)findViewById(R.id.loading);
            TextView  nodata=(TextView)findViewById(R.id.nodata);
            GifImageView gifImageView=(GifImageView)findViewById(R.id.nodatagif);
            gifImageView.setImageResource(0);
        search_result_chassie=(RecyclerView)findViewById(R.id.search_result_chassie);
        chassieNumberResultsPresenter=new chassieNumberResultsPresenter(this,this,search_result_chassie,getChassieId(),loading,nodata,gifImageView);
        chassieNumberResultsPresenter.getData();
    }
    else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    private String getChassieId()
    {
        Intent intent=getIntent();
        String id=intent.getStringExtra("chassieNum");
        return id;
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
