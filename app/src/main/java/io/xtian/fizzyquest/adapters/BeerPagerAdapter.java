package io.xtian.fizzyquest.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import io.xtian.fizzyquest.models.Beer;
import io.xtian.fizzyquest.ui.BeerDetailFragment;

public class BeerPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Beer> mBeers;

    public BeerPagerAdapter(FragmentManager fm, ArrayList<Beer> beers){
        super(fm);
        mBeers = beers;
    }

    @Override
    public Fragment getItem(int position) {
        return BeerDetailFragment.newInstance(mBeers.get(position));
    }

    @Override
    public int getCount() {
        return mBeers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBeers.get(position).getName();
    }

}
