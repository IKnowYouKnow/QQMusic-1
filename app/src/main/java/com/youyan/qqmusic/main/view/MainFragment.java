package com.youyan.qqmusic.main.view;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyan.qqmusic.R;
import com.youyan.qqmusic.base.BaseFragment;
import com.youyan.qqmusic.util.DimenUtils;
import com.youyan.qqmusic.util.StatusBarCompat;
import com.youyan.qqmusic.widget.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {

    public interface OnMainTabBtnClickListener {
        void onTabBtnClick(int id);
    }

    @Bind(R.id.main_view_pager) ViewPager mViewPager;
    private OnMainTabBtnClickListener mTabBtnListener;
    private MainTab mTab;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTabBtnListener = mActivity instanceof OnMainTabBtnClickListener ? ((OnMainTabBtnClickListener) mActivity) : null;
    }

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
        initMainTab();
    }

    private void initMainTab() {
        TitleBar mTitleBar = new TitleBar(mRootView.findViewById(R.id.title_bar),
                R.drawable.main_title_left_btn, R.drawable.main_title_right_btn);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTitleBar.getRightBtn().getLayoutParams();
        params.width = params.height = DimenUtils.dp2px(32);
        params.rightMargin = DimenUtils.dp2px(5);
        mTitleBar.getRightBtn().requestLayout();

        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) mTitleBar.getLeftButton().getLayoutParams();
        params1.width = DimenUtils.dp2px(60);
        mTitleBar.getLeftButton().requestLayout();

        View tabView = LayoutInflater.from(mActivity).inflate(R.layout.main_tab, null);
        mTab = new MainTab();
        ButterKnife.bind(mTab, tabView);
        mTitleBar.setTitleContent(tabView);
        mTab.setSelectTab(1);
    }

    @OnClick({R.id.title_left_btn, R.id.title_right_btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left_btn:
            case R.id.title_right_btn:
                if (mTabBtnListener != null) {
                    mTabBtnListener.onTabBtnClick(v.getId());
                }
                break;
        }
    }

    class MainTab implements View.OnClickListener {
        @Bind(R.id.main_tab1) TextView tab1;
        @Bind(R.id.main_tab2) TextView tab2;
        @Bind(R.id.main_tab3) TextView tab3;

        public void setSelectTab(int index) {
            tab1.setSelected(false);
            tab1.setTypeface(null, Typeface.NORMAL);
            tab2.setSelected(false);
            tab2.setTypeface(null, Typeface.NORMAL);
            tab3.setSelected(false);
            tab3.setTypeface(null, Typeface.NORMAL);
            switch (index) {
                case 0:
                    tab1.setSelected(true);
                    tab1.setTypeface(null, Typeface.BOLD);
                    break;
                case 1:
                    tab2.setSelected(true);
                    tab2.setTypeface(null, Typeface.BOLD);
                    break;
                case 2:
                    tab3.setSelected(true);
                    tab3.setTypeface(null, Typeface.BOLD);
                    break;
            }
        }

        @OnClick({R.id.main_tab1, R.id.main_tab2, R.id.main_tab3})
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_tab1:
                    setSelectTab(0);
                    break;
                case R.id.main_tab2:
                    setSelectTab(1);
                    break;
                case R.id.main_tab3:
                    setSelectTab(2);
                    break;
            }
        }
    }
}
