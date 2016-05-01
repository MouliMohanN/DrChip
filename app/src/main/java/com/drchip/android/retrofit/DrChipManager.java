package com.drchip.android.retrofit;

import com.drchip.android.constants.DrChipConstants;
import com.drchip.android.interfaces.ContentService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by mohann on 30-04-2016.
 */
public class DrChipManager {

    private static DrChipManager instance = null;
    private static ContentService contentService;

    public static DrChipManager getInstance(){
        if(null == instance){
            instance = new DrChipManager();
        }
        return  instance;
    }

    public static ContentService getContentService(){
        return contentService;
    }

    public static void initializeContentService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DrChipConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        contentService = retrofit.create(ContentService.class);
    }

}
