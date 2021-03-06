package com.youyan.qqmusic.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.youyan.qqmusic.mvp.presenter.BaseMvpPresenter;

public abstract class LazyFragment<P extends BaseMvpPresenter> extends BaseFragment<P> {

    private boolean isViewInit = false;
    private boolean isDataInit = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewInit = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
           lazyLoad();
        }
    }

    protected void lazyLoad() {
        if (isViewInit && !isDataInit) {
            loadData(true);
            isDataInit = true;
        }
    }

    protected void loadData(boolean pullToRefresh) {

    }

}
