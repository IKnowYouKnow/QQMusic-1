package com.youyan.qqmusic.main.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyan.qqmusic.MainActivity;
import com.youyan.qqmusic.R;
import com.youyan.qqmusic.base.BaseFragment;
import com.youyan.qqmusic.main.adapter.MainPagerAdapter;
import com.youyan.qqmusic.discovery.view.DiscoverFragment;
import com.youyan.qqmusic.mine.view.MineFragment;
import com.youyan.qqmusic.music.view.MusicFragment;
import com.youyan.qqmusic.util.AnimatorUtils;
import com.youyan.qqmusic.util.DimenUtils;
import com.youyan.qqmusic.util.StatusBarCompat;
import com.youyan.qqmusic.widget.MiniBar;
import com.youyan.qqmusic.widget.TitleBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    public interface OnMainTabBtnClickListener {
        void onTabBtnClick(int id);
    }

    @Bind(R.id.layout_label_search) LinearLayout mLayoutLabelSearch;
    @Bind(R.id.layout_edit_search) LinearLayout mLayoutEditSearch;
    @Bind(R.id.clear_search) ImageView mClearSearchBtn;
    @Bind(R.id.voice_search_btn) ImageView mVoiceSearchBtn;
    @Bind(R.id.btn_back) ImageView mBackBtn;
    @Bind(R.id.btn_search) TextView mSearchBtn;
    @Bind(R.id.main_view_pager) ViewPager mViewPager;

    private OnMainTabBtnClickListener mTabBtnListener;
    private MainTab mTab;
    private TitleBar mTitleBar;
    private MiniBar mMiniBar;
    private boolean isSearchEditShow = false;
    private int mInitialLeft;
    private int mTitleHeight;
    private int mBackBtnW;
    private int mSearchBtnW;

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
    protected void initView() {
        initMainTab();
        initViewPager();
        initSearchBar();
        mMiniBar = new MiniBar(mRootView.findViewById(R.id.minibar));
    }

    private void initSearchBar() {
        mLayoutEditSearch.setVisibility(View.GONE);
        mBackBtn.setVisibility(View.VISIBLE);
        mSearchBtn.setVisibility(View.VISIBLE);
        mLayoutLabelSearch.setVisibility(View.VISIBLE);
        mVoiceSearchBtn.setVisibility(View.GONE);
        mClearSearchBtn.setVisibility(View.GONE);

        mLayoutLabelSearch.getViewTreeObserver().addOnGlobalLayoutListener(new LayoutListener(mLayoutLabelSearch));
        mTitleBar.getView().getViewTreeObserver().addOnGlobalLayoutListener(new LayoutListener(mTitleBar.getView()));
        mBackBtn.getViewTreeObserver().addOnGlobalLayoutListener(new LayoutListener(mBackBtn));
        mSearchBtn.getViewTreeObserver().addOnGlobalLayoutListener(new LayoutListener(mSearchBtn));
    }

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        private View mTarget;

        public LayoutListener(View target) {
            mTarget = target;
        }

        @Override
        public void onGlobalLayout() {
            if (mTarget == mLayoutLabelSearch) {
                mInitialLeft = mLayoutLabelSearch.getLeft();
                mLayoutLabelSearch.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            } else if(mTarget == mTitleBar.getView()) {
                mTitleHeight = mTitleBar.getView().getHeight();
                mTitleBar.getView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
            } else if(mTarget == mBackBtn){
                mBackBtnW = mBackBtn.getWidth();
                mBackBtn.setVisibility(View.GONE);
                mBackBtn.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            } else if(mTarget == mSearchBtn) {
                mSearchBtnW = mSearchBtn.getWidth();
                mSearchBtn.setVisibility(View.GONE);
                mSearchBtn.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        }
    }

    private void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MineFragment());
        fragments.add(new MusicFragment());
        fragments.add(new DiscoverFragment());
        MainPagerAdapter adapter = new MainPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(1, false);
        MainActivity activity = mActivity instanceof MainActivity ? ((MainActivity) mActivity) : null;
        if (activity != null) {
            activity.getSlidingPane().setConflictViewPager(mViewPager);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTab.setSelectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initMainTab() {
        mTitleBar = new TitleBar(mRootView.findViewById(R.id.title_bar),
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

    @OnClick({R.id.title_left_btn, R.id.title_right_btn, R.id.layout_search_bg,
              R.id.btn_search, R.id.btn_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left_btn:
            case R.id.title_right_btn:
                if (mTabBtnListener != null) {
                    mTabBtnListener.onTabBtnClick(v.getId());
                }
                break;
            case R.id.layout_search_bg:
                showSearchEdit();
                break;
            case R.id.btn_back:
                hideSearchEdit();
                break;
        }
    }

    private void showSearchEdit() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLayoutLabelSearch, "translationX", -mInitialLeft);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLayoutLabelSearch.setVisibility(View.GONE);
                mLayoutEditSearch.setVisibility(View.VISIBLE);
                mVoiceSearchBtn.setVisibility(View.VISIBLE);
                isSearchEditShow = true;
            }
        });

        ValueAnimator valueHeight = AnimatorUtils.valueHeight(mTitleBar.getView(), mTitleHeight, 0);
        valueHeight.setDuration(300);
        valueHeight.setInterpolator(new AccelerateDecelerateInterpolator());
        valueHeight.start();

        mBackBtn.setVisibility(View.VISIBLE);
        mSearchBtn.setVisibility(View.VISIBLE);
        ValueAnimator backLeft = AnimatorUtils.valueWidth(mBackBtn, 0, mBackBtnW);
        backLeft.setDuration(300);
        backLeft.setInterpolator(new AccelerateDecelerateInterpolator());
        backLeft.start();

        ValueAnimator searchWidth = AnimatorUtils.valueWidth(mSearchBtn, 0, mSearchBtnW);
        searchWidth.setDuration(300);
        searchWidth.setInterpolator(new AccelerateDecelerateInterpolator());
        searchWidth.start();
    }

    private void hideSearchEdit() {
        mLayoutLabelSearch.setVisibility(View.VISIBLE);
        mLayoutEditSearch.setVisibility(View.GONE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLayoutLabelSearch, "translationX", 0);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mBackBtn.setVisibility(View.GONE);
                mSearchBtn.setVisibility(View.GONE);
                isSearchEditShow = false;
            }
        });

        ValueAnimator valueHeight = AnimatorUtils.valueHeight(mTitleBar.getView(), 0, mTitleHeight);
        valueHeight.setDuration(300);
        valueHeight.setInterpolator(new AccelerateDecelerateInterpolator());
        valueHeight.start();

        ValueAnimator backLeft = AnimatorUtils.valueWidth(mBackBtn, mBackBtnW, 0);
        backLeft.setDuration(300);
        backLeft.setInterpolator(new AccelerateDecelerateInterpolator());
        backLeft.start();

        ValueAnimator searchWidth = AnimatorUtils.valueWidth(mSearchBtn, mSearchBtnW, 0);
        searchWidth.setDuration(300);
        searchWidth.setInterpolator(new AccelerateDecelerateInterpolator());
        searchWidth.start();
    }

    @Override
    public boolean onBackPressed() {
        if (isSearchEditShow) {
            hideSearchEdit();
            return true;
        } else {
            return false;
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
                    mViewPager.setCurrentItem(0, true);
                    break;
                case R.id.main_tab2:
                    setSelectTab(1);
                    mViewPager.setCurrentItem(1, true);
                    break;
                case R.id.main_tab3:
                    setSelectTab(2);
                    mViewPager.setCurrentItem(2, true);
                    break;
            }
        }
    }
}
