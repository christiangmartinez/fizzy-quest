package io.xtian.fizzyquest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import java.io.IOException;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.R;
import io.xtian.fizzyquest.services.FizzyService;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = SearchActivity.class.getSimpleName();
    @Bind(R.id.userParams) EditText mUserParams;
    @Bind(R.id.searchButton)Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);
    }

    private void getBeers(String styleId) {
        final FizzyService fizzyService = new FizzyService();
        fizzyService.findBeers(styleId, new Callback(){

        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                String jsonData = response.body().string();
                Log.v(TAG, jsonData);
            } catch (IOException e) {
                e.printStackTrace();
             }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == mSearchButton) {
            String searchTerm = mUserParams.getText().toString();
            if (searchTerm.equals("")) {
                Toast.makeText(SearchActivity.this, "Please fill out search field", Toast.LENGTH_LONG).show();
            } else {
                Log.d("searchTerm", searchTerm);
            }
        }
    }
}
