package com.youyan.qqmusic.music.presenter;


import android.content.Context;

import com.youyan.qqmusic.base.BasePresenter;
import com.youyan.qqmusic.music.model.MusicModel;
import com.youyan.qqmusic.music.view.MusicFragment;

public class MusicPresenter extends BasePresenter<MusicFragment, MusicModel> {

    public MusicPresenter(Context cxt) {
        super(cxt);
    }

    @Override
    protected MusicModel createModel() {
        return new MusicModel();
    }

    public void loadBannerData(LoadCallback callback) {

    }
}
