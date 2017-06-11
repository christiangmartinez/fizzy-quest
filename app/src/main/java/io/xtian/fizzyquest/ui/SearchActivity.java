package io.xtian.fizzyquest.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.Constants;
import io.xtian.fizzyquest.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mSearchTermReference;
    private ValueEventListener mSearchTermReferenceListener;
    @Bind(R.id.userParams) EditText mUserParams;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.questLog) Button mQuestLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchTermReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_SEARCH_TERM);

        mSearchTermReferenceListener = mSearchTermReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot searchTermSnapshot : dataSnapshot.getChildren()) {
                    String searchTerm = searchTermSnapshot.getValue().toString();
                    Log.d("Search Terms updated", "searchTerm: " + searchTerm);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);
        mQuestLog.setOnClickListener(this);
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        mSearchTermReference.removeEventListener(mSearchTermReferenceListener);
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
        if (v == mQuestLog) {
            Intent intent = new Intent(SearchActivity.this, SavedBeerListActivity.class);
            startActivity(intent);
        }
    }

    public void saveTermToFirebase(String searchTerm) {
        mSearchTermReference.push().setValue(searchTerm);
    }

}
