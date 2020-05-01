package com.carsyalla.www.services;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.Model.serviceall;
import com.carsyalla.www.cars.allCenters;
import com.carsyalla.www.cars.all_service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class allservicesAdapter extends RecyclerView.Adapter<allservicesAdapter.serviceHolder> {
    ArrayList<service_list>mylist;
    Context context;

    public allservicesAdapter(ArrayList<service_list> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public serviceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.service_item,viewGroup,false);
        serviceHolder serviceHolder=new serviceHolder(view);
        return serviceHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull serviceHolder viewHolder, final int i) {
        Picasso.with(context).load(mylist.get(i).getImage()).into(viewHolder.image);
            viewHolder.serviceTitle.setText(mylist.get(i).getTitle().toString());
            viewHolder.centers.setText(mylist.get(i).getCenters().toString());
            viewHolder.service.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, allCenters.class);
                     intent.putExtra("brand_id",mylist.get(i).getBrand_id().toString());
                    intent.putExtra("service_id",mylist.get(i).getId().toString());
                    v.getContext().startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class serviceHolder extends RecyclerView.ViewHolder {
        TextView serviceTitle;
        LinearLayout service;
        ImageView image;
        TextView centers;
        public serviceHolder(@NonNull View itemView) {
            super(itemView);
            serviceTitle=(TextView)itemView.findViewById(R.id.title);
            service=(LinearLayout)itemView.findViewById(R.id.item);
            image=(ImageView)itemView.findViewById(R.id.image);
            centers=(TextView)itemView.findViewById(R.id.centers);
        }
    }
}
