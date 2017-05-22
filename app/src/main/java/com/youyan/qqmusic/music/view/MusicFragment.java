package com.youyan.qqmusic.music.view;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.youyan.qqmusic.R;
import com.youyan.qqmusic.base.BasePresenter;
import com.youyan.qqmusic.base.LazyFragment;
import com.youyan.qqmusic.music.presenter.MusicPresenter;

import butterknife.Bind;

public class MusicFragment extends LazyFragment<MusicPresenter> {

    @Bind(R.id.music_viewpager) ViewPager mViewPager;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_music;
    }

    @Override
    protected void loadData(boolean pullToRefresh) {
        loadBannerData();
    }

    private void loadBannerData() {
        mPresenter.loadBannerData(new BasePresenter.LoadCallback() {
            @Override
            public void onResult(String result) {
                Log.e(null, "onResult: "+result );
            }
        });
    }

    @Override
    protected MusicPresenter createPresenter() {
        return new MusicPresenter(getContext());
    }
}
