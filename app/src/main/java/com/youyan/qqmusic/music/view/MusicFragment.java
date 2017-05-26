package com.youyan.qqmusic.music.view;

import android.support.v7.widget.RecyclerView;

import com.youyan.qqmusic.R;
import com.youyan.qqmusic.base.LazyFragment;
import com.youyan.qqmusic.music.presenter.MusicPresenter;

import butterknife.Bind;

public class MusicFragment extends LazyFragment<MusicPresenter> {

    @Bind(R.id.category_list) RecyclerView mCategoryList;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_music;
    }

    @Override
    protected void loadData(boolean pullToRefresh) {
        loadBannerData();
    }

    private void loadBannerData() {

    }

    @Override
    protected MusicPresenter createPresenter() {
        return new MusicPresenter(getContext());
    }
}
