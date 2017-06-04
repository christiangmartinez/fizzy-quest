package io.xtian.fizzyquest.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xtian.fizzyquest.R;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.titleView) TextView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface pixelFont = Typeface.createFromAsset(getAssets(), "fonts/slkscrb.ttf");
        mTitleView.setTypeface(pixelFont);
    }
}
