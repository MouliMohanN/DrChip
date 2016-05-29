package com.drchip.android.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.drchip.android.R;
import com.drchip.android.fragments.SplashScreen;

public class HomeActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupActionBar();
        setFragmentContainerId(R.id.fragment_container);
        loadFragment(new SplashScreen(), false, null);
    }

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    public void setupActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().hide();
        }
    }

}
