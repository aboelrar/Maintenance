package com.carsyalla.www.cars.Adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carsyalla.www.cars.Model.brand;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.allCenters;
import com.carsyalla.www.services.allservices;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.util.ArrayList;

import static android.content.Context.ACTIVITY_SERVICE;

public class brandAdapter extends RecyclerView.Adapter<brandAdapter.brandHolder> {
    Context context;
    ArrayList<brand> mylist;
    int selectedPosition = -1;

    public brandAdapter(Context context, ArrayList<brand> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public brandHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, viewGroup, false);
        brandHolder brandHolder = new brandHolder(view);
        return brandHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final brandHolder viewHolder, final int i) {
        Picasso.with(context).load(mylist.get(i).getIamge())
                .into(viewHolder.BrandIcon);
        viewHolder.brandName.setText(mylist.get(i).getName().toString());
        viewHolder.centersNum.setText(mylist.get(i).getCentersNum().toString());
        viewHolder.brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                selectedPosition = i;
                notifyDataSetChanged();
                final Intent intent = new Intent(context, allservices.class);
                intent.putExtra("brand_id", mylist.get(i).getId());
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class brandHolder extends RecyclerView.ViewHolder {
        TextView brandName, centersNum;
        ImageView BrandIcon;
        LinearLayout brand;

        public brandHolder(@NonNull View itemView) {
            super(itemView);
            brandName = (TextView) itemView.findViewById(R.id.brandName);
            BrandIcon = (ImageView) itemView.findViewById(R.id.brandicon);
            centersNum = (TextView) itemView.findViewById(R.id.centernum);
            brand = (LinearLayout) itemView.findViewById(R.id.brand);
        }
    }


}
