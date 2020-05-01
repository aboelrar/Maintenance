package com.carsyalla.www.cars.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

public class loading {
    static Dialog dialog;
    public void loadingDialog(final Context context, int resource, double widthh)
    {
        dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();
    }

    public void dismissDialog()
    {
        dialog.dismiss();
    }

}
