package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.library.checkConnection;

public class search_center_details extends AppCompatActivity {
    static TextView model,city,service;
    static  String brand_id,model_id,model_name,city_id,gouvernate_id,service_name,service_id;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.search_center_details);
        city_id="0";
        model_id="0";
        service_id="0";
        //get car model
        model=(TextView)findViewById(R.id.model);
        model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(search_center_details.this,reservation_brands.class);
                startActivityForResult(intent, 1);
            }
        });
        //get city
        city=(TextView)findViewById(R.id.city);
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(search_center_details.this, gouvernate_city.class);
                startActivityForResult(i, 2);
            }
        });
        //services_i
        service=(TextView)findViewById(R.id.service);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(search_center_details.this, all_service.class);
                startActivityForResult(i, 3);
            }
        });
        //go to search
        search=(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
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
                brand_id = data.getStringExtra("brand_id");
                model_id=data.getStringExtra("model_id");
                model_name=data.getStringExtra("model_name");
                model.setText(model_name);
            }
        }
        else  if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                city_id = data.getStringExtra("city_id");
                gouvernate_id = data.getStringExtra("gouvernate_id");
                String city_name = data.getStringExtra("city_name");
                String gouvernate_name = data.getStringExtra("gouvernate_name");
                city.setText(gouvernate_name+","+city_name);
            }
        }
        else if(requestCode == 3)
        {
            if(resultCode==RESULT_OK)
            {
                service_name=data.getStringExtra("service_name");
                service_id=data.getStringExtra("service_id");
                Toast.makeText(search_center_details.this, ""+service_name, Toast.LENGTH_SHORT).show();
                service.setText(service_name);
            }
        }
    }

    //on click
    private void search()
    {

        if(model_id.equals("0"))
        {
            Toast.makeText(search_center_details.this, "من فضلك اختر موديل السياره", Toast.LENGTH_SHORT).show();
        }

        else {
            Intent intent=new Intent(search_center_details.this,search_result.class);
            intent.putExtra("brand_id",brand_id);
            intent.putExtra("service_id",service_id);
            intent.putExtra("city_id",city_id);
            startActivity(intent);
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
