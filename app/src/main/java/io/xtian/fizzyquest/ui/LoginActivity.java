package io.xtian.fizzyquest.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.createAccount) TextView mCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateAccount) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
