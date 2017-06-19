package io.xtian.fizzyquest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.R;
import io.xtian.fizzyquest.models.Beer;
import io.xtian.fizzyquest.ui.BeerDetailActivity;

import static java.lang.String.valueOf;


public class  BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerViewHolder> {
    private ArrayList<Beer> mBeers = new ArrayList<>();
    private Context mContext;

    public BeerListAdapter(Context context, ArrayList<Beer> beers) {
        mContext = context;
        mBeers = beers;
    }

    @Override
    public BeerListAdapter.BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_list_item, parent, false);
        BeerViewHolder viewHolder = new BeerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BeerListAdapter.BeerViewHolder holder, int position) {
        holder.bindBeer(mBeers.get(position));
    }

    @Override
    public int getItemCount() {
        return mBeers.size();
    }

    public  class BeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.beerNameTextView)TextView mBeerNameTextView;
        @Bind(R.id.brewName)TextView mBrewName;
        @Bind(R.id.abvibu) TextView mAbvibu;
        private Context mContext;

        public BeerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindBeer(Beer beer) {
            mBeerNameTextView.setText(beer.getName());
            mBrewName.setText(beer.getBrewery());
            mAbvibu.setText("ABV: " + beer.getAbv() + "   " + "IBU: " + beer.getIbu());

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BeerDetailActivity.class);
            intent.putExtra("position", itemPosition);
            Log.i("ITEMPOSITION", valueOf(itemPosition));
            intent.putExtra("beers", Parcels.wrap(mBeers));
            mContext.startActivity(intent);
        }

    }
}
