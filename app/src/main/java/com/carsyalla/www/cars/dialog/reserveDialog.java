package com.carsyalla.www.cars.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.centerDetails;
import com.carsyalla.www.cars.interfaces.brand_reservation;
import com.carsyalla.www.cars.interfaces.reservation;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.reservationPresenter;
import com.carsyalla.www.cars.reservation_brands;

import java.util.Calendar;

public class reserveDialog implements reservation.interfaces.View, brand_reservation.interfaces.View {
    com.carsyalla.www.cars.library.savedId savedId=new savedId();
    com.carsyalla.www.cars.presenter.brand_reservationPresenter brand_reservationPresenter;
    com.carsyalla.www.cars.presenter.reservationPresenter reservationPresenter;
    String brand_id,model_id,model_name=null;
    static TextView mark,reservationDate;
    static  EditText manufacturingyear;
    String center_id;
    int year;
    public void brand(String brand_id,String model_id,String center_id)
    {
        this.brand_id=brand_id;
        this.model_id=model_id;
        this.model_name=model_name;
        this.center_id=center_id;
    }

    public void reserve(final Context context, int resource, double widthh, final reservation.interfaces.View view, brand_reservation.interfaces.View view1)
    {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        final EditText username=(EditText)dialog.findViewById(R.id.username);
        final EditText phone=(EditText)dialog.findViewById(R.id.phone);
        mark=(TextView)dialog.findViewById(R.id.mark);
        manufacturingyear =(EditText)dialog.findViewById(R.id.manufacturingyear);
         reservationDate=(TextView)dialog.findViewById(R.id.reservationDate);
        Button reserve=(Button)dialog.findViewById(R.id.reserve);

        //get reservation date
        reservationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDatePickerDiolag(context);
            }
        });

        //go to brands
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,reservation_brands.class);
                ((centerDetails)context).startActivityForResult(intent, 1);
                mark.setText(model_name);
            }
        });

        //send data to
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals(""))
                {
                    Toast.makeText(context, "من فضلك ادخل الاسم", Toast.LENGTH_SHORT).show();
                }
                else if(phone.getText().toString().equals(""))
                {
                    Toast.makeText(context, "من فضلك ادخل رقم الهاتف", Toast.LENGTH_SHORT).show();
                }
                else if(model_id==null)
                {
                    Toast.makeText(context, "من فضلك اختر الماركه", Toast.LENGTH_SHORT).show();
                }

                else if(manufacturingyear.getText().toString().equals(""))
                {
                    Toast.makeText(context, "من فضلك ادخل سنة الصنع", Toast.LENGTH_SHORT).show();
                }
                else if(reservationDate.getText().toString().equals(""))
                {
                    Toast.makeText(context, "من فضلك ادخل تاريخ الحجز", Toast.LENGTH_SHORT).show();
                }
                else {

                    loading loading=new loading();
                    loading.loadingDialog(context,R.layout.loading,.80);
                    savedId savedId = new savedId();
                    reservationPresenter = new reservationPresenter(view, context, username, phone, manufacturingyear, reservationDate, brand_id, model_id, savedId.getId(context), center_id, dialog);
                    reservationPresenter.getData();
                }
            }
        });

        dialog.show();
    }

    //set model name
    public void setModel_name(String model_name)
    {
        mark.setText(model_name);
    }

    //set date
    private void createDatePickerDiolag(Context context) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener listner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                reservationDate.setText(""+year +"-"+ (month+1) + "-" + dayOfMonth );
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(context, listner, mYear, mMonth, mDay);
        dialog.show();
    }

}
