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

import com.carsyalla.www.cars.interfaces.plateNumSearch;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.plateNumResultsPresenter;

import pl.droidsonroids.gif.GifImageView;

public class search_result_plate_number extends AppCompatActivity implements plateNumSearch.interfaces.View {
    com.carsyalla.www.cars.presenter.plateNumResultsPresenter plateNumResultsPresenter;
   RecyclerView search_result_plateNumber;
   ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
            setContentView(R.layout.search_result_plate_number);
            search_result_plateNumber = (RecyclerView) findViewById(R.id.search_result_plateNumber);
            Intent intent = getIntent();
            String plate_type = intent.getStringExtra("plate_type");
            String plateNum = intent.getStringExtra("plateNum");
            loading = (ProgressBar) findViewById(R.id.loading);
            TextView nodata=(TextView)findViewById(R.id.nodata);
            GifImageView gifImageView=(GifImageView)findViewById(R.id.nodatagif);
            gifImageView.setImageResource(0);
            plateNumResultsPresenter = new plateNumResultsPresenter(search_result_plate_number.this, search_result_plate_number.this, search_result_plateNumber, plate_type, plateNum, loading,gifImageView,nodata);
            plateNumResultsPresenter.getData();
        } else {
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
