package com.drchip.android.views;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.drchip.android.R;
import com.drchip.android.constants.DrChipConstants;
import com.drchip.android.models.HomePageOptions;
import com.drchip.android.views.custom.RectangleLinearLayout;

/**
 * Created by mohann on 22-04-2016.
 */
public class HomePageCardView extends FrameLayout implements View.OnClickListener{

    RectangleLinearLayout rectangleLinearLayout;
    ImageView serviceIcon;
    TextView serviceName;
    HomePageOptions homePageOptions;
    DrChipConstants constants = new DrChipConstants();

    public HomePageCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.home_page_item_card, this);
        rectangleLinearLayout = (RectangleLinearLayout) findViewById(R.id.rootLayout);
        rectangleLinearLayout.setOnClickListener(this);
        serviceIcon = (ImageView) findViewById(R.id.service_icon);
        serviceName = (TextView) findViewById(R.id.service_name);
    }

    public void setHomePageCardView(HomePageOptions homePageOptions){
        this.homePageOptions = homePageOptions;
        serviceName.setText(homePageOptions.getName());
        if(null != homePageOptions.getIconUrl()){
            serviceIcon.setImageDrawable(homePageOptions.getIconUrl());
        }

    }

    @Override
    public void onClick(View view) {
        if(view.equals(rectangleLinearLayout)){
            if(homePageOptions.getType().equalsIgnoreCase(constants.COMPUTER_SERVICE)){

            }
        }
    }
}
