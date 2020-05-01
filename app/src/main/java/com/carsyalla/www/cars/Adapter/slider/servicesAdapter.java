package com.carsyalla.www.cars.Adapter.slider;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carsyalla.www.cars.Model.serviceall;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.all_service;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class servicesAdapter extends RecyclerView.Adapter<servicesAdapter.serviceHolder> {
    ArrayList<serviceall>mylist;
    Context context;

    public servicesAdapter(ArrayList<serviceall> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public serviceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.city_item,viewGroup,false);
        serviceHolder serviceHolder=new serviceHolder(view);
        return serviceHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull serviceHolder viewHolder, final int i) {
            viewHolder.serviceTitle.setText(mylist.get(i).getTitle().toString());
            viewHolder.service.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                     intent.putExtra("service_name",mylist.get(i).getTitle().toString());
                    intent.putExtra("service_id",mylist.get(i).getId().toString());
                    ((all_service)context).setResult(RESULT_OK, intent);
                    ((all_service)context).finish();
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
        public serviceHolder(@NonNull View itemView) {
            super(itemView);
            serviceTitle=(TextView)itemView.findViewById(R.id.cityTitle);
            service=(LinearLayout)itemView.findViewById(R.id.city);
        }
    }
}
