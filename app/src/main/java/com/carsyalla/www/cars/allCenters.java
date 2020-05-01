package com.carsyalla.www.cars;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.carsyalla.www.R;
import com.carsyalla.www.edit_profile.edit_profile;
import com.carsyalla.www.cars.Adapter.centerAdapter;

import com.carsyalla.www.cars.Adapter.spinner_adapter;
import com.carsyalla.www.cars.Model.spinner_list;
import com.carsyalla.www.cars.interfaces.centers;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.interfaces.navigation_userName;
import com.carsyalla.www.cars.interfaces.nearby_centers;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.library.loginTrueOrFalse;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.centerPresenter;
import com.carsyalla.www.cars.presenter.nearby_centerPresenter;
import com.carsyalla.www.cars.presenter.userPresenter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class allCenters extends AppCompatActivity implements centers.interfaces.View, favourite.interfaces.View, nearby_centers.interfaces.View, navigation_userName.View {
  static   RecyclerView centerList,specialList;
    centerPresenter centerPresenter;
    nearby_centerPresenter nearby_centerPresenter;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private LocationRequest mLocationRequest;
    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    static ImageView ads;
    Intent intent;
    static ViewSwitcher switcher;
     static String  numo="0";
     static  String service_id;
    int num=1;
    static String numberof="0";
    static  String saved_num_fav="1";
  static   ProgressBar loading;
    NestedScrollView nestedScrollView;
    static  double  current_lat,current_lng;
    LinearLayout search_d;
    static String lat,lng;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Boolean mLocationPermissionsGranted = false;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    com.carsyalla.www.cars.library.savedId savedId;
    static String brandId;
    /**
     * IMPLEMENT PAGINATION
     */
    int pagenum=1;
    int itemcount=10;
    LinearLayoutManager linearLayoutManager;
    private boolean isLoading=true;
    private int pastVisibleItem,visibleItemCount,totalItemCount,previous_total=0;
    private int viewthrishold=12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    checkConnection checkConnection = new checkConnection();
    if (checkConnection.isOnline(this) == true) {
        setContentView(R.layout.activity_all_centers);
        savedId=new savedId();
        intent = getIntent();
        brandId = intent.getStringExtra("brand_id");
        service_id=intent.getStringExtra("service_id");
        centerList = (RecyclerView) findViewById(R.id.centerlist);
        specialList = (RecyclerView) findViewById(R.id.specialList);
        /**
         * Layout Manager
         */
        centerList.setNestedScrollingEnabled(true);
        linearLayoutManager=new LinearLayoutManager(allCenters.this);
        centerList.setHasFixedSize(true);
        centerList.setLayoutManager(linearLayoutManager);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
       // switcher=(ViewSwitcher)findViewById(R.id.switcher);
        ads = (ImageView) findViewById(R.id.ads);
        ads.setImageResource(0);
        loading = (ProgressBar) findViewById(R.id.loading);

      /*  centerAdapter centerAdapter = new centerAdapter(this, null, this, loading,service_id);
            centerAdapter.centerPresenter(allCenters.this, allCenters.this, centerList, specialList, brandId, allCenters.this, ads, num);*/

      centerPresenter=new centerPresenter(allCenters.this, allCenters.this, centerList, specialList, brandId, allCenters.this, ads,null, num,loading,service_id);
     centerPresenter.getData();
      //  doPagination();
        menu();
        navigationClick();
        onClick();
       // statusCheck();
       // getLocationPermission();
      //  getDeviceLocation() ;

    }
    else {
        setContentView(R.layout.no_connection);
        onclickonconnection();
    }
}
    //menu button
    public void menu()
    {
        final DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
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

    //on Click Buttons
    private void onClick()
    {
      final ImageView  search=(ImageView)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allCenters.this,search.class));
            }
        });


        //Change sort
        //get nearest place
       final Spinner nearest=(Spinner)findViewById(R.id.spinnersort);
        ArrayList<spinner_list>mylist=new ArrayList<spinner_list>();
        mylist.add(new spinner_list("رتب"));
        mylist.add(new spinner_list("جميع المراكز"));
        mylist.add(new spinner_list("الاقرب اليك"));
        spinner_adapter adapter = new spinner_adapter(allCenters.this,R.layout.sort_item,R.id.sort,mylist,allCenters.this,allCenters.this,allCenters.this,specialList,centerList,brandId,ads,loading,nearest,0,service_id);
        nearest.setAdapter(adapter);
        nearest.setSelection(0, false);

        //GO TO SEARCH
         LinearLayout search_d=(LinearLayout)findViewById(R.id.search_d);
        search_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allCenters.this,search.class));
            }
        });

    }


    //Navigation Click
    private void navigationClick()
    {
        //SET LOGIN NAME
        TextView logout=(TextView)findViewById(R.id.logout);
        TextView  userName=(TextView)findViewById(R.id.userName);
        final savedId savedId=new savedId();
        if(savedId.getUserBoolean(allCenters.this)==true)
        {
            userPresenter     userPresenter =new userPresenter(allCenters.this,allCenters.this,userName,savedId.getId(allCenters.this));
            userPresenter.getData();
            logout.setText("تسجيل الخروج");
            /*SharedPreferences sharedPreferences = getSharedPreferences("idss", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();*/
        }
        //GO TO SEARCH
        LinearLayout searchNav=(LinearLayout)findViewById(R.id.searchNav);
        searchNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allCenters.this,search.class));
            }
        });

        //logout
        TextView Logout=(TextView)findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(allCenters.this,LoginAndRegist.class);
                startActivity(intent);
                loginTrueOrFalse loginTrueOrFalse=new loginTrueOrFalse();
                loginTrueOrFalse.userId(allCenters.this);
            }
        });

        //Add Center
        LinearLayout addCenter=(LinearLayout)findViewById(R.id.addCenter);
        addCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allCenters.this,addNewCenter.class));
            }
        });

        //My Reservation
        LinearLayout my_reservation=(LinearLayout)findViewById(R.id.my_reservation);
        my_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(allCenters.this,my_reservations.class));

            }
        });

        //Go To Favourite
        LinearLayout favourite=(LinearLayout)findViewById(R.id.myFavourite);
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(allCenters.this,my_favourite.class);
                startActivity(intent);
            }
        });
        //Problems and contact us
        LinearLayout problems=(LinearLayout)findViewById(R.id.problems);
        problems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(allCenters.this,problems.class);
                startActivity(intent);
            }
        });
        //GO TO EDIT PROFILE
        LinearLayout editprofile=(LinearLayout)findViewById(R.id.editprofile);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(savedId.getUserBoolean(allCenters.this)==false)
                {
                    Toast.makeText(allCenters.this, "يجب تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(allCenters.this,LoginAndRegist.class);
                    intent.putExtra("res","edit");
                    startActivity(intent);                }
                else {
                    Intent intent = new Intent(allCenters.this, edit_profile.class);
                    startActivity(intent);
                }
            }
        });

        //Go TO HOME
        LinearLayout home=(LinearLayout)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(allCenters.this, home.class);
                startActivity(intent);
            }
        });
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



    private void getLocationPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    getDeviceLocation();

                    //initialize our map
                }
            }
        }
    }

    //SAVE CURRENT LAT AND LNG
    //get user id shared prefrences
    public void setCurrent_lat(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("location",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("loca", lat+"/"+lng);
        editor.commit();
    }

    //Recieve message from adapter
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            numo = intent.getStringExtra("num");
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        if(numo.equals("nearby"))
        {
            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                startLocationUpdates();
                numberof="0";
            }
        }
        else {
        if(saved_num_fav.equals("2"))
        {
            nearby_centerPresenter=new nearby_centerPresenter(allCenters.this,allCenters.this,centerList,specialList,brandId,allCenters.this,ads,null,1,loading,savedId.retrieveLoca(allCenters.this),service_id);
            nearby_centerPresenter.getData();

        }else {
            centerPresenter=new centerPresenter(allCenters.this, allCenters.this, centerList, specialList, brandId, allCenters.this, ads,null, num,loading,service_id);
            centerPresenter.getData();
            doPagination();

        }}

}

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }

    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("لم نتمكن من تحديد موقعك يجب السماح بالوصول الى خدمات الموقع")
                .setCancelable(false)
                .setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
                    }
                })
                .setNegativeButton("رفض", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    //My Loca
    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(allCenters.this);
        try {
            if (mLocationPermissionsGranted) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(allCenters.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                final Task<Location> location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {


                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task task) {
                        if(task.isSuccessful() && task.getResult() != null){
                            Location currentLocation = (Location) task.getResult();
                            //get latitude
                            current_lat=currentLocation.getLatitude();
                            lat = String.valueOf(current_lat);
                            //get langitude
                            current_lng=currentLocation.getLongitude();
                            lng=String.valueOf(current_lng);
                            setCurrent_lat(allCenters.this);
                            loading.setVisibility(View.VISIBLE);
                            nearby_centerPresenter nearby_centerPresenter=new nearby_centerPresenter(allCenters.this,allCenters.this,centerList,specialList,brandId,allCenters.this,ads,null,1,loading,current_lat+"/"+current_lng,service_id);
                            nearby_centerPresenter.getData();
                            Log.e("sdrfrfrf",""+current_lat+current_lng);

                        }else{
                            Toast.makeText(allCenters.this, "لم نتمكن من تحديد موقعك", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                Toast.makeText(allCenters.this, "لم نتمكن من تحديد موقعك", Toast.LENGTH_SHORT).show();
            }
        }catch (SecurityException e){
            Toast.makeText(allCenters.this, "حدث خطا ما", Toast.LENGTH_SHORT).show();
        }

    }


    //UPDATE LOCATION AFTER OPEN GBS
    protected void startLocationUpdates() {

        // Create the location request to start receiving updates
        final LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

            getFusedLocationProviderClient(allCenters.this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            // do work here
                            if(numberof.equals("0")) {
                                onLocationChanged(locationResult.getLastLocation());
                            }
                            else {
                                getFusedLocationProviderClient(allCenters.this).removeLocationUpdates(new LocationCallback());
                            }
                        }
                    },
                    Looper.myLooper());
        }

        public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        loading.setVisibility(View.VISIBLE);
        nearby_centerPresenter nearby_centerPresenter=new nearby_centerPresenter(allCenters.this,allCenters.this,centerList,specialList,brandId,allCenters.this,ads,null,1,loading,location.getLatitude()+"/"+location.getLongitude(),service_id);
        nearby_centerPresenter.getData();
        Log.e("sdrfrfrf",""+current_lat+current_lng);
        numberof="1";
    }

    /**
     * DO PAGINATION
     */
    private void doPagination()
    {

        centerList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount=linearLayoutManager.getChildCount();
                totalItemCount=linearLayoutManager.getItemCount();
                pastVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();
                if(dy>0)
                {
                    if(isLoading)
                    {
                        if(totalItemCount>previous_total)
                        {
                            isLoading=false;
                            previous_total=totalItemCount;
                        }
                    }
                    if(!isLoading&&(totalItemCount-visibleItemCount)<=(pastVisibleItem+viewthrishold))
                    {
                        pagenum++;
                        centerPresenter=new centerPresenter(allCenters.this, allCenters.this, centerList, specialList, brandId, allCenters.this, ads,null, pagenum,loading,service_id);
                        centerPresenter.getData3();
                        isLoading=true;
                    }}
            }
        });
    }
}
