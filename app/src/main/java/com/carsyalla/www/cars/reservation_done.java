package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.interfaces.reservation_details;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.reservationDetailsPresenter;

public class reservation_done extends AppCompatActivity implements reservation_details.interfaces.View {
    com.carsyalla.www.cars.presenter.reservationDetailsPresenter reservationDetailsPresenter;
    TextView reservation_title,phone,car_model,gouvernate,city,address,date;
    LinearLayout viewMap;
    Intent intent;
    Button myreservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
            setContentView(R.layout.reservation_done);
            reservation_title = (TextView) findViewById(R.id.centerName);
            phone = (TextView) findViewById(R.id.phone);
            date = (TextView) findViewById(R.id.date);
            car_model = (TextView) findViewById(R.id.model);
            gouvernate = (TextView) findViewById(R.id.gouvernate);
            city = (TextView) findViewById(R.id.city);
            address = (TextView) findViewById(R.id.address);
            viewMap = (LinearLayout) findViewById(R.id.viewmap);
            intent = getIntent();
            String id_reservation = intent.getStringExtra("id_reservation");
            reservationDetailsPresenter = new reservationDetailsPresenter(reservation_done.this, reservation_done.this, reservation_title, date, phone, car_model, gouvernate, city, address, viewMap, id_reservation);
            reservationDetailsPresenter.getData();
            onClick();
        }
        else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }
    //GO TO MYRESERVATION
    private void onClick()
    {
        myreservation=(Button)findViewById(R.id.myreservation);
        myreservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(reservation_done.this,my_reservations.class);
                startActivity(intent);
            }
        });
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
