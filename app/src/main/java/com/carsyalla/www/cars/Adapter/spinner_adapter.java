package com.carsyalla.www.cars.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.cars.Model.spinner_list;
import com.carsyalla.www.cars.allCenters;
import com.carsyalla.www.cars.interfaces.centers;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.interfaces.nearby_centers;
import com.carsyalla.www.cars.presenter.centerPresenter;
import com.carsyalla.www.cars.presenter.nearby_centerPresenter;

import com.carsyalla.www.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class spinner_adapter extends ArrayAdapter implements centers.interfaces.View, favourite.interfaces.View, nearby_centers.interfaces.View{
    public ArrayList<spinner_list> mylist;
    public Context context;
    public int resource,id;
    centers.interfaces.View allcentes_v;
    favourite.interfaces.View fav_v;
    nearby_centers.interfaces.View nearby_v;
    RecyclerView centerlis,specialList;
    String brandId;
    ImageView ads;
    ProgressBar loading;
    Spinner spinner;
    int number;
    static  double  current_lat,current_lng;
    LinearLayout search_d;
    static String lat,lng;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Boolean mLocationPermissionsGranted = false;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    String service_id;

    public spinner_adapter(@NonNull Context context, @LayoutRes int resource,int id, @NonNull ArrayList<spinner_list> mylist,centers.interfaces.View allcentes_v,favourite.interfaces.View fav_v,nearby_centers.interfaces.View nearby_v,RecyclerView speciallist,RecyclerView centerlist,String brandId,ImageView ads,ProgressBar loading,Spinner spinner,int number,String service_id) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.mylist = mylist;
        this.id=id;
        this.allcentes_v=allcentes_v;
        this.fav_v=fav_v;
        this.nearby_v=nearby_v;
        this.centerlis=centerlist;
        this.specialList=speciallist;
        this.brandId=brandId;
        this.ads=ads;
        this.loading=loading;
        this.spinner=spinner;
        this.number=number;
        this.service_id=service_id;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // item view
        if(position==0)
        {
            convertView = inflater.inflate(R.layout.sort_first_item, parent, false);
            TextView sortname=(TextView)convertView.findViewById(R.id.sortname);
            sortname.setText(mylist.get(position).getTitle().toString());

        }
        else {
            convertView = inflater.inflate(R.layout.sort_item, parent, false);
            final RadioButton radio = (RadioButton) convertView.findViewById(R.id.sort);
            radio.setText(mylist.get(position).getTitle().toString());
            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position==1)
                    {
                        loading.setVisibility(View.VISIBLE);
                        centerPresenter   centerPresenter=new centerPresenter(allcentes_v, context, centerlis, specialList, brandId, fav_v, ads,null, 1,loading,service_id);
                        centerPresenter.getData();
                        hideSpinnerDropDown(spinner);
                        number=0;
                        Intent intent = new Intent("custom-message");
                        //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                        intent.putExtra("num","allcenters");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    }
                    else if(position==2)
                    {
                        hideSpinnerDropDown(spinner);
                        number=1;
                        statusCheck();
                       getLocationPermission();
                        getDeviceLocation();
                        Intent intent = new Intent("custom-message");
                        //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                        intent.putExtra("num","nearby");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    }
                }
            });
            if (position==1)
            {
                if(number==0)
                {
                    radio.setChecked(true);
                }
            }else if(position==2)
            {
                if(number==1)
                {
                    radio.setChecked(true);
                }
            }
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
    @Override
    public int getCount() {
        return mylist.size();
    }

    public static void hideSpinnerDropDown(Spinner spinner) {
        try {
            Method method = Spinner.class.getDeclaredMethod("onDetachedFromWindow");
            method.setAccessible(true);
            method.invoke(spinner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //My Loca
    private void getDeviceLocation() {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        try {
            if (mLocationPermissionsGranted) {

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                        getLocationPermission();
                        if(task.isSuccessful() && task.getResult() != null){
                            Location currentLocation = (Location) task.getResult();
                            //get latitude
                            current_lat=currentLocation.getLatitude();
                            lat = String.valueOf(current_lat);
                            //get langitude
                            current_lng=currentLocation.getLongitude();
                            lng=String.valueOf(current_lng);
                            setCurrent_lat(context);
                            loading.setVisibility(View.VISIBLE);
                            nearby_centerPresenter nearby_centerPresenter=new nearby_centerPresenter(nearby_v,context,centerlis,specialList,brandId,fav_v,ads,null,1,loading,current_lat+"/"+current_lng,service_id);
                            nearby_centerPresenter.getData();
                            Log.e("sdrfrfrf",""+current_lat+current_lng);

                        }else{
                            Toast.makeText(context, "لم نتمكن من تحديد موقعك", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Toast.makeText(context, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void getLocationPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(context.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(context.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;

            }else{
                ActivityCompat.requestPermissions(((allCenters)(context)),
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);

            }
        }else{
            ActivityCompat.requestPermissions(((allCenters)(context)),
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
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

    public void statusCheck() {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();


        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("لم نتمكن من تحديد موقعك يجب السماح بالوصول الى خدمات الموقع")
                .setCancelable(false)
                .setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

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

}
