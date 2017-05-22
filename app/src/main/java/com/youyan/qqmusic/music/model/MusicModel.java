package com.youyan.qqmusic.music.model;

import com.youyan.qqmusic.base.BasePresenter;
import com.youyan.qqmusic.constant.Constants;
import com.youyan.qqmusic.http.HttpTask;

import java.util.HashMap;
import java.util.Map;

public class MusicModel {

    public void loadBannerData(final BasePresenter.LoadCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("showapi_appid", Constants.SHOWAPI_APPID);
        params.put("showapi_sign", Constants.SHOWAPI_SIGN);
        params.put("topid", "4");
        new HttpTask(Constants.HOT_TOP, params, new HttpTask.HttpCallback() {
            @Override
            public void onResult(String result) {
                if (callback != null) {
                    callback.onResult(result);
                }
            }
        }).execute();
    }
}
