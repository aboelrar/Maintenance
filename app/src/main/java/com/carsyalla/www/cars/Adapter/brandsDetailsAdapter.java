package com.carsyalla.www.cars.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carsyalla.www.cars.Model.brandDetails;
import com.carsyalla.www.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class brandsDetailsAdapter  extends RecyclerView.Adapter<brandsDetailsAdapter.bransHolder> {
    Context context;
    ArrayList<brandDetails>mylist;

    public brandsDetailsAdapter(Context context, ArrayList<brandDetails> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public bransHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.service_details_item,viewGroup,false);
        bransHolder bransHolder=new bransHolder(view);
        return bransHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull bransHolder viewHolder, int i) {
         viewHolder.title.setText(mylist.get(i).getName().toString());
        Picasso.with(context).load(mylist.get(i).getIcon()).into(viewHolder.icon);
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class bransHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;
        public bransHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.service);
            icon=(ImageView)itemView.findViewById(R.id.icon);
        }
    }
}
