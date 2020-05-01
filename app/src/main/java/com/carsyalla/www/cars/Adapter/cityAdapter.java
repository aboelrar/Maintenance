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

import com.carsyalla.www.cars.Model.cityall;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.allCity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class cityAdapter extends RecyclerView.Adapter<cityAdapter.cityHolder> {
    ArrayList<cityall>mylist;
    Context context;
    String gouvernate_id,gouvernate_name;

    public cityAdapter(ArrayList<cityall> mylist, Context context,String gouvernate_id,String gouvernate_name) {
        this.mylist = mylist;
        this.context = context;
        this.gouvernate_id=gouvernate_id;
        this.gouvernate_name=gouvernate_name;
    }

    @NonNull
    @Override
    public cityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.city_item,viewGroup,false);
        cityHolder cityHolder=new cityHolder(view);
        return cityHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull cityHolder viewHolder, final int i) {
            viewHolder.cityTitle.setText(mylist.get(i).getTitle().toString());
            viewHolder.city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("gouvernate_id", gouvernate_id);
                    intent.putExtra("city_id",mylist.get(i).getId().toString());
                    intent.putExtra("city_name",mylist.get(i).getTitle().toString());
                    intent.putExtra("gouvernate_name",gouvernate_name);
                    ((allCity)context).setResult(RESULT_OK, intent);
                    ((allCity)context).finish();
                }
            });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class cityHolder extends RecyclerView.ViewHolder {
        TextView cityTitle;
        LinearLayout city;
        public cityHolder(@NonNull View itemView) {
            super(itemView);
            cityTitle=(TextView)itemView.findViewById(R.id.cityTitle);
            city=(LinearLayout)itemView.findViewById(R.id.city);
        }
    }
}
