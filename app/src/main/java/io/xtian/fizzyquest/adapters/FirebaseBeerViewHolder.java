package io.xtian.fizzyquest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import io.xtian.fizzyquest.Constants;
import io.xtian.fizzyquest.R;
import io.xtian.fizzyquest.models.Beer;
import io.xtian.fizzyquest.ui.BeerDetailActivity;

public class FirebaseBeerViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;

    public FirebaseBeerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBeer(Beer beer) {
        TextView brewskiName = (TextView) mView.findViewById(R.id.brewskiName);
        TextView brewskiDescription = (TextView) mView.findViewById(R.id.brewskiDescription);
        TextView brewskiAbv = (TextView) mView.findViewById(R.id.brewskiAbv);
        TextView brewskiIbu = (TextView) mView.findViewById(R.id.brewskiIbu);
        TextView brewName = (TextView) mView.findViewById(R.id.brewName);
        TextView brewSite = (TextView) mView.findViewById(R.id.brewSite);

        brewskiName.setText(beer.getName());
        brewskiDescription.setText(beer.getDescription());
        brewskiAbv.setText(beer.getAbv());
        brewskiIbu.setText(beer.getIbu());
        brewName.setText(beer.getBrewery());
        brewSite.setText(beer.getBrewsite());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Beer> beers = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEERS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    beers.add(snapshot.getValue(Beer.class));
                }
                int itemPosition = getLayoutPosition();
                Intent intent  = new Intent(mContext, BeerDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("beers", Parcels.wrap(beers));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
