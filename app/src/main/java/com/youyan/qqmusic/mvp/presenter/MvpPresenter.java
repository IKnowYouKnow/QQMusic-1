package com.youyan.qqmusic.mvp.presenter;


import com.youyan.qqmusic.mvp.view.MvpView;

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);
    void detachView();
}
