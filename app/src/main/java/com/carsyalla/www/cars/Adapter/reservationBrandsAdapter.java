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

import com.carsyalla.www.cars.Model.brandsall;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.reservation_brands;
import com.carsyalla.www.cars.reservation_models;

import java.util.ArrayList;

public class reservationBrandsAdapter extends RecyclerView.Adapter<reservationBrandsAdapter.reservationBrandsHolder> {
    ArrayList<brandsall>mylist;
    Context context;

    public reservationBrandsAdapter(ArrayList<brandsall> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public reservationBrandsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.gouvernate_item,viewGroup,false);
        reservationBrandsHolder reservationBrandsHolder=new reservationBrandsHolder(view);
        return reservationBrandsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull reservationBrandsHolder viewHolder, final int i) {
            viewHolder.brandTitle.setText(mylist.get(i).getTitle().toString());
            viewHolder.brand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,reservation_models.class);
                    intent.putExtra("brand_id", mylist.get(i).getId().toString());
                    ((reservation_brands)context).startActivityForResult(intent,1);
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
            brandTitle=(TextView)itemView.findViewById(R.id.gouverrment);
            brand=(LinearLayout)itemView.findViewById(R.id.gouvernate);
        }
    }
}
