package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.library.checkConnection;

public class search_by_name extends AppCompatActivity {
EditText searchByName;
Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.search_by_name);
        searchByName=(EditText)findViewById(R.id.searchByName);
        search=(Button)findViewById(R.id.search);
        //Search Button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(search_by_name.this,search_rasult_name.class);
                intent.putExtra("name",searchByName.getText().toString());
                startActivity(intent);
            }
        });
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
