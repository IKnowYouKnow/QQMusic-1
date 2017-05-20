package com.youyan.qqmusic.http;

import android.os.AsyncTask;

import com.youyan.qqmusic.util.HttpUtils;

import java.util.Map;

public class HttpTask extends AsyncTask<Void, Void, String> {

    public interface HttpCallback {
        void onResult(String result);
    }

    private String mUrl;
    private Map<String, String> mParams;
    private HttpCallback mCallback;

    public HttpTask(String url, Map<String, String> params, HttpCallback callback) {
        mUrl = url;
        mParams = params;
        mCallback = callback;
    }

    @Override
    protected String doInBackground(Void[] params) {
        return HttpUtils.get(mUrl, mParams);
    }

    @Override
    protected void onPostExecute(String s) {
        if (mCallback != null) {
            mCallback.onResult(s);
        }
    }
}
