package com.carsyalla.www.cars.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.carsyalla.www.cars.ui.seachCenter;
import com.carsyalla.www.cars.ui.searchReportedCars;
import com.carsyalla.www.cars.ui.searchStolenCars;

import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    private List<Fragment> myFragments;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                searchReportedCars searchStolenCars =new searchReportedCars();
                return searchStolenCars;
            case 1:
                com.carsyalla.www.cars.ui.searchStolenCars usedCar=new searchStolenCars();
                return usedCar;
            case 2:
            seachCenter seachCenter = new seachCenter();
            return seachCenter;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}