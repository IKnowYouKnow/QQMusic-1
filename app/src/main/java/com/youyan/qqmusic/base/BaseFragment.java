package com.youyan.qqmusic.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youyan.qqmusic.music.presenter.MusicPresenter;
import com.youyan.qqmusic.mvp.presenter.BaseMvpPresenter;
import com.youyan.qqmusic.mvp.view.MvpFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BaseMvpPresenter> extends MvpFragment<P>
        implements View.OnClickListener, BaseActivity.OnBackPressedListener {

    protected Activity mActivity;
    protected View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        BaseActivity baseActivity = mActivity instanceof BaseActivity ? ((BaseActivity) mActivity) : null;
        if (baseActivity != null) {
            baseActivity.addOnBackPressedListener(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseActivity baseActivity = mActivity instanceof BaseActivity ? ((BaseActivity) mActivity) : null;
        if (baseActivity != null) {
            baseActivity.removeOnBackPressedListener(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutRes(), container, false);
            ButterKnife.bind(this, mRootView);
            initView();
        }

        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

        return mRootView;
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected void initView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
