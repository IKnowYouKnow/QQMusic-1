package com.youyan.qqmusic.mvp.presenter;


import com.youyan.qqmusic.mvp.view.MvpView;

public class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
