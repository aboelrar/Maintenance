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

import com.carsyalla.www.cars.Model.gouverment;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.allCity;
import com.carsyalla.www.cars.gouvernate_city;

import java.util.ArrayList;

public class gouvernateAdapter extends RecyclerView.Adapter<gouvernateAdapter.gouvernateHolder> {
    Context context;
    ArrayList<gouverment>mylist;

    public gouvernateAdapter(Context context, ArrayList<gouverment> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public gouvernateHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.gouvernate_item,viewGroup,false);
        gouvernateHolder gouvernateHolder=new gouvernateHolder(view);
        return gouvernateHolder;
    }

    @Override
    public void onBindViewHolder(gouvernateHolder viewHolder, final int i) {
       viewHolder.gouvernateTitle.setText(mylist.get(i).getTitle().toString());
       viewHolder.gouvernate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(context,allCity.class);
               intent.putExtra("gouvernate_id",mylist.get(i).getId().toString());
               intent.putExtra("gouvernate_name",mylist.get(i).getTitle().toString());
               ((gouvernate_city)context).startActivityForResult(intent,1);


           }
       });

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class gouvernateHolder extends RecyclerView.ViewHolder {
        TextView gouvernateTitle;
        LinearLayout gouvernate;
        public gouvernateHolder(@NonNull View itemView) {
            super(itemView);
            gouvernateTitle=(TextView)itemView.findViewById(R.id.gouverrment);
            gouvernate=(LinearLayout)itemView.findViewById(R.id.gouvernate);
        }
    }


}
