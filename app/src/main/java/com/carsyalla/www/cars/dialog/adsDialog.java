package com.carsyalla.www.cars.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.interfaces.popup_ads;
import com.carsyalla.www.cars.presenter.popup_adsPresenter;

public class adsDialog {
    com.carsyalla.www.cars.presenter.popup_adsPresenter popup_adsPresenter;

    public void ads_dialog(Context context,int resource,double widthh, popup_ads.interfaces.View view)
    {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        ImageView cancel=(ImageView)dialog.findViewById(R.id.cancel);
        LinearLayout bg=(LinearLayout)dialog.findViewById(R.id.bg);
        popup_adsPresenter=new popup_adsPresenter(view,context,bg,dialog);
        popup_adsPresenter.getData();

        //CLOSE DIALOG

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
