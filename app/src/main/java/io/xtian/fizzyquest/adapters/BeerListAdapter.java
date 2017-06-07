package io.xtian.fizzyquest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.R;
import io.xtian.fizzyquest.models.Beer;


public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerViewHolder>{
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

    public  class BeerViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.beerNameTextView)TextView mBeerNameTextView;
        @Bind(R.id.abvibu) TextView mAbvibu;
        private Context mContext;

        public BeerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindBeer(Beer beer) {
            mBeerNameTextView.setText(beer.getName());
            mAbvibu.setText("abv: " + beer.getAbv() + "   " + "ibu: " + beer.getIbu());

        }

    }
}
