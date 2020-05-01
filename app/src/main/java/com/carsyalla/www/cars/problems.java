package com.carsyalla.www.cars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.presenter.problrmsPresenter;

public class problems extends AppCompatActivity implements com.carsyalla.www.cars.interfaces.problems.interfaces.View {
    EditText name, phone, message;
    Button send;
    com.carsyalla.www.cars.presenter.problrmsPresenter problrmsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problems);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        message = (EditText) findViewById(R.id.message);
        send = (Button) findViewById(R.id.send_problem);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")) {
                    Toast.makeText(problems.this, "من فضلك ادخل الاسم", Toast.LENGTH_SHORT).show();
                } else if (phone.getText().toString().equals("")) {
                    Toast.makeText(problems.this, "من فضلك ادخل رقم الهاتف", Toast.LENGTH_SHORT).show();
                } else if (message.getText().toString().equals("")) {
                    Toast.makeText(problems.this, "من فضلك ادخل الرساله", Toast.LENGTH_SHORT).show();
                } else {
                    loading loading = new loading();
                    loading.loadingDialog(problems.this, R.layout.loading_login, .80);
                    problrmsPresenter = new problrmsPresenter(problems.this, problems.this, name, phone, message);
                    problrmsPresenter.getData();
                }
            }
        });
    }
}
