package com.carsyalla.www.cars.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.interfaces.addRate;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.addRatePresenter;

public class rateDialog implements addRate.interfaces.View {
    addRatePresenter addRatePresenter;
    com.carsyalla.www.cars.library.savedId savedId=new savedId();
    public void add_rate(final Context context, int resource, double widthh, final addRate.interfaces.View views, final String center_id)
    {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        RatingBar ratingBar=(RatingBar)dialog.findViewById(R.id.rating);
        final Button rate=(Button)dialog.findViewById(R.id.rate);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, final float rating, boolean fromUser) {
                TextView value=(TextView)dialog.findViewById(R.id.value);
                value.setText(""+rating);
                rate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int rate_num=Math.round(rating);
                        addRatePresenter=new addRatePresenter(views,context,savedId.getId(context),center_id,rate_num);
                        addRatePresenter.getData();
                        dialog.dismiss();

                    }
                });
            }
        });
        dialog.show();

    }
}
