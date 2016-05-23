package com.drchip.android.models.mobileinfo;

public class UserAgent {

    Os os;
    App app;


    public UserAgent() {
    }

    public UserAgent(Os os, App app) {
        this.os = os;
        this.app = app;
    }

    public Os getOs() {
        return os;
    }

    public void setOs(Os os) {
        this.os = os;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

}
