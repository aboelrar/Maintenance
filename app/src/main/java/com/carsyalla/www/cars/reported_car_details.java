package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.interfaces.reportedCar_details;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.reportedCarDetailsPresenter;

import me.relex.circleindicator.CircleIndicator;

public class reported_car_details extends AppCompatActivity implements reportedCar_details.interfaces.View {
    TextView brand, notes, plate_number, brand1, model1, address;
    Intent intent;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    com.carsyalla.www.cars.presenter.reportedCarDetailsPresenter reportedCarDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.reported_car_details);
        brand = (TextView) findViewById(R.id.brand);
        notes = (TextView) findViewById(R.id.notes);
        plate_number = (TextView) findViewById(R.id.plate_number);
        brand1 = (TextView) findViewById(R.id.brand1);
        model1 = (TextView) findViewById(R.id.model1);
        address=(TextView)findViewById(R.id.address);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        //view all data
        reportedCarDetailsPresenter = new reportedCarDetailsPresenter(reported_car_details.this, reported_car_details.this, brand, plate_number, notes, brand1, model1, address, getReportedId(), viewPager, circleIndicator);
        reportedCarDetailsPresenter.getData();
    }
    else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    private String getReportedId() {
        Intent intent = getIntent();
        String stolen_cat_id = intent.getStringExtra("reported_cat_id");
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