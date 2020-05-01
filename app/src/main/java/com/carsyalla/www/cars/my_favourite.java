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
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.myFavouritePresenter;

import pl.droidsonroids.gif.GifImageView;

public class my_favourite extends AppCompatActivity implements com.carsyalla.www.cars.interfaces.myFavourite.interfaces.View, favourite.interfaces.View {
    RecyclerView myFavourite;
    com.carsyalla.www.cars.presenter.myFavouritePresenter myFavouritePresenter;
    TextView nodata;
    GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.activity_my_favourite);
            if(new savedId().getUserBoolean(my_favourite.this)==false)
            {
                Intent intent=new Intent(my_favourite.this,LoginAndRegist.class);
                intent.putExtra("res","myfav");
                startActivity(intent);
                Toast.makeText(my_favourite.this, "يرجى تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
            }
            else {
        myFavourite=(RecyclerView)findViewById(R.id.myFavourite);
        ProgressBar loading=(ProgressBar)findViewById(R.id.loading);
            nodata=(TextView)findViewById(R.id.nodata);
            gifImageView=(GifImageView)findViewById(R.id.nodatagif);
            gifImageView.setImageResource(0);
        myFavouritePresenter=new myFavouritePresenter(this,this,myFavourite,null,this,loading,nodata,gifImageView);
        myFavouritePresenter.getData();
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
        startActivity(new Intent(my_favourite.this,home.class));
    }
}
