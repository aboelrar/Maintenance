package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.interfaces.model_reservation;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.model_reservationPresenter;

public class reservation_models extends AppCompatActivity implements model_reservation.interfaces.View {
    com.carsyalla.www.cars.presenter.model_reservationPresenter model_reservationPresenter;
    Intent intent;
    String brand_id;
    RecyclerView reservationModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
            setContentView(R.layout.reservation_models);
            intent = getIntent();
            brand_id = intent.getStringExtra("brand_id");
            reservationModels = (RecyclerView) findViewById(R.id.reservationModels);
            model_reservationPresenter = new model_reservationPresenter(reservation_models.this, reservation_models.this, reservationModels, brand_id);
            model_reservationPresenter.getData();
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
