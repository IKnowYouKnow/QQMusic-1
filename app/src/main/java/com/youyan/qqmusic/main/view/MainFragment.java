package com.youyan.qqmusic.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youyan.qqmusic.R;
import com.youyan.qqmusic.base.BaseFragment;
import com.youyan.qqmusic.util.StatusBarCompat;
import com.youyan.qqmusic.widget.TitleBar;

import butterknife.Bind;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return StatusBarCompat.setStatusHolder(view, R.color.colorPrimaryDark);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView() {
        TitleBar mTitleBar = new TitleBar(mRootView.findViewById(R.id.title_bar),
                R.drawable.main_title_left_btn, 0);
    }

    @OnClick({R.id.title_left_btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left_btn:
                break;
        }
    }
}
