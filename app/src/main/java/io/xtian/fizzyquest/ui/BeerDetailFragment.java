package io.xtian.fizzyquest.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.R;
import io.xtian.fizzyquest.models.Beer;

public class BeerDetailFragment extends Fragment {
    @Bind(R.id.brewskiName) TextView mBrewskiName;
    @Bind(R.id.brewskiDescription) TextView mBrewskiDescription;
    @Bind(R.id.brewskiAbv) TextView mBrewskiAbv;
    @Bind(R.id.brewskiIbu) TextView mBrewskiIbu;
    @Bind(R.id.addBeerQuest) Button mAddBeerQuest;
    @Bind(R.id.brewName) TextView mBrewName;
    @Bind(R.id.brewSite) TextView mBrewSite;

    private Beer mBeer;

    public static BeerDetailFragment newInstance(Beer beer) {
        BeerDetailFragment beerDetailFragment = new BeerDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("beer", Parcels.wrap(beer));
        beerDetailFragment.setArguments(args);
        return beerDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeer = Parcels.unwrap(getArguments().getParcelable("beer"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_detail, container, false);
        ButterKnife.bind(this, view);
        mBrewskiName.setText(mBeer.getName());
        mBrewskiDescription.setText(mBeer.getDescription());
        mBrewskiAbv.setText(mBeer.getAbv());
        mBrewskiIbu.setText(mBeer.getIbu());
        mBrewName.setText(mBeer.getBrewery());
        mBrewSite.setText(mBeer.getBrewsite());
        return view;
    }
}
