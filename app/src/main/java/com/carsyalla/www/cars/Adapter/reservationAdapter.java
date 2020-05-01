package com.carsyalla.www.cars.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.cars.Model.reservation;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.centerDetails;
import com.carsyalla.www.cars.interfaces.addRate;
import com.carsyalla.www.cars.interfaces.cancel_reservation;
import com.carsyalla.www.cars.interfaces.centers;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.my_reservations;
import com.carsyalla.www.cars.network.apis;
import com.carsyalla.www.cars.presenter.cancel_reservationPresenter;
import com.carsyalla.www.cars.presenter.centerPresenter;
import com.carsyalla.www.cars.rating;
import com.carsyalla.www.cars.view_reservation_location;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class reservationAdapter extends RecyclerView.Adapter<reservationAdapter.centerHolder> implements addRate.interfaces.View, centers.interfaces.View {
    Context context;
    ArrayList<reservation> mylist;
    com.carsyalla.www.cars.presenter.favouritePresenter favouritePresenter;
    addRate.interfaces.View view;
    centers.interfaces.View views;
    cancel_reservation.interfaces.View cancel_view;
    com.carsyalla.www.cars.presenter.centerPresenter centerPresenter;
    apis apis=new apis();
    static RecyclerView iconsSer;
    public reservationAdapter(Context context, ArrayList<reservation> mylist, addRate.interfaces.View view,cancel_reservation.interfaces.View cancel_view) {
        this.context = context;
        this.mylist = mylist;
        this.cancel_view=cancel_view;
        this.view=view;
    }

    @NonNull
    @Override
    public centerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.my_reservation_item,viewGroup,false);
        reservationAdapter.centerHolder centerHolder=new reservationAdapter.centerHolder(view);
        return centerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final reservationAdapter.centerHolder viewHolder, final int i) {
        Picasso.with(context).load(mylist.get(i).getImage()).into(viewHolder.centerImage);
        viewHolder.centerName.setText(mylist.get(i).getName().toString());
        viewHolder.centersAddress.setText(mylist.get(i).getAddress().toString());
        viewHolder.reservationTime.setText(mylist.get(i).getReservatioDate().toString());

        if(mylist.get(i).getCanCancel().equals("1"))
        {
            viewHolder.stateIcon.setImageResource(R.drawable.crosscolor);
            viewHolder.state.setText("الغاء");
            //Delete
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    // set dialog message
                    alertDialogBuilder
                            .setMessage("هل انت متأكد من الغاء الحجز؟")
                            .setCancelable(false)
                            .setPositiveButton("نعم",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    cancel_reservationPresenter cancel_reservationPresenter=new cancel_reservationPresenter(cancel_view,context,mylist.get(i).getId().toString(),"2");
                                    cancel_reservationPresenter.getData();
                                    Intent intent=((my_reservations)context).getIntent();
                                    ((my_reservations)context).finish();
                                    v.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton("لا",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // show it
                    alertDialog.show();
                }
            });
        }
        else
        {
            if(mylist.get(i).getCanRate().equals("1"))
            {
                viewHolder.stateIcon.setImageResource(R.drawable.starcolor);
                viewHolder.state.setText("تقييم");
                //RATE
                viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                 Intent intent=new Intent(context,rating.class);
                 intent.putExtra("reservation_id",mylist.get(i).getId().toString());
                 intent.putExtra("user_name",mylist.get(i).getUserName().toString());
                 intent.putExtra("center_name",mylist.get(i).getCenterName().toString());
                 intent.putExtra("center_id",mylist.get(i).getCenter_id().toString());
                 v.getContext().startActivity(intent);
                    }
                });
            }
            else {
                if(mylist.get(i).getId_reservation_status().equals("1"))
                {
                    viewHolder.stateIcon.setImageResource(R.drawable.complete);
                    viewHolder.state.setText(mylist.get(i).getCurrentStatus().toString());
                }
                else if(mylist.get(i).getId_reservation_status().equals("2"))
                {
                    viewHolder.stateIcon.setImageResource(R.drawable.complete);
                    viewHolder.state.setText(mylist.get(i).getCurrentStatus().toString());
                }
                else if(mylist.get(i).getId_reservation_status().equals("3"))
                {
                    viewHolder.stateIcon.setImageResource(R.drawable.hourglass);
                    viewHolder.state.setText(mylist.get(i).getCurrentStatus().toString());
                }
                else {
                    Toast.makeText(context, ""+mylist.get(i).getCurrentStatus().toString(), Toast.LENGTH_SHORT).show();
                }
            }}

         //Go To Map
        viewHolder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,view_reservation_location.class);
                 intent.putExtra("latitude",mylist.get(i).getLatitude());
                 intent.putExtra("longitude",mylist.get(i).getLontiude());
                 v.getContext().startActivity(intent);
            }
        });
        //Go To Deatils
        viewHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,centerDetails.class);
                intent.putExtra("center_id",mylist.get(i).getCenter_id().toString());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class centerHolder extends RecyclerView.ViewHolder {
        TextView centerName,centersAddress,reservationTime;
        ImageView centerImage,stateIcon;
        LinearLayout delete,map,linear;
        TextView state;
        public centerHolder(@NonNull View itemView) {
            super(itemView);
            linear=(LinearLayout)itemView.findViewById(R.id.linear);
            centerName=(TextView)itemView.findViewById(R.id.resrvationName);
            centerImage=(ImageView)itemView.findViewById(R.id.reservationImg);
            centersAddress=(TextView)itemView.findViewById(R.id.address);
            reservationTime=(TextView)itemView.findViewById(R.id.reservationTime);
            delete=(LinearLayout)itemView.findViewById(R.id.delete);
            map=(LinearLayout)itemView.findViewById(R.id.map);
            state=(TextView)itemView.findViewById(R.id.state);
            stateIcon=(ImageView)itemView.findViewById(R.id.stateIcon);
        }
    }
    public void centerPresenter(centers.interfaces.View view, Context context, RecyclerView recyclerView, RecyclerView recyclerView1, String id, favourite.interfaces.View view1, ImageView ads, int pageNum)
    {
        centerPresenter =new centerPresenter(view,context,recyclerView,recyclerView1,id,view1,ads,iconsSer,pageNum,null,null);
        centerPresenter.getData();
        centerPresenter.getData2();
    }

}
