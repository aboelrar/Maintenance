package com.carsyalla.www.cars.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.carsyalla.www.cars.Model.icons;
import com.carsyalla.www.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class iconAdapter extends RecyclerView.Adapter<iconAdapter.iconHolder> {
    Context context;
    ArrayList<icons>mylist;

    public iconAdapter(Context context, ArrayList<icons> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public iconHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.icon_tiem,viewGroup,false);
        iconHolder iconHolder=new iconHolder(view);
        return iconHolder;
    }

    @Override
    public void onBindViewHolder(iconHolder viewHolder, int i) {
        Picasso.with(context).load(mylist.get(i).getIcons()).into(viewHolder.icon);
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class iconHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        public iconHolder(@NonNull View itemView) {
            super(itemView);
            icon=(ImageView)itemView.findViewById(R.id.icon_ser);
        }
    }
}
