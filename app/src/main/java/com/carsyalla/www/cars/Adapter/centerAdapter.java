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
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.cars.LoginAndRegist;
import com.carsyalla.www.cars.Model.center;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.centerDetails;
import com.carsyalla.www.cars.interfaces.centers;
import com.carsyalla.www.cars.interfaces.favourite;
import com.carsyalla.www.cars.library.adapter;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.network.apis;
import com.carsyalla.www.cars.presenter.centerPresenter;
import com.carsyalla.www.cars.presenter.favouritePresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class centerAdapter extends RecyclerView.Adapter<centerAdapter.centerHolder> implements favourite.interfaces.View, centers.interfaces.View {
    Context context;
    ArrayList<center> mylist;
    com.carsyalla.www.cars.presenter.favouritePresenter favouritePresenter;
    favourite.interfaces.View view;
    centers.interfaces.View views;
    String service_id;
    com.carsyalla.www.cars.presenter.centerPresenter centerPresenter;
    ProgressBar loading;
    apis apis=new apis();
    static RecyclerView iconsSer;
    public centerAdapter(Context context, ArrayList<center> mylist, favourite.interfaces.View view, ProgressBar loading,String service_id) {
        this.context = context;
        this.mylist = mylist;
        this.loading=loading;
        this.service_id=service_id;
        this.view=view;
    }

    @NonNull
    @Override
    public centerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.maintanceitem,viewGroup,false);
        centerAdapter.centerHolder centerHolder=new centerAdapter.centerHolder(view);
        return centerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final centerAdapter.centerHolder viewHolder, final int i) {
        Picasso.with(context).load(mylist.get(i).getImage()).into(viewHolder.centerImage);
        viewHolder.centerName.setText(mylist.get(i).getName().toString());
        viewHolder.ratings.setRating(mylist.get(i).getRating());
        viewHolder.centersAddress.setText(mylist.get(i).getAddress().toString());
        viewHolder.discount.setText(mylist.get(i).getDiscount().toString());

    /*    RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(context);
        viewHolder.icons.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter=new iconAdapter(context,mylist.get(i).getMylist());
        viewHolder.icons.setAdapter(adapter);*/
        adapter adapter1=new adapter();
        adapter1.Horozintal(viewHolder.icons,new iconAdapter(context,mylist.get(i).getMylist()),context);
        viewHolder.center_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,centerDetails.class);
                intent.putExtra("center_id",mylist.get(i).getId().toString());
                v.getContext().startActivity(intent);
                }
        });

        //like to all item that clicked on
        if(mylist.get(i).getFavNum()==1) {
            viewHolder.favourite.setImageResource(R.drawable.redfav);
            mylist.get(i).setFav(true);
        }
        //save to fav
        viewHolder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(new savedId().getUserBoolean(context)==false)
                {
                    Intent intent=new Intent(context, LoginAndRegist.class);
                    intent.putExtra("res","allcenters");
                    context.startActivity(intent);
                    Toast.makeText(context, "يرجى تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                }
                else {
                if(mylist.get(i).isFav()==false) {
                    viewHolder.favourite.setImageResource(R.drawable.redfav);
                    favouritePresenter=new favouritePresenter(view,context,apis.addFavoriteCenter,mylist.get(i).getId().toString());
                    favouritePresenter.getData();
                    mylist.get(i).setFav(true);
                }
                else{
                    viewHolder.favourite.setImageResource(R.drawable.favourite);
                    mylist.get(i).setFav(false);
                    favouritePresenter=new favouritePresenter(view,context,apis.deleteFavouriteCenter,mylist.get(i).getId().toString());
                    favouritePresenter.getData();
                }}
            }
        });
        if(mylist.get(i).getDiscount().equals("0"))
        {
            ViewGroup.LayoutParams params = viewHolder.discountitem.getLayoutParams();
            params.height = 0;
            params.width = 0;
            viewHolder.discountitem.setLayoutParams(params);
        }
        if(mylist.get(i).getSpecial().equals("2"))
        {
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) viewHolder.special.getLayoutParams();
            params.height = 0;
            params.width=0;
            viewHolder.special.setLayoutParams(params);
        }

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class centerHolder extends RecyclerView.ViewHolder {
        TextView centerName,centersAddress;
        ImageView centerImage,favourite;
        LinearLayout center_item;
        RatingBar ratings;
        RecyclerView icons;
        TextView discount;
        LinearLayout discountitem;
        TextView special;
        public centerHolder(@NonNull View itemView) {
            super(itemView);
            centerName=(TextView)itemView.findViewById(R.id.centerName);
            centerImage=(ImageView)itemView.findViewById(R.id.imagecenter);
            centersAddress=(TextView)itemView.findViewById(R.id.address);
            center_item=(LinearLayout)itemView.findViewById(R.id.center_item);
            favourite=(ImageView)itemView.findViewById(R.id.fav);
            icons=(RecyclerView)itemView.findViewById(R.id.icons);
            ratings=(RatingBar)itemView.findViewById(R.id.ratings);
            discount=(TextView)itemView.findViewById(R.id.discount);
            discountitem=(LinearLayout)itemView.findViewById(R.id.discountitem);
            special=(TextView)itemView.findViewById(R.id.special);
        }
    }
    public void centerPresenter(centers.interfaces.View view, Context context, RecyclerView recyclerView,RecyclerView recyclerView1,String id,favourite.interfaces.View view1,ImageView ads,int pageNum)
    {
        centerPresenter =new centerPresenter(view,context,recyclerView,recyclerView1,id,view1,ads,iconsSer,pageNum,loading,service_id);
        centerPresenter.getData();
        centerPresenter.getData2();
    }

    public void addList(ArrayList<center> mylists)
    {
        for(center list:mylists)
        {
            mylist.add(list);
            notifyItemInserted(mylist.size()-1);
        }
    }

}
