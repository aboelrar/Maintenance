package com.carsyalla.www.cars;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.carsyalla.www.R;
import com.carsyalla.www.cars.dialog.checkDialog;
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.interfaces.addCenter;
import com.carsyalla.www.cars.interfaces.checkList;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.library.removeArrayListData;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.addCenterPresenter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class addNewCenter extends AppCompatActivity implements OnMapReadyCallback, checkList.interfaces.View, addCenter.interfaces.View {
    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private FusedLocationProviderClient mFusedLocationProviderClient;
  static  double  current_lat,current_lng;
  static String lat,lng;
    static String cr_lat,cr_lng;
    com.carsyalla.www.cars.dialog.checkDialog checkDialog;
    static ArrayList<String> Album=null;
    static EditText centerName, descripition, email, phone, address, workName, timework, to ,discount,holidays;
  static   TextView city,services, brands,goToMap;
    static String city_id, gouvernate_id;
    Boolean mLocationPermissionsGranted = false;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    com.carsyalla.www.cars.presenter.addCenterPresenter addCenterPresenter;
    static ImageView uploadedimg;
    static String Imagez="0";
    static Bitmap SelectedPhoto;
    ImageView addPhones;
    static  LinearLayout album;
    com.carsyalla.www.cars.library.savedId savedId;
    int multiImage=3;
    com.carsyalla.www.cars.library.removeArrayListData removeArrayListData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
            setContentView(R.layout.activity_add_new_center);
            MultiDex.install(this);
//REMOVE ALL ARRAYLIST
            removeArrayListData = new removeArrayListData();
            removeArrayListData.removeArrayBrands(addNewCenter.this);
            removeArrayListData.removeArrayListServices(addNewCenter.this);

            centerName = (EditText) findViewById(R.id.centerName);
            descripition = (EditText) findViewById(R.id.descripition);
            address = (EditText) findViewById(R.id.address);
            discount = (EditText) findViewById(R.id.discount);
            workName = (EditText) findViewById(R.id.ownerName);
            email = (EditText) findViewById(R.id.email);
            phone = (EditText) findViewById(R.id.phoneNUm);
            city = (TextView) findViewById(R.id.city);
            timework = (EditText) findViewById(R.id.timework);
            uploadedimg = (ImageView) findViewById(R.id.uplaodImg);
            album = (LinearLayout) findViewById(R.id.album);
            goToMap = (TextView) findViewById(R.id.myloca);
            holidays=(EditText)findViewById(R.id.holidays);
            onClick();
            addCenter();
            getloca();
        }
  else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    //on click buttons
    private void onClick() {
        checkDialog = new checkDialog();

        //go to map
        goToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addNewCenter.this,add_center_map.class);
                startActivityForResult(intent, 4);

            }
        });
        //services_i
        services = (TextView) findViewById(R.id.service);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDialog.searchDialog(addNewCenter.this, R.layout.checkdialog, .80, addNewCenter.this,services);

            }
        });
        //Brands
        brands = (TextView) findViewById(R.id.brands);
        brands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDialog.brandDialog(addNewCenter.this, R.layout.checkdialog, .80, addNewCenter.this,brands);

            }
        });
        //city
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(addNewCenter.this, gouvernate_city.class);
                startActivityForResult(i, 1);
            }
        });
        //upload center image
        uploadedimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "اختر الصوره الرئيسيه"),2);
            }
        });

        //Add Album for center
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Intent.EXTRA_ALLOW_MULTIPLE,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "اختر البوم صور"),multiImage);
            }
        });
    }


    //back from activity
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //back from gouvertnate with cityid and gouvernate id
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                city_id = data.getStringExtra("city_id");
                gouvernate_id = data.getStringExtra("gouvernate_id");
                String city_name = data.getStringExtra("city_name");
                String gouvernate_name = data.getStringExtra("gouvernate_name");
                city.setText(gouvernate_name+","+city_name);
            }
        }
          //upload main image
       else if (resultCode == RESULT_OK) {
            if (requestCode == 2) {
                Uri selectedImage = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                SelectedPhoto = BitmapFactory.decodeStream(imageStream);
                Bitmap bitmap = Bitmap.createScaledBitmap(SelectedPhoto, 300, 300, true);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,70,os);
               byte[] bs = os.toByteArray();
                Imagez= Base64.encodeToString(bs,Base64.DEFAULT);
                Log.e("erroorr",Imagez);
                uploadedimg.setImageBitmap(bitmap);
                uploadedimg.setPadding(0,0,0,0);
            } }

            //upload multi images
        if (resultCode == RESULT_OK) {
            if (requestCode == multiImage) {
                ClipData clipData=data.getClipData();
                Album=new ArrayList<String>();
                if (clipData==null)
                {
                    Toast.makeText(this, "من فضلك اختر اكتر من صوره", Toast.LENGTH_LONG).show();

                }
                else if(clipData!=null)
                {

                    for (int index=0;index<clipData.getItemCount();index++) {
                        ClipData.Item item = clipData.getItemAt(index);
                        Uri uri = item.getUri();
                        try {
                            InputStream imageStreams = getContentResolver().openInputStream(uri);
                            Bitmap SelectedPhotos = BitmapFactory.decodeStream(imageStreams);
                            Bitmap bitmap = Bitmap.createScaledBitmap(SelectedPhotos, 300, 300, true);
                            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, os);
                            byte[] bs = os.toByteArray();
                            String Image = Base64.encodeToString(bs, Base64.DEFAULT);
                            Album.add(Image);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                   ImageView addImg=(ImageView)findViewById(R.id.addImg);
                    addImg.setImageResource(R.drawable.addsuccessfull);
                 }}}
    //get current location
        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                cr_lat = data.getStringExtra("lat");
                cr_lng = data.getStringExtra("lng");
                Toast.makeText(this, ""+cr_lat+"/"+cr_lng, Toast.LENGTH_SHORT).show();
                goToMap.setText(""+cr_lat+"/"+cr_lng);
            }
        }
    }


    public void getloca() {
        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

        }
    }

    private void getDeviceLocation() {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {

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

                            Log.e("sdrfrfrf",""+current_lat+current_lng);
                }else{
                            Toast.makeText(addNewCenter.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Toast.makeText(this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

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
                    //initialize our map
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getDeviceLocation();
        getloca();
        }



    //add New Center
    private void addCenter() {
          final ArrayList<String>name=new ArrayList<String>();
        final Button addCenter=(Button)findViewById(R.id.addCenter);
        name.add("aaa");
        addCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validate elements
                if(Imagez.equals("0"))
                {
                    Toast.makeText(addNewCenter.this, "من فضلك ارفع صورة المركز", Toast.LENGTH_SHORT).show();

                }
                 else if(centerName.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل اسم المركز", Toast.LENGTH_SHORT).show();
                  }
                  else if(descripition.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل الوصف", Toast.LENGTH_SHORT).show();

                  }
                  else if(email.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل البريد الالكترونى", Toast.LENGTH_SHORT).show();
                      }
                      else if(phone.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل الهاتف ", Toast.LENGTH_SHORT).show();

                      }
                  else if(address.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل العنوان ", Toast.LENGTH_SHORT).show();

                  }
                  else if(goToMap.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل احداثيات الموقع", Toast.LENGTH_SHORT).show();
                  }
                  else if(timework.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل مواقت العمل", Toast.LENGTH_SHORT).show();
                  }
                  else if(workName.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل اسم المالك", Toast.LENGTH_SHORT).show();
                  }
                  else if(discount.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل الخصم", Toast.LENGTH_SHORT).show();
                  }
                  else if(holidays.getText().toString().equals(""))
                  {
                      Toast.makeText(addNewCenter.this, "من فضلك ادخل ايام الاجازه", Toast.LENGTH_SHORT).show();
                  }

                  else {
                      loading loading=new loading();
                      loading.loadingDialog(addNewCenter.this,R.layout.loading,.80);
                addCenterPresenter =new addCenterPresenter(addNewCenter.this,addNewCenter.this,centerName,descripition,email,phone,address,timework,null,workName,gouvernate_id,city_id,cr_lat,cr_lng,Imagez,new savedId().retrieveBrands(addNewCenter.this),new savedId().retrieveServices(addNewCenter.this),Album,discount,holidays);
                addCenterPresenter.getData();

            }}
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
}
