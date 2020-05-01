package com.carsyalla.www.cars;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.dialog.reserveDialog;
import com.carsyalla.www.cars.interfaces.centerdetails;
import com.carsyalla.www.cars.interfaces.checkList;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.centerDetailsPresenter;

import com.carsyalla.www.cars.ui.comments;
import me.relex.circleindicator.CircleIndicator;

public class centerDetails extends AppCompatActivity implements centerdetails.interfaces.View, checkList.interfaces.View, com.carsyalla.www.cars.interfaces.reservation.interfaces.View, favourite.interfaces.View {
    TextView centerName,descripition,discount;
    static  String center_id,brand_id,model_id,model_name;
    RatingBar ratings;
    com.carsyalla.www.cars.presenter.centerDetailsPresenter centerDetailsPresenter;
    Intent intent;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    static com.carsyalla.www.cars.dialog.reserveDialog reserveDialog;
    Button reservation;
    boolean isFav =false;
    ImageView fav;
    com.carsyalla.www.cars.presenter.favouritePresenter favouritePresenter;
    Button comments,center_details;
    ViewSwitcher switcher;
   static LinearLayout discount_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.activity_center_details);
        centerName=(TextView)findViewById(R.id.centerName);
        descripition=(TextView)findViewById(R.id.descripition);
        ratings=(RatingBar)findViewById(R.id.ratings);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        circleIndicator=(CircleIndicator)findViewById(R.id.indicator);
        reservation=(Button) findViewById(R.id.reserve);
        fav=(ImageView)findViewById(R.id.fav);

        //ON CLICK BUTTON
            onClick();

        //reserve center
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new savedId().getUserBoolean(centerDetails.this)==false)
                {
                    Intent intent=new Intent(centerDetails.this,LoginAndRegist.class);
                    intent.putExtra("res","res");
                    startActivity(intent);
                    Toast.makeText(centerDetails.this, "يرجى تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                }
                    else {
                reserveDialog = new reserveDialog();
                reserveDialog.reserve(centerDetails.this, R.layout.reservation, .80, centerDetails.this, null);
            }}
        });
     //get Center Id
        intent=getIntent();
        center_id=intent.getStringExtra("center_id");
    //retrieve data for details
          ImageView fav=(ImageView)findViewById(R.id.fav);
            discount=(TextView)findViewById(R.id.discount);
            discount_item=(LinearLayout)findViewById(R.id.discountitem);
          centerDetailsPresenter=new centerDetailsPresenter(centerDetails.this,centerDetails.this,center_id,centerName,ratings,descripition,viewPager,circleIndicator,fav,centerDetails.this);
        centerDetailsPresenter.getData();
   //center details
        centerDetails();
    }
    else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }
 //go to center details
    private void centerDetails()
    {
        Bundle bundle = new Bundle();
        bundle.putString("center_id",center_id);
        Fragment centerDetails=new com.carsyalla.www.cars.ui.centerdetails();
        FragmentManager fragmentManager=getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, centerDetails).commit();
        centerDetails.setArguments(bundle);
    }
//GO TO COMMENTS
    private void getComments()
    {
        Bundle bundle = new Bundle();
        bundle.putString("center_id",center_id);
        Fragment comments=new comments();
        FragmentManager fragmentManager=getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, comments).commit();
        comments.setArguments(bundle);
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
                reserveDialog.brand(brand_id,model_id,center_id);
                reserveDialog.setModel_name(model_name);
            }
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

    //ON CLICK BUTTONS
    private  void onClick()
    {
        center_details=(Button)findViewById(R.id.center_details);
        //COMMENTS
        comments=(Button)findViewById(R.id.comments);
        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getComments();
                center_details.setBackgroundResource(R.color.buttons);
                comments.setBackgroundResource(R.color.white);
                Typeface typeface = null;
                Typeface typeface1 = null;

                //SET COMMENTS COLORS
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    typeface = getResources().getFont(R.font.heading);
                    typeface1 = getResources().getFont(R.font.phargraphe);
                }
                else
                {
                     typeface = ResourcesCompat.getFont(centerDetails.this, R.font.heading);
                    typeface1 = ResourcesCompat.getFont(centerDetails.this, R.font.phargraphe);

                }
                comments.setTypeface(typeface);
                center_details.setTypeface(typeface1);
            }
        });

        //CENTER DETAILS
        center_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerDetails();
                comments.setBackgroundResource(R.color.buttons);
                center_details.setBackgroundResource(R.color.white);
                Typeface typeface = null;
                Typeface typeface1 = null;

                //SET COMMENTS COLORS
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    typeface = getResources().getFont(R.font.heading);
                    typeface1 = getResources().getFont(R.font.phargraphe);
                }
                else
                {
                    typeface = ResourcesCompat.getFont(centerDetails.this, R.font.heading);
                    typeface1 = ResourcesCompat.getFont(centerDetails.this, R.font.phargraphe);

                }
                center_details.setTypeface(typeface);
                comments.setTypeface(typeface1);
            }
        });

    }
}
