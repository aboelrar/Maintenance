package com.carsyalla.www.cars.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carsyalla.www.cars.Model.modelsall;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.reservation_models;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class reservationModelAdapter extends RecyclerView.Adapter<reservationModelAdapter.reservationBrandsHolder> {
    ArrayList<modelsall>mylist;
    Context context;
    String brand_id;
    public reservationModelAdapter(ArrayList<modelsall> mylist, Context context,String brand_id) {
        this.mylist = mylist;
        this.context = context;
        this.brand_id=brand_id;
    }

    @NonNull
    @Override
    public reservationBrandsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.city_item,viewGroup,false);
        reservationBrandsHolder reservationBrandsHolder=new reservationBrandsHolder(view);
        return reservationBrandsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull reservationBrandsHolder viewHolder, final int i) {
            viewHolder.brandTitle.setText(mylist.get(i).getTitle().toString());
            viewHolder.brand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("model_id", mylist.get(i).getId().toString());
                    intent.putExtra("brand_id", brand_id);
                    intent.putExtra("model_name",mylist.get(i).getTitle().toString());
                    ((reservation_models)context).setResult(RESULT_OK, intent);
                    ((reservation_models)context).finish();
                }
            });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class reservationBrandsHolder extends RecyclerView.ViewHolder {
        TextView brandTitle;
        LinearLayout brand;
        public reservationBrandsHolder(@NonNull View itemView) {
            super(itemView);
            brandTitle=(TextView)itemView.findViewById(R.id.cityTitle);
            brand=(LinearLayout)itemView.findViewById(R.id.city);
        }
    }
}
