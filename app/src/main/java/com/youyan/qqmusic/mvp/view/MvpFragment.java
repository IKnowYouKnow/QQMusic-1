package com.youyan.qqmusic.mvp.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.youyan.qqmusic.mvp.presenter.BaseMvpPresenter;

public abstract class MvpFragment<P extends BaseMvpPresenter> extends Fragment implements MvpView {

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected P createPresenter() {
        return null;
    }
}
