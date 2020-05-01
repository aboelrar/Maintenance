package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.carsyalla.www.cars.Adapter.PagerAdapter;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.library.checkConnection;

public class search extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
            setContentView(R.layout.activity_search);
            tabBar();
        }
        else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    //TabbarLaouts Items
    public void tabBar()
    {
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("سيارات مبلغ عنها"));
        tabLayout.addTab(tabLayout.newTab().setText("سيارات مسروقه"));
        tabLayout.addTab(tabLayout.newTab().setText("مراكز صيانه"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager=(ViewPager)findViewById(R.id.items);
        PagerAdapter pagerAdapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(tabLayout.getTabCount());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                tabLayout.setScrollPosition(i,0,true);
                tabLayout.setSelected(true);
            }


            @Override
            public void onPageSelected(int i) {


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public int getPosition()
    {
      Intent intent=getIntent();
        int positionTap=intent.getIntExtra("position",0);
        return positionTap;
    }

    //refresh page
    public void onclickonconnection()
    {
        Button noConnection=(Button)findViewById(R.id.tryagain);
        noConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=getIntent();
                startActivity(intent);
            }
        });
    }
}
