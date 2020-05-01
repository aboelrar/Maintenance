package com.carsyalla.www.cars.Adapter;

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

import com.carsyalla.www.cars.Model.stolenCar;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.stolen_car_details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class stolenCarAdapter extends RecyclerView.Adapter <stolenCarAdapter.stolenHolder>{
    ArrayList<stolenCar>mylist;
    Context context;

    public stolenCarAdapter(ArrayList<stolenCar> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public stolenHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.stolencar_item,viewGroup,false);
        stolenHolder stolenHolder=new stolenHolder(view);
        return stolenHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull stolenHolder viewHolder,final int i) {
        Picasso.with(context).load(mylist.get(i).getCarImg()).into(viewHolder.carImg);
        viewHolder.carModel.setText(mylist.get(i).getCarModel().toString());
        viewHolder.plate_number.setText(mylist.get(i).getPalteNumber().toString());
        viewHolder.brand.setText(mylist.get(i).getBrand().toString());
        viewHolder.yearmodel.setText(mylist.get(i).getYearModel().toString());
        viewHolder.serial.setText(mylist.get(i).getSerial().toString());
        viewHolder.color.setText(mylist.get(i).getColor().toString());
        viewHolder.address.setText(mylist.get(i).getAddress().toString());
        viewHolder.from.setText(mylist.get(i).getPhone().toString());
        viewHolder.stolenCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,stolen_car_details.class);
                intent.putExtra("stolen_cat_id",mylist.get(i).getId().toString());
                v.getContext().startActivity(intent);
            }
        });
        }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class stolenHolder extends RecyclerView.ViewHolder {
        ImageView carImg;
        TextView carModel,plate_number,brand,yearmodel,serial,color,name,from,address;
        LinearLayout stolenCar;
        public stolenHolder(@NonNull View itemView) {
            super(itemView);
            carImg=(ImageView)itemView.findViewById(R.id.carImg);
            carModel=(TextView)itemView.findViewById(R.id.carModel);
            plate_number=(TextView)itemView.findViewById(R.id.plate_number);
            brand=(TextView)itemView.findViewById(R.id.brand);
            yearmodel=(TextView)itemView.findViewById(R.id.yearmodel);
            serial=(TextView)itemView.findViewById(R.id.serial);
            color=(TextView)itemView.findViewById(R.id.color);
            name=(TextView)itemView.findViewById(R.id.name);
            from=(TextView)itemView.findViewById(R.id.from);
            stolenCar=(LinearLayout)itemView.findViewById(R.id.stolenCar);
            address=(TextView)itemView.findViewById(R.id.address);
        }
    }
}
