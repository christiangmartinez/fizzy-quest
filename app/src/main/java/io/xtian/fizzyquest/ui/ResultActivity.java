package io.xtian.fizzyquest.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.R;
import io.xtian.fizzyquest.adapters.BeerListAdapter;
import io.xtian.fizzyquest.models.Beer;
import io.xtian.fizzyquest.services.FizzyService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultActivity extends AppCompatActivity {
    public static final String TAG = SearchActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private BeerListAdapter mAdapter;
    public ArrayList<Beer> mBeers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String searchTerm = intent.getStringExtra("searchTerm");
        getBeers(searchTerm);
    }

    private void getBeers(String styleId) {
        final FizzyService fizzyService = new FizzyService();
        fizzyService.findBeers(styleId, new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mBeers = fizzyService.processResults(response);
                ResultActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new BeerListAdapter(getApplicationContext(), mBeers);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ResultActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
