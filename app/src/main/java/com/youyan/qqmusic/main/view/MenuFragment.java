package com.youyan.qqmusic.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youyan.qqmusic.R;
import com.youyan.qqmusic.base.BaseFragment;
import com.youyan.qqmusic.util.StatusBarCompat;


public class MenuFragment extends BaseFragment {

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
}
