package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.interfaces.city;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.cityPresenter;

public class allCity extends AppCompatActivity implements city.interfaces.View {
    com.carsyalla.www.cars.presenter.cityPresenter cityPresenter;
    RecyclerView cityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.activity_all_city);
        cityList=(RecyclerView)findViewById(R.id.cityList);
        Intent intent=getIntent();
        String gouvernate_id=intent.getStringExtra("gouvernate_id");
        String gouvernate_name=intent.getStringExtra("gouvernate_name");
        cityPresenter=new cityPresenter(allCity.this,allCity.this,cityList,gouvernate_id,gouvernate_name);
        cityPresenter.getData();
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
