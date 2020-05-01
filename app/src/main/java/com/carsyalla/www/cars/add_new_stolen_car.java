package com.carsyalla.www.cars;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.carsyalla.www.R;
import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.interfaces.add_stolen_car;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.addStolenPresenter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class add_new_stolen_car extends AppCompatActivity implements add_stolen_car.interfaces.View {
static TextView brand,city;
static  String model_id,brand_id,city_id, gouvernate_id,lecienceImg;
static String Imagez="0";
static EditText manufacturingyear,phone,serial,plate_number,color,stolen_address,notes;
static Button addstolencar;
static Bitmap SelectedPhoto,SelectedLiecencePhoto;
static ImageView uplaodImg,addlecienceImg;
static LinearLayout license,album;
static  ArrayList<String>Album=null;
int storage_premission_code=3;
    String plate_type="1";
    int multiImage=5;
    static RadioGroup paltetype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection=new checkConnection();
        if(checkConnection.isOnline(this)==true) {
        setContentView(R.layout.add_new_stolen_car);
        brand=(TextView)findViewById(R.id.mark);
        manufacturingyear=(EditText)findViewById(R.id.manufacturingyear);
        phone=(EditText)findViewById(R.id.phone);
        city=(TextView)findViewById(R.id.city);
        serial=(EditText)findViewById(R.id.serial);
        plate_number=(EditText)findViewById(R.id.plate_number);
        color=(EditText)findViewById(R.id.color);
        stolen_address=(EditText)findViewById(R.id.stolenAddress);
        notes=(EditText)findViewById(R.id.notes);
        addstolencar=(Button)findViewById(R.id.addstolencar);
        uplaodImg=(ImageView)findViewById(R.id.uplaodImg);
        license=(LinearLayout)findViewById(R.id.license);
        album=(LinearLayout) findViewById(R.id.album);
        paltetype=(RadioGroup)findViewById(R.id.paltetype);
        onClick();
        setAddstolencar();
    }
    else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    private void onClick()
    {
        //go to brand to get model
        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(add_new_stolen_car.this,reservation_brands.class);
                startActivityForResult(intent, 1);
            }
        });
        //city
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_new_stolen_car.this, gouvernate_city.class);
                startActivityForResult(i, 2);
            }
        });
        //UPLOAD STOLEN CAR
        uplaodImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "اختر الصوره الرئيسيه"),3);
            }
        });
        //UPLOAD LIENCE CAR
        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "اختر صوره الرخصه"),4);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                brand_id = data.getStringExtra("brand_id");
                model_id = data.getStringExtra("model_id");
                String model_name = data.getStringExtra("model_name");
                brand.setText(model_name);

            }
        }
        //back from gouvertnate with cityid and gouvernate id
        else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                city_id = data.getStringExtra("city_id");
                gouvernate_id = data.getStringExtra("gouvernate_id");
                String city_name = data.getStringExtra("city_name");
                String gouvernate_name = data.getStringExtra("gouvernate_name");
                city.setText(gouvernate_name + "," + city_name);
            }
        }
        //GET STOLEN IMAGE
        else if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
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
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                byte[] bs = os.toByteArray();
                Imagez = Base64.encodeToString(bs, Base64.DEFAULT);
                Log.e("erroorr", Imagez);
                uplaodImg.setImageBitmap(bitmap);
                uplaodImg.setPadding(0,0,0,0);
            }
        }
        //GET LIECENCE IMAGE
        else if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                SelectedLiecencePhoto = BitmapFactory.decodeStream(imageStream);
                Bitmap bitmap = Bitmap.createScaledBitmap(SelectedLiecencePhoto, 300, 300, true);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                byte[] bs = os.toByteArray();
                lecienceImg = Base64.encodeToString(bs, Base64.DEFAULT);
                Log.e("erroorr", lecienceImg);
                Drawable liecenceImg = new BitmapDrawable(getResources(), bitmap);
                license.setBackgroundDrawable(liecenceImg);
                //set image and text after upload multi images
                //Image
                addlecienceImg=(ImageView)findViewById(R.id.addlecienceImg);
                addlecienceImg.setImageResource(R.drawable.checked);
                //Text
                TextView changeText=(TextView)findViewById(R.id.addlecienceText);
                changeText.setText("تمت الاضافه بنجاج");
            }
        }

        //upload multi images
        if (resultCode == RESULT_OK) {
            if (requestCode == multiImage) {
                 ClipData clipData=data.getClipData();
                Album=new ArrayList<String>();
                if (clipData==null)
                {
                    Toast.makeText(this, "Please Select More Than Image", Toast.LENGTH_LONG).show();

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
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
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
    }

    private void grantedOrNot()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(add_new_stolen_car.this).setTitle("Premission To Open Gallery").setMessage("If you need to upload image you must do this premission").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                //positive
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(add_new_stolen_car.this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},storage_premission_code);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent( Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(i, "Select Your Photo"),3);
                }
            }).create().show();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},storage_premission_code);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        if(requestCode==storage_premission_code)
        {
            if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent i = new Intent( Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "Select Your Photo"),3);
            }
            else {
                Toast.makeText(add_new_stolen_car.this, "not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //add Data to stolen car
    private void setAddstolencar()
    {

        paltetype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radio1)
                {
                    plate_type="1";
                }else if(checkedId==R.id.radio0)
                {
                    plate_type="2";
                }
            }
        });

        //add stolen car
        addstolencar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //VALIDATE ELEMENTS
                if(Imagez.equals("0"))
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ارفع صورة السياره", Toast.LENGTH_SHORT).show();

                }
                else if(manufacturingyear.getText().toString().equals(""))
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ادخل سنة الصنع", Toast.LENGTH_SHORT).show();

                }
                else if(phone.getText().toString().equals(""))
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ادخل رقم الهاتف", Toast.LENGTH_SHORT).show();
                }
                else if (serial.getText().toString().equals(""))
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ادخل رقم الشاسيه", Toast.LENGTH_SHORT).show();
                }
                else if(plate_number.getText().toString().equals(""))
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ادخل رقم اللوحات", Toast.LENGTH_SHORT).show();
                }
                else if(color.getText().toString().equals(""))
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ادخل لون السياره", Toast.LENGTH_SHORT).show();
                }
                else if(stolen_address.getText().toString().equals(""))
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ادخل عنوان السرقه", Toast.LENGTH_SHORT).show();
                }
                else if(model_id==null)
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ادخل موديل السياره", Toast.LENGTH_SHORT).show();
                }
                else if(city_id==null)
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ادخل المدينه", Toast.LENGTH_SHORT).show();
                }
                else if(Imagez==null)
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك صوره السياره المسروقه", Toast.LENGTH_SHORT).show();
                }
                else if(lecienceImg==null)
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك ادخل صوره الرخصه", Toast.LENGTH_SHORT).show();
                }
                else if(plate_type==null)
                {
                    Toast.makeText(add_new_stolen_car.this, "من فضلك اختر نوع اللوحه", Toast.LENGTH_SHORT).show();
                }
                else {
                    loading loading=new loading();
                    loading.loadingDialog(add_new_stolen_car.this,R.layout.loading,.80);
                    savedId savedId = new savedId();
                    addStolenPresenter addStolenPresenter = new addStolenPresenter(add_new_stolen_car.this, add_new_stolen_car.this, brand_id, model_id, manufacturingyear.getText().toString(), phone.getText().toString(), city_id, plate_type, serial.getText().toString(), plate_number.getText().toString(), color.getText().toString(), stolen_address.getText().toString(), notes.getText().toString(), Imagez, lecienceImg, Album, savedId.getId(add_new_stolen_car.this));
                    addStolenPresenter.getData();
                }
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
}
