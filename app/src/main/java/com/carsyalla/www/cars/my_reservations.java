package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.interfaces.addRate;
import com.carsyalla.www.cars.interfaces.cancel_reservation;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.interfaces.myReservation;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.my_reservationPresenter;

import pl.droidsonroids.gif.GifImageView;

public class my_reservations extends AppCompatActivity implements myReservation.interfaces.View, favourite.interfaces.View, addRate.interfaces.View, cancel_reservation.interfaces.View {

    my_reservationPresenter my_reservationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.my_reservations);
        if(new savedId().getUserBoolean(my_reservations.this)==false)
        {
            Intent intent=new Intent(my_reservations.this,LoginAndRegist.class);
            intent.putExtra("res","myres");
            startActivity(intent);
            Toast.makeText(my_reservations.this, "يرجى تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
        }
        else {
        RecyclerView reservation_List=(RecyclerView)findViewById(R.id.myreservationList);
            TextView  nodata=(TextView)findViewById(R.id.nodata);
            GifImageView gifImageView=(GifImageView)findViewById(R.id.nodatagif);
            gifImageView.setImageResource(0);
            ProgressBar loading=(ProgressBar)findViewById(R.id.loading);
            my_reservationPresenter=new my_reservationPresenter(this,this,this,reservation_List,this,this,nodata,gifImageView,loading);
        my_reservationPresenter.getData();
    }}
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(my_reservations.this,home.class));
    }

}
