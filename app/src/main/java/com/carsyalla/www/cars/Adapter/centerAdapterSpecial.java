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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.cars.LoginAndRegist;
import com.carsyalla.www.cars.Model.center;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.centerDetails;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.network.apis;
import com.carsyalla.www.cars.presenter.favouritePresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class centerAdapterSpecial extends RecyclerView.Adapter<centerAdapterSpecial.centerHolder> implements favourite.interfaces.View {
    Context context;
    ArrayList<center> mylist;
    com.carsyalla.www.cars.network.apis apis = new apis();
    com.carsyalla.www.cars.presenter.favouritePresenter favouritePresenter;
    favourite.interfaces.View view;

    public centerAdapterSpecial(Context context, ArrayList<center> mylist, favourite.interfaces.View view) {
        this.context = context;
        this.mylist = mylist;
        this.view = view;
    }


    @NonNull
    @Override
    public centerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.center_item_special, viewGroup, false);
        centerAdapterSpecial.centerHolder centerHolder = new centerAdapterSpecial.centerHolder(view);
        return centerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final centerAdapterSpecial.centerHolder viewHolder, final int i) {
        Picasso.with(context).load(mylist.get(i).getImage()).into(viewHolder.centerImage);
        viewHolder.centerName.setText(mylist.get(i).getName().toString());
        viewHolder.ratings.setRating(mylist.get(i).getRating());
        viewHolder.centersAddress.setText(mylist.get(i).getAddress().toString());
        viewHolder.discount.setText(mylist.get(i).getDiscount().toString());
        adapter adapter1 = new adapter();
        adapter1.Horozintal(viewHolder.icons, new iconAdapter(context, mylist.get(i).getMylist()), context);
        //send id to show centers
        viewHolder.brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, centerDetails.class);
                intent.putExtra("center_id", mylist.get(i).getId().toString());
                v.getContext().startActivity(intent);
            }
        });

        //like to all item that clicked on
        if (mylist.get(i).getFavNum() == 1) {
            viewHolder.favourite.setImageResource(R.drawable.redfav);
            mylist.get(i).setFav(true);
        }

        //save to fav
        viewHolder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new savedId().getUserBoolean(context) == false) {
                    Intent intent = new Intent(context, LoginAndRegist.class);
                    intent.putExtra("res", "allcenters");
                    context.startActivity(intent);
                    Toast.makeText(context, "يرجى تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                } else {
                    if (mylist.get(i).isFav() == false) {
                        viewHolder.favourite.setImageResource(R.drawable.redfav);
                        favouritePresenter = new favouritePresenter(view, context, apis.addFavoriteCenter, mylist.get(i).getId().toString());
                        favouritePresenter.getData();
                        mylist.get(i).setFav(true);
                    } else {
                        viewHolder.favourite.setImageResource(R.drawable.favourite);
                        mylist.get(i).setFav(false);
                        favouritePresenter = new favouritePresenter(view, context, apis.deleteFavouriteCenter, mylist.get(i).getId().toString());
                        favouritePresenter.getData();
                    }
                }
            }
        });
        if (mylist.get(i).getDiscount().equals("0")) {
            ViewGroup.LayoutParams params = viewHolder.discountitem.getLayoutParams();
            params.height = 0;
            params.width = 0;
            viewHolder.discountitem.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class centerHolder extends RecyclerView.ViewHolder {
        TextView centerName, centersAddress;
        ImageView centerImage;
        LinearLayout brand;
        ImageView favourite;
        RatingBar ratings;
        RecyclerView icons;
        TextView discount;
        LinearLayout discountitem;

        public centerHolder(@NonNull View itemView) {
            super(itemView);
            centerName = (TextView) itemView.findViewById(R.id.centerName);
            centerImage = (ImageView) itemView.findViewById(R.id.imagecenter);
            centersAddress = (TextView) itemView.findViewById(R.id.address);
            brand = (LinearLayout) itemView.findViewById(R.id.brand);
            favourite = (ImageView) itemView.findViewById(R.id.fav);
            ratings = (RatingBar) itemView.findViewById(R.id.ratings);
            icons = (RecyclerView) itemView.findViewById(R.id.icons);
            discount = (TextView) itemView.findViewById(R.id.discount);
            discountitem = (LinearLayout) itemView.findViewById(R.id.discountitem);


        }
    }
}
