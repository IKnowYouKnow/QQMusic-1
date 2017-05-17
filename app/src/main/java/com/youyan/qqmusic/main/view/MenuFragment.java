package com.youyan.qqmusic.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youyan.qqmusic.R;
import com.youyan.qqmusic.base.BaseFragment;
import com.youyan.qqmusic.util.StatusBarCompat;

import butterknife.Bind;
import butterknife.OnClick;


public class MenuFragment extends BaseFragment {

    @Bind(R.id.current_themename) TextView mCurThemename;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_menu;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return StatusBarCompat.setStatusHolder(view, R.drawable.sliding_menu_status_bar_gradient);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCurThemename.setText("默认主题");
    }

    @OnClick({R.id.menu_item_myvip, R.id.menu_item_personalcenter, R.id.menu_item_notifycenter})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
