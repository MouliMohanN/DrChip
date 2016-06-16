package com.drchip.android.retrofit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by mohann on 30-04-2016.
 */
public class DrChipContentManager {

    private static DrChipContentManager drChipContentManager;

    public static DrChipContentManager getInstance(){
        if(null == drChipContentManager){
            drChipContentManager = new DrChipContentManager();
        }
        return drChipContentManager;
    }

    public void sayHelloWorld(String url, JsonObject request){
        Call<JsonObject> call = DrChipManager.getInstance().getContentService().helloWorld(url, request);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response, Retrofit retrofit) {
                try{
                    JsonObject result = response.body();
                    System.out.println("Mouli HelloWorld .. " + result);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        try {
            Call<JsonObject> doCall = call.clone();
            doCall.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sayHelloWorldGet(String url){
        Call<JsonObject> call = DrChipManager.getContentService().helloWorld(url);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response, Retrofit retrofit) {
                try{
                    JsonObject result = response.body();
                    System.out.println("Mouli HelloWorldGet .. " + result);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        try {
            Call<JsonObject> doCall = call.clone();
            doCall.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMail(JsonObject jsonObject){
        JsonElement name = jsonObject.get("name");
        JsonElement phone = jsonObject.get("phone");
        JsonElement email = jsonObject.get("email");
        JsonElement message = jsonObject.get("message");
        Call<JsonObject> call = DrChipManager.getInstance().getContentService().sendMail("/contact/send_mail.php", name.toString(),
                phone.toString(), email.toString(), message.toString());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response, Retrofit retrofit) {
                try{
                    String result = response.toString();
                    System.out.println("Mouli HelloWorldGet .. " + result);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        try {
            Call<JsonObject> doCall = call.clone();
            doCall.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
