package com.carsyalla.www.cars.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.interfaces.checkList;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.checkPresenter;

public class checkDialog implements checkList.interfaces.View {
    Context context;
    com.carsyalla.www.cars.presenter.checkPresenter checkPresenter;
    Button add;

    public void searchDialog(final Context context, int resource, double widthh, checkList.interfaces.View view, final TextView service)
    {
        this.context=context;
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        RecyclerView services=(RecyclerView)dialog.findViewById(R.id.checkList);
        checkPresenter=new checkPresenter(view,context,services);
        checkPresenter.getData();
   //click Add To Add service To Server
        add=(Button)dialog.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedId savedId=new savedId();
                String services=""+savedId.retrieveServicesTitle(context);
                Toast.makeText(context, ""+ services, Toast.LENGTH_SHORT).show();
                services=services.replace("[", "");
                services=services.replace("]", "");
                service.setText(services);
                dialog.dismiss();
            }
        });
        dialog.show();
        }

    public void brandDialog(final Context context, int resource, double widthh, checkList.interfaces.View view, final TextView brand)
    {
        this.context=context;
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        RecyclerView services=(RecyclerView)dialog.findViewById(R.id.checkList);
        checkPresenter=new checkPresenter(view,context,services);
        checkPresenter.getSecondData();
        add=(Button)dialog.findViewById(R.id.add);
        TextView title=(TextView)dialog.findViewById(R.id.title);
        title.setText("الماركات");
 //click Add To Add brands To Server
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedId savedId=new savedId();
                String brands=""+savedId.retrieveBrandsTitle(context);
                Toast.makeText(context, ""+brands, Toast.LENGTH_SHORT).show();
                brands=brands.replace("[", "");
                brands=brands.replace("]", "");
                brand.setText(brands);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
