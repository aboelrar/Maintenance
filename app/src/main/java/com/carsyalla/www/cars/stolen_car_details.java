package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.interfaces.stolenCar_details;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.stolenCarDetailsPresenter;

import me.relex.circleindicator.CircleIndicator;

public class stolen_car_details extends AppCompatActivity implements stolenCar_details.interfaces.View {
    TextView brand,notes,plate_number,brand1,model1,modelyear,serial,address;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    com.carsyalla.www.cars.presenter.stolenCarDetailsPresenter stolenCarDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.stolen_car_details);
        brand=(TextView)findViewById(R.id.brand);
        notes=(TextView)findViewById(R.id.notes);
        plate_number=(TextView)findViewById(R.id.plate_number);
        brand1=(TextView)findViewById(R.id.brand1);
        model1=(TextView)findViewById(R.id.model1);
        modelyear=(TextView)findViewById(R.id.modelyear);
        serial=(TextView)findViewById(R.id.serial);
        address=(TextView)findViewById(R.id.address);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        circleIndicator=(CircleIndicator)findViewById(R.id.indicator);
        //view all data
        stolenCarDetailsPresenter=new stolenCarDetailsPresenter(stolen_car_details.this,stolen_car_details.this,brand,plate_number,notes,brand1,model1,modelyear,serial,address,getStolenId(),viewPager,circleIndicator);
        stolenCarDetailsPresenter.getData();
    }
    else
        {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    private String getStolenId()
    {
        Intent intent=getIntent();
        String stolen_cat_id=intent.getStringExtra("stolen_cat_id");
        return stolen_cat_id;
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
