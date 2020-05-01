package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.ratePresenter;

public class rating extends AppCompatActivity implements com.carsyalla.www.cars.interfaces.rate.interfaces.View {
    com.carsyalla.www.cars.presenter.ratePresenter ratePresenter;
    CheckBox show_hidden;
    static String privacy="1";
    EditText addcomment;
    RatingBar RatingBar;
   static String center_id;
    int rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);
        rate();
        Intent intent=getIntent();
        String user_name=intent.getStringExtra("user_name");
        String center_name=intent.getStringExtra("center_name");
        center_id=intent.getStringExtra("center_id");
        //SET USERNAME
        TextView username=(TextView)findViewById(R.id.username);
        username.setText(user_name);
        //SET CENTERNAME
        TextView centerName=(TextView)findViewById(R.id.centerName);
        centerName.setText(center_name);
        }

        //SEND RATE
    private void rate()
    {
        //SET PRIVACY
        show_hidden=(CheckBox)findViewById(R.id.show_hidden);
        show_hidden.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    privacy="2";
                }
                else {
                    privacy="1";
                }
            }
        });
        //ADD COMMENT
        addcomment=(EditText) findViewById(R.id.addcomment);
        //ADD RATE
        RatingBar=(RatingBar)findViewById(R.id.rating);
        RatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(android.widget.RatingBar ratingBar, float rating, boolean fromUser) {
                rate=Math.round(rating);
            }
        });
        //SEND RATE
        Button send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading loading=new loading();
                loading.loadingDialog(rating.this,R.layout.loading_login,.80);
                //GET RESERVATION ID
                Intent intent=getIntent();
                String reservation_id=intent.getStringExtra("reservation_id");
                //GET USER ID
                savedId savedId=new savedId();
                //SEND DATA
                ratePresenter ratePresenter=new ratePresenter(rating.this, rating.this,privacy,addcomment.getText().toString(),center_id,reservation_id,savedId.getId(rating.this),rate);
                ratePresenter.getData();
            }
        });
    }
}
