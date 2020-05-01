package com.carsyalla.www.edit_profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;

import com.carsyalla.www.change_pass.change_pass;
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.library.savedId;

public class edit_profile extends AppCompatActivity implements my_profile_data_and_edit_i.View{
    edit_profile_prsenter edit_profile_prsenter;
    static EditText fullname,Email,phoneNum;
    TextView changepass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        fullname=(EditText)findViewById(R.id.username);
        Email=(EditText)findViewById(R.id.email);
        phoneNum=(EditText)findViewById(R.id.phone);
        edit_profile_prsenter=new edit_profile_prsenter(edit_profile.this,edit_profile.this,fullname,phoneNum,Email,new savedId().getId(edit_profile.this));
        edit_profile_prsenter.getData();
      //edit profile
        edit_data();
    }
    //On click
    private  void edit_data()
    {
       ImageView editdata=(ImageView) findViewById(R.id.editdata);
        editdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullname.getText().toString().equals(""))
                {
                    Toast.makeText(edit_profile.this, "من فضلك ادخل الاسم", Toast.LENGTH_SHORT).show();
                }
                else if(Email.getText().toString().equals(""))
                {
                    Toast.makeText(edit_profile.this, "من فضلك ادخل البريد الاكترونى", Toast.LENGTH_SHORT).show();
                }
                else if(phoneNum.getText().toString().equals(""))
                {
                    Toast.makeText(edit_profile.this, "من فضلك ادخل رقم الهاتف", Toast.LENGTH_SHORT).show();
                }
                else {
                    loading loading = new loading();
                    loading.loadingDialog(edit_profile.this, R.layout.loading, .80);
                    edit_profile_prsenter = new edit_profile_prsenter(edit_profile.this, edit_profile.this, fullname, phoneNum, Email, new savedId().getId(edit_profile.this));
                    edit_profile_prsenter.secondData();
                }
            }
        });
        changepass=(TextView)findViewById(R.id.changepass);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(edit_profile.this, change_pass.class));
            }
        });

    }
}
