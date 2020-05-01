package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.interfaces.brand_reservation;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.brand_reservationPresenter;

public class reservation_brands extends AppCompatActivity implements brand_reservation.interfaces.View {
    com.carsyalla.www.cars.presenter.brand_reservationPresenter brand_reservationPresenter;
    RecyclerView reservationBrands;
    String model_id,model_name,brand_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.reservation_brands);
        reservationBrands=(RecyclerView)findViewById(R.id.reservationBrands);
        brand_reservationPresenter=new brand_reservationPresenter(reservation_brands.this,reservation_brands.this,reservationBrands);
        brand_reservationPresenter.getData();
    }
    else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //back from gouvertnate with cityid and gouvernate id
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                model_id = data.getStringExtra("model_id");
                model_name=data.getStringExtra("model_name");
                brand_id=data.getStringExtra("brand_id");

                Intent intent = new Intent();
                intent.putExtra("model_id",model_id);
                intent.putExtra("brand_id",brand_id);
                intent.putExtra("model_name",model_name);
                setResult(RESULT_OK, intent);
                finish();
            }
        }}

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
