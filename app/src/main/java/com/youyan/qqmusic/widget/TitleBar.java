package com.youyan.qqmusic.widget;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.youyan.qqmusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TitleBar {

    private View mTitleBar;
    @Bind(R.id.title_left_btn) ImageView mLeftBtn;
    @Bind(R.id.title_right_btn) ImageView mRightBtn;
    @Bind(R.id.title_content) FrameLayout mTitleContent;

    public TitleBar(View titleBar) {
        this(titleBar, 0, 0);
    }

    public TitleBar(View titleBar, int leftBtn, int rightBtn) {
        setTitleBar(titleBar);
        mLeftBtn.setImageResource(leftBtn);
        mRightBtn.setImageResource(rightBtn);
    }

    public void setTitleBar(View titleBar) {
        mTitleBar = titleBar;
        init();
    }

    public View getView() {
        return mTitleBar;
    }

    private void init() {
        ButterKnife.bind(this, mTitleBar);
    }

    public ImageView getLeftButton() {
        return mLeftBtn;
    }

    public ImageView getRightBtn() {
        return mRightBtn;
    }

    public void setLeftBtn(int leftBtn) {
        mLeftBtn.setImageResource(leftBtn);
    }

    public void setRightBtn(int rightBtn) {
        mRightBtn.setImageResource(rightBtn);
    }

    public void setTitleContent(View view) {
        mTitleContent.addView(view);
    }
}
