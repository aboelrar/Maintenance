package com.carsyalla.www.cars.library;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.carsyalla.www.R;


public class onClick extends Activity{
    public void search_click(final Context context)
    {
        ImageView search=(ImageView)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "wdmodwom", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
