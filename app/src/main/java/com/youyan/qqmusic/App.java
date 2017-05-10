package com.youyan.qqmusic;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

public class App extends Application {

    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        ShareSDK.initSDK(this);
    }

    public static App getApp() {
        return mApp;
    }
}
