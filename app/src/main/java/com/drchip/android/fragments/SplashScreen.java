package com.drchip.android.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.drchip.android.R;
import com.drchip.android.activities.BaseActivity;

/**
 * Created by mohann on 29-05-2016.
 */
public class SplashScreen extends BaseFragment {

    View rootView;
    ImageView drChipIcon;
    RelativeLayout drChipIconRl;
    RelativeLayout rlToRotate;
    TextView welcomeText;
    Animation zoomOut;
    Animation fade;
    Animation rotate;
    BaseActivity baseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.splash_screen_view, container, false);
        baseActivity = (BaseActivity) getActivity();
        drChipIcon = (ImageView) rootView.findViewById(R.id.drchip_icon);
        drChipIconRl = (RelativeLayout) rootView.findViewById(R.id.image_icon_rl);
        rlToRotate = (RelativeLayout) rootView.findViewById(R.id.rl_to_rotate);
        welcomeText = (TextView) rootView.findViewById(R.id.welcome_text_view);
        welcomeText.setVisibility(View.GONE);

        zoomOut = AnimationUtils.loadAnimation(baseActivity, R.anim.zoom_out);
        fade = AnimationUtils.loadAnimation(baseActivity, R.anim.fade_drchip_icon_rl);
        rotate = AnimationUtils.loadAnimation(baseActivity, R.anim.rotate);

        drChipIcon.setAnimation(zoomOut);

        zoomOut.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub
                drChipIcon.startAnimation(rotate);
                drChipIconRl.startAnimation(fade);
                rlToRotate.startAnimation(zoomOut);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                zoomOut.cancel();

            }
        });

        Handler textViewHandler = new Handler();
        textViewHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                welcomeText.setVisibility(View.VISIBLE);
                welcomeText.setAnimation(fade);
            }
        },2500);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                baseActivity.loadFragment(new HomeFragment(), false, HomeFragment.class.getName());
            }
        }, 4000);
        return rootView;
    }




    private class Zoom implements Runnable {
        @Override
        public void run() {
            while (true) {
                baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        drChipIcon.startAnimation(zoomOut);
                    }
                });

            }
        }
    }

}
