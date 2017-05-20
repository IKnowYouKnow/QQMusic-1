package com.youyan.qqmusic.base;


import android.content.Context;

import com.youyan.qqmusic.mvp.presenter.BaseMvpPresenter;
import com.youyan.qqmusic.mvp.view.MvpView;

public abstract class BasePresenter<V extends MvpView, M> extends BaseMvpPresenter<V> {

    protected Context mContext;
    protected M mModel;

    public BasePresenter(Context cxt) {
        mContext = cxt;
        mModel = createModel();
    }

    protected M createModel() {
        return null;
    }

    public void loadData(boolean pullToRefresh, LoadCallback callback) {

    }

    public interface LoadCallback {
        void onResult(String result);
    }
}
