package com.youyan.qqmusic;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.view.WindowManager;

import com.youyan.qqmusic.base.BaseActivity;
import com.youyan.qqmusic.util.DimenUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements SlidingPaneLayout.PanelSlideListener {

    @Bind(R.id.sliding_pane) SlidingPaneLayout mSlidingPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);

        mSlidingPane.setPanelSlideListener(this);
        mSlidingPane.setParallaxDistance(DimenUtils.dp2px(100));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(View panel) {

    }

    @Override
    public void onPanelClosed(View panel) {

    }

    @Override
    public void onBackPressed() {
        if (mSlidingPane.isOpen()) {
            mSlidingPane.closePane();
            return;
        }
        super.onBackPressed();
    }
}
