package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


import com.carsyalla.www.R;
import com.carsyalla.www.cars.interfaces.services;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.servicePresenter;

public class all_service extends AppCompatActivity implements services.interfaces.View {
RecyclerView serviceList;
com.carsyalla.www.cars.presenter.servicePresenter servicePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.all_service);
        serviceList=(RecyclerView)findViewById(R.id.serviceList);
        servicePresenter=new servicePresenter(all_service.this,all_service.this,serviceList);
        servicePresenter.getData();}
        else
        {
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
