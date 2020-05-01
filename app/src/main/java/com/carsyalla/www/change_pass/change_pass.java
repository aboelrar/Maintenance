package com.carsyalla.www.change_pass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.dialog.loading;

public class change_pass extends AppCompatActivity implements change_pass_i.interfaces.View {
    change_pass_prsenter change_pass_prsenter;
    EditText oldpass,newpass,renewpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pass);
        oldpass=(EditText)findViewById(R.id.oldpass);
        newpass=(EditText)findViewById(R.id.newpass);
        renewpass=(EditText)findViewById(R.id.renewpass);
        ImageView changepass=(ImageView)findViewById(R.id.changepass);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldpass.getText().toString().equals(""))
                {
                    Toast.makeText(change_pass.this, "من فضلك ادخل كلمة المرور القديمة", Toast.LENGTH_SHORT).show();
                }
                else if(newpass.getText().toString().equals(""))
                {
                    Toast.makeText(change_pass.this, "من فضلك ادخل كلمة المرور الجديدة", Toast.LENGTH_SHORT).show();
                }
                else if(renewpass.getText().toString().equals(""))
                {
                    Toast.makeText(change_pass.this, "من فضلك اعد كتابة كلمة المرور ", Toast.LENGTH_SHORT).show();
                }
                else {
                loading loading=new loading();
                loading.loadingDialog(change_pass.this,R.layout.loading,.80);
                change_pass_prsenter=new change_pass_prsenter(change_pass.this,change_pass.this,newpass,oldpass,renewpass,null);
                change_pass_prsenter.getData();
            }}
        });

    }
}
