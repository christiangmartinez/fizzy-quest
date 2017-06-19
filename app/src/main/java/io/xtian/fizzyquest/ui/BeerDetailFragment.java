package io.xtian.fizzyquest.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.Constants;
import io.xtian.fizzyquest.R;
import io.xtian.fizzyquest.models.Beer;

public class BeerDetailFragment extends Fragment implements View.OnClickListener{
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
        mBrewSite.setOnClickListener(this);
        mAddBeerQuest.setOnClickListener(this);
        mBrewskiName.setText(mBeer.getName());
        mBrewskiDescription.setText(mBeer.getDescription());
        mBrewskiAbv.setText("ABV: " + mBeer.getAbv());
        mBrewskiIbu.setText("IBU: " + mBeer.getIbu());
        mBrewName.setText(mBeer.getBrewery());
        mBrewSite.setText(mBeer.getBrewsite());
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mBrewSite) {
            Intent goBrewsite = new Intent(Intent.ACTION_VIEW, Uri.parse(mBeer.getBrewsite()));
            startActivity(goBrewsite);
        }

        if (v == mAddBeerQuest) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference beerRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_BEERS)
                    .child(uid);

            DatabaseReference pushRef = beerRef.push();
            String pushId = pushRef.getKey();
            mBeer.setPushId(pushId);
            pushRef.setValue(mBeer);

            Toast.makeText(getContext(), "Added to quest log!", Toast.LENGTH_SHORT).show();
        }
    }
}
