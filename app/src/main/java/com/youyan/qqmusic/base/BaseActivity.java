package com.youyan.qqmusic.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.youyan.qqmusic.util.StatusBarCompat;

import java.util.ArrayList;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public interface OnBackPressedListener {
        boolean onBackPressed();
    }

    protected Activity mActivity;
    private ArrayList<OnBackPressedListener> mBackPressedListeners =  new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        if (!mBackPressedListeners.isEmpty()) {
            for (OnBackPressedListener listener : mBackPressedListeners) {
                if (listener.onBackPressed()) {
                    return;
                }
            }
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    public void addOnBackPressedListener(OnBackPressedListener listener) {
        mBackPressedListeners.add(listener);
    }

    public void removeOnBackPressedListener(OnBackPressedListener listener) {
        mBackPressedListeners.remove(listener);
    }
}
