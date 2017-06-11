package io.xtian.fizzyquest.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.Constants;
import io.xtian.fizzyquest.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mSearchTermReference;
    @Bind(R.id.userParams) EditText mUserParams;
    @Bind(R.id.searchButton)Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchTermReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_SEARCH_TERM);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSearchButton) {
            String searchTerm = mUserParams.getText().toString();
            if (searchTerm.equals("")) {
                Toast.makeText(SearchActivity.this, "Please fill out search field", Toast.LENGTH_LONG).show();
            } else {
                saveTermToFirebase(searchTerm);
                Log.d("searchTerm", searchTerm);
                Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                intent.putExtra("searchTerm", searchTerm);
                startActivity(intent);
            }
        }
    }
    public void saveTermToFirebase(String searchTerm) {
        mSearchTermReference.setValue(searchTerm);
    }

}
