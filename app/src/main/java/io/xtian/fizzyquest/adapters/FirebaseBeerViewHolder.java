package io.xtian.fizzyquest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

import io.xtian.fizzyquest.Constants;
import io.xtian.fizzyquest.R;
import io.xtian.fizzyquest.models.Beer;
import io.xtian.fizzyquest.ui.BeerDetailActivity;
import io.xtian.fizzyquest.util.OnStartDragListener;

public class FirebaseBeerViewHolder  extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;
    public TextView beerNameTextView;

    public FirebaseBeerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindBeer(Beer beer) {
        beerNameTextView = (TextView) mView.findViewById(R.id.beerNameTextView);
        TextView brewName = (TextView) mView.findViewById(R.id.brewName);
        TextView abvibu = (TextView) mView.findViewById(R.id.abvibu);

        beerNameTextView.setText(beer.getName());
        brewName.setText(beer.getBrewery());
        abvibu.setText("ABV: " + beer.getAbv() + "   " + "IBU: " + beer.getIbu());
    }
}
