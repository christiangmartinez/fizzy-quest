package io.xtian.fizzyquest.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.R;
import io.xtian.fizzyquest.adapters.BeerPagerAdapter;
import io.xtian.fizzyquest.models.Beer;

import static java.lang.String.valueOf;

public class BeerDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private BeerPagerAdapter adapterviewPager;
    ArrayList<Beer> mBeers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);
        ButterKnife.bind(this);

        mBeers = Parcels.unwrap(getIntent().getParcelableExtra("beers"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        Log.i("POSITION", valueOf(startingPosition));
        adapterviewPager = new BeerPagerAdapter(getSupportFragmentManager(), mBeers);
        mViewPager.setAdapter(adapterviewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
