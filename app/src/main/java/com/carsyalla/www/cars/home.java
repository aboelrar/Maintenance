package com.carsyalla.www.cars;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;
import com.carsyalla.www.edit_profile.edit_profile;
import com.carsyalla.www.cars.dialog.adsDialog;
import com.carsyalla.www.cars.dialog.searchDialog;
import com.carsyalla.www.cars.interfaces.navigation_userName;
import com.carsyalla.www.cars.interfaces.popup_ads;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.library.loginTrueOrFalse;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.userPresenter;
import com.carsyalla.www.cars.ui.fragment_home;
import com.carsyalla.www.cars.ui.fragment_stolen_car;
import com.carsyalla.www.cars.ui.reported_car;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class home extends AppCompatActivity implements navigation_userName.View, popup_ads.interfaces.View {
com.carsyalla.www.cars.dialog.searchDialog searchDialog;
ImageView search;
    com.carsyalla.www.cars.presenter.userPresenter userPresenter;
 static    DrawerLayout drawerLayout;
    static  double  current_lat,current_lng;
    static String lat,lng;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    View background;
    Boolean mLocationPermissionsGranted = false;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    adsDialog ads_dialog=new adsDialog();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        FirebaseApp.initializeApp(this);

        checkConnection checkConnection=new checkConnection();
        if(checkConnection.isOnline(this)==true) {
        setContentView(R.layout.activity_home);
                  Runnable r = new Runnable() {
            @Override
            public void run(){
                ads_dialog.ads_dialog(home.this, R.layout.ads_popup,0.90,home.this);
            }
        };
        Handler h = new Handler();
        h.postDelayed(r, 3000); //
            Log.e("Token is ", FirebaseInstanceId.getInstance().getToken());
            onClick();
        menu();
        fabbing();
        allCenters();
        bottomNav();
        navigationClick();
        }
    else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    //get dialog for search centers
    private void searchCenters()
    {
        searchDialog =new searchDialog();
        searchDialog.searchDialog(home.this,R.layout.activity_search,.85);
    }

    //on Click Buttons
    private void onClick()
    {
        //search
       search=(ImageView)findViewById(R.id.search);
       search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(home.this,search.class));
           }
       });

       //fabSpeedDial
        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.mini_fab);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }
        });

    }

    //menu button
    public void menu()
    {
          drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        ImageView menu=(ImageView)findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.END)) {
                    drawerLayout.closeDrawer(Gravity.END);

                } else {
                    drawerLayout.openDrawer(Gravity.END);
                }
            }
        });
    }

    //Fab Dialog
    private void fabbing()
    {
        // add new product
        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.mini_fab);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                background=(View) findViewById(R.id.background_dimmer);
                background.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.addCenter)
                {
                    startActivity(new Intent(home.this,addNewCenter.class));
                    background.setVisibility(View.GONE);

                }
                else if(menuItem.getItemId()==R.id.addstolencar)
                {
                    startActivity(new Intent(home.this,add_new_stolen_car.class));
                    background.setVisibility(View.GONE);


                }
                else if(menuItem.getItemId()==R.id.addreportedcar)
                {
                    startActivity(new Intent(home.this,add_reported_car.class));
                    background.setVisibility(View.GONE);

                }
                return true;
            }

            @Override
            public void onMenuClosed() {
                background.setVisibility(View.GONE);
            }
        });
    }

    //ADD FRAGMENT TO ACTIVITY
    private void allCenters()
    {
        Fragment home=new fragment_home();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, home ).commit();
    }

    //ADD FRAGMENT STOELN CARS
    private void stolen_Cars()
    {
        Fragment stolenCar=new fragment_stolen_car();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, stolenCar ).commit();
    }

    //ADD FRAGMENT reported CARS
    private void reported_Cars()
    {
        Fragment reported=new reported_car();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag, reported ).commit();
    }
    public void bottomNav() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.allCenters);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.allCenters) {
                    allCenters();
                } else if (item.getItemId() == R.id.stolenCars) {
                    stolen_Cars();
                }
                else if(item.getItemId()==R.id.reportedCae)
                {
                    reported_Cars();
                }
                return true;
            }
        });}

     //Navigation Click
    private void navigationClick()
    {
        //SET LOGIN NAME
        TextView logout=(TextView)findViewById(R.id.logout);
        TextView  userName=(TextView)findViewById(R.id.userName);
        final savedId savedId=new savedId();
        if(savedId.getUserBoolean(home.this)==true)
        {
            userPresenter =new userPresenter(home.this,home.this,userName,savedId.getId(home.this));
            userPresenter.getData();
            logout.setText("تسجيل الخروج");
          /*  SharedPreferences sharedPreferences = getSharedPreferences("idss", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();*/
        }
        //GO TO SEARCH
        LinearLayout searchNav=(LinearLayout)findViewById(R.id.searchNav);
        searchNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,search.class));
            }
        });

        //logout
        TextView Logout=(TextView)findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,LoginAndRegist.class);
                startActivity(intent);
                loginTrueOrFalse loginTrueOrFalse=new loginTrueOrFalse();
                loginTrueOrFalse.userId(home.this);
            }
        });

        //Add Center
        LinearLayout addCenter=(LinearLayout)findViewById(R.id.addCenter);
        addCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,addNewCenter.class));
            }
        });

        //My Reservation
        LinearLayout my_reservation=(LinearLayout)findViewById(R.id.my_reservation);
        my_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,my_reservations.class));

            }
        });

        //Go To Favourite
        LinearLayout favourite=(LinearLayout)findViewById(R.id.myFavourite);
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,my_favourite.class);
                startActivity(intent);
            }
        });
        //Problems and contact us
        LinearLayout problems=(LinearLayout)findViewById(R.id.problems);
        problems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,problems.class);
                startActivity(intent);
            }
        });
        //GO TO EDIT PROFILE
        LinearLayout editprofile=(LinearLayout)findViewById(R.id.editprofile);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(savedId.getUserBoolean(home.this)==false)
                {
                    Toast.makeText(home.this, "يجب تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(home.this,LoginAndRegist.class);
                    intent.putExtra("res","edit");
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(home.this, edit_profile.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        drawerLayout.closeDrawer(Gravity.END);

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
