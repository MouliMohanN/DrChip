package com.drchip.android.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.drchip.android.R;
import com.drchip.android.activities.BaseActivity;
import com.drchip.android.activities.HomeActivity;

public class BaseFragment extends Fragment implements View.OnClickListener {

    BaseActivity baseActivity;

    public android.support.v7.app.ActionBar getActionBar() {
        return ((BaseActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        baseActivity = (HomeActivity) getActivity();
        super.onCreate(savedInstanceState);
    }

    public void setupActionBar() {
        if (this instanceof HomeFragment) {
            ((HomeActivity) getActivity()).setupActionBar();
            ((HomeActivity) getActivity()).getmToolbar().setVisibility(View.VISIBLE);
            android.support.v7.app.ActionBar actionBar = getActionBar();
            actionBar.show();
            ((HomeActivity) getActivity()).getmToolbar().findViewById(R.id.burger).setVisibility(View.VISIBLE);
            ((HomeActivity) getActivity()).getmToolbar().findViewById(R.id.burger).setOnClickListener(this);
            ((HomeActivity) getActivity()).getmToolbar().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);

        }
    }

    public void setTitle(String title) {
        ((TextView)getActivity().findViewById(R.id.toolbar_title)).setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(((HomeActivity) getActivity()).getmToolbar().findViewById(R.id.burger))){
            Toast.makeText(baseActivity, "on help Clicked", Toast.LENGTH_SHORT).show();
        }
    }

    private void onBackPress() {
        getActivity().onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}