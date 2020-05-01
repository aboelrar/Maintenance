package com.carsyalla.www.forget_forget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.library.savedId;

public class forget_passwrd extends AppCompatActivity implements forget_password_i.interfaces.View{
    forget_password_presenter forget_password_presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_passwrd);
        send_message();
    }

    //On Click Send Mesage
    private void send_message()
    {
        final EditText phone=(EditText)findViewById(R.id.phone);
        ImageView sendcode=(ImageView)findViewById(R.id.sendcode);

        //Send Message
        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.getText().toString().equals(""))
                {
                    Toast.makeText(forget_passwrd.this, "من فضلك ادخل رقم الهاتف", Toast.LENGTH_SHORT).show();
                }
                else {
                    loading loading=new loading();
                    loading.loadingDialog(forget_passwrd.this,R.layout.loading_login,.80);
                    forget_password_presenter=new forget_password_presenter(forget_passwrd.this,forget_passwrd.this,new savedId().getId(forget_passwrd.this),phone.getText().toString());
                    forget_password_presenter.getData();
                }
            }
        });

    }
}
