package com.youyan.qqmusic.util;


import android.content.Context;
import android.content.SharedPreferences;

import com.youyan.qqmusic.App;


public class AppConfig {

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    static {
        sp = App.getApp().getSharedPreferences("config", Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    public static boolean isNeedGuide() {
        return sp.getBoolean("need_guide", true);
    }

    public static void setNeedGuide(boolean b) {
        editor.putBoolean("need_guide", b);
    }
}
