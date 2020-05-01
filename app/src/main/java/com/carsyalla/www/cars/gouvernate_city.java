package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.interfaces.gouvernate;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.presenter.gouvernatePresenter;

public class gouvernate_city extends AppCompatActivity implements gouvernate.interfaces.View {
    RecyclerView gouvernateList;
    com.carsyalla.www.cars.presenter.gouvernatePresenter gouvernatePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.activity_gouvernate);
        gouvernateList=(RecyclerView)findViewById(R.id.gouvernateList);
        gouvernatePresenter=new gouvernatePresenter(gouvernate_city.this,gouvernate_city.this,gouvernateList);
        gouvernatePresenter.getData();
    }
    else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String city_id = data.getStringExtra("city_id");
                String gouvernate_id = data.getStringExtra("gouvernate_id");
                String gouvernate_name = data.getStringExtra("gouvernate_name");
                String city_name = data.getStringExtra("city_name");

                Intent intent = new Intent();
                intent.putExtra("city_id", city_id);
                intent.putExtra("gouvernate_id", gouvernate_id);
                intent.putExtra("gouvernate_name",gouvernate_name);
                intent.putExtra("city_name",city_name);
                setResult(RESULT_OK, intent);
                finish();
            }
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
