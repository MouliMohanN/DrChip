package com.drchip.android;

import android.app.Application;
import android.content.Context;

import com.drchip.android.retrofit.DrChipManager;

/**
 * Created by mohann on 30-04-2016.
 */
public class DrChipApplication extends Application {

    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        DrChipManager.initializeContentService();
    }
}
